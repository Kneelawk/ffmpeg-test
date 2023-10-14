package com.kneelawk.ffmpegtest.natives;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;

public class NativeExtractor {
    private static final PosixFilePermission[] PERMISSIONS = {
        PosixFilePermission.OTHERS_EXECUTE, PosixFilePermission.OTHERS_WRITE, PosixFilePermission.OTHERS_READ,
        PosixFilePermission.GROUP_EXECUTE, PosixFilePermission.GROUP_WRITE, PosixFilePermission.GROUP_READ,
        PosixFilePermission.OWNER_EXECUTE, PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_READ
    };

    private static final String TEMP_DIR_PREFIX = "ffmpegtest-";
    public static final Path TEMP_DIR;

    static {
        try {
            TEMP_DIR = Files.createTempDirectory(TEMP_DIR_PREFIX);
        } catch (IOException e) {
            throw new RuntimeException("Error creating temp dir.", e);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("Cleaning up temp files...");
                Files.walkFileTree(TEMP_DIR, new SimpleFileVisitor<>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        if (exc != null) throw exc;

                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    }
                });
                System.out.println("Temp files removed.");
            } catch (IOException e) {
                throw new RuntimeException("Unable to clean up temp dir.", e);
            }
        }));
    }

    public static void extractNatives(URL archive, ArchiveType type) {
        if (archive == null) throw new RuntimeException("Archive not found.");

        System.out.println("Extracting natives...");
        try (InputStream is = archive.openStream()) {
            if (is == null) throw new RuntimeException("Failed to find " + archive);

            type.extract(is, TEMP_DIR);
        } catch (IOException e) {
            throw new RuntimeException("Failed to extract natives.", e);
        }
        System.out.println("Natives extracted.");
    }

    private static void setPerms(Path outputFile, TarArchiveEntry entry) throws IOException {
        Set<PosixFilePermission> perms = new HashSet<>();

        int mode = entry.getMode();
        for (int i = 0; i < 9; i++) {
            if ((mode & (1 << i)) != 0) {
                perms.add(PERMISSIONS[i]);
            }
        }

        Files.setPosixFilePermissions(outputFile, perms);
    }

    public interface ArchiveType {
        void extract(InputStream archive, Path dest) throws IOException;
    }

    public static final ArchiveType TAR_XZ = (archive, dest) -> {
        try (XZCompressorInputStream xzis = new XZCompressorInputStream(archive);
             TarArchiveInputStream tais = new TarArchiveInputStream(xzis)) {

            TarArchiveEntry entry;
            while ((entry = tais.getNextTarEntry()) != null) {
                Path outputFile = dest.resolve(entry.getName());

                if (entry.isDirectory()) {
                    Files.createDirectories(outputFile);
                } else if (entry.isSymbolicLink()) {
                    Path target = outputFile.getParent().resolve(entry.getLinkName());
                    Files.createSymbolicLink(outputFile, target);
                } else {
                    Files.copy(tais, outputFile, StandardCopyOption.REPLACE_EXISTING);

                    setPerms(outputFile, entry);
                }
            }
        }
    };

    public static final ArchiveType ZIP = (archive, dest) -> {
        try (ZipInputStream zis = new ZipInputStream(archive)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                Path outputFile = dest.resolve(entry.getName());

                if (entry.isDirectory()) {
                    Files.createDirectories(outputFile);
                } else {
                    Files.copy(zis, outputFile, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    };
}
