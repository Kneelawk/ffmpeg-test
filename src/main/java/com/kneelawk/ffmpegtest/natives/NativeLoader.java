package com.kneelawk.ffmpegtest.natives;

import java.lang.foreign.Arena;
import java.lang.foreign.SymbolLookup;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class NativeLoader {
    private static Map<String, SymbolLookup> lookups;

    public record Library(String name, String path) {
    }

    public record Configuration(URL archive, NativeExtractor.ArchiveType type, Library[] libraries) {
        public void load() {
            NativeLoader.load(archive, type, libraries);
        }
    }

    public static void load(Map<OsConfiguration, Configuration> configurations) {
        Configuration configuration = configurations.get(OsConfiguration.CURRENT);

        if (configuration == null)
            throw new RuntimeException("Unsupported OS configuration: " + OsConfiguration.CURRENT.getName());

        configuration.load();
    }

    public static void load(URL archive, NativeExtractor.ArchiveType type, Library[] libraries) {
        NativeExtractor.extractNatives(archive, type);

        lookups = new HashMap<>();

        for (Library lib : libraries) {
            lookups.put(lib.name,
                SymbolLookup.libraryLookup(NativeExtractor.TEMP_DIR.resolve(lib.path), Arena.global()));
        }
    }

    public static SymbolLookup getLibrary(String name) {
        if (lookups == null) throw new IllegalStateException("Loader has not been initialized.");

        return lookups.get(name);
    }
}
