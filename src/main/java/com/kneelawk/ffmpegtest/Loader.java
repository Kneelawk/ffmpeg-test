package com.kneelawk.ffmpegtest;

import java.lang.foreign.Arena;
import java.lang.foreign.SymbolLookup;
import java.util.HashMap;
import java.util.Map;

public class Loader {
    private static Map<String, SymbolLookup> lookups;

    public record Library(String name, String path) {
    }

    public static void load(Library[] libraries) {
        Extractor.extractNatives();

        lookups = new HashMap<>();

        for (Library lib : libraries) {
            lookups.put(lib.name, SymbolLookup.libraryLookup(Extractor.TEMP_DIR.resolve(lib.path), Arena.global()));
        }
    }

    public static SymbolLookup getLibrary(String name) {
        if (lookups == null) throw new IllegalStateException("Loader has not been initialized.");

        return lookups.get(name);
    }
}
