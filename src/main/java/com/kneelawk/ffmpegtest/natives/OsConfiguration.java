package com.kneelawk.ffmpegtest.natives;

public enum OsConfiguration {
    LINUX_AMD64("Linux, amd64"),
    WINDOWS_AMD64("Windows, amd64");

    public static final OsConfiguration CURRENT;
    static {
        String name = System.getProperty("os.name");
        String lowerName = name.toLowerCase();
        String arch = System.getProperty("os.arch");

        boolean amd64 = arch.equals("amd64") || arch.equals("x86_64");

        if (lowerName.startsWith("linux") && amd64) CURRENT = LINUX_AMD64;
        else if (lowerName.startsWith("windows") && amd64) CURRENT = WINDOWS_AMD64;
        // TODO: support mac
        else throw new RuntimeException("Unsupported OS configuration: " + name + ", " + arch);
    }

    private final String name;

    OsConfiguration(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
