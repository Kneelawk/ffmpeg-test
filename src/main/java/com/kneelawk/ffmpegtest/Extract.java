package com.kneelawk.ffmpegtest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;

public class Extract {
    private static final PosixFilePermission[] PERMISSIONS = {
        PosixFilePermission.OTHERS_EXECUTE, PosixFilePermission.OTHERS_WRITE, PosixFilePermission.OTHERS_READ,
        PosixFilePermission.GROUP_EXECUTE, PosixFilePermission.GROUP_WRITE, PosixFilePermission.GROUP_READ,
        PosixFilePermission.OWNER_EXECUTE, PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_READ
    };
    private static final String NOT_FOUND_STRING = ": cannot open shared object file: No such file or directory";

    private static final String TEMP_DIR_PREFIX = "ffmpegtest-";
    private static final String LINUX_NATIVES_ARCHIVE = "natives/ffmpeg-master-latest-linux64-lgpl-shared.tar.xz";
    private static final Path TEMP_DIR;

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

        System.out.println("Extracting natives...");
        try (InputStream is = Extract.class.getResourceAsStream(LINUX_NATIVES_ARCHIVE)) {
            if (is == null) throw new RuntimeException("Failed to find " + LINUX_NATIVES_ARCHIVE);

            try (XZCompressorInputStream xzis = new XZCompressorInputStream(is);
                 TarArchiveInputStream tais = new TarArchiveInputStream(xzis)) {

                TarArchiveEntry entry;
                while ((entry = tais.getNextTarEntry()) != null) {
                    Path outputFile = TEMP_DIR.resolve(entry.getName());

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

    public static Path load(String name) {
        Path loading = TEMP_DIR.resolve(name);
        load(loading);

        return loading;
    }

    private static void load(Path toLoad) {
        if (!Files.exists(toLoad)) throw new RuntimeException("Path " + toLoad + " does not exist");

        boolean loading = true;
        while (loading) {
            try {
                System.load(toLoad.toAbsolutePath().toString());
                loading = false;
            } catch (UnsatisfiedLinkError e) {
                String message = e.getMessage();
                if (message.endsWith(NOT_FOUND_STRING)) {
                    String messageStart = message.substring(0, message.length() - NOT_FOUND_STRING.length());
                    String missingLibrary = messageStart.substring(messageStart.lastIndexOf(": ") + 2);

                    Path depPath = toLoad.getParent().resolve(missingLibrary);
                    load(depPath);
                } else {
                    throw e;
                }
            }
        }
    }
}
