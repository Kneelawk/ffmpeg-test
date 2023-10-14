package com.kneelawk.ffmpegtest.natives;

import java.util.Map;

public class Natives {
    private static final Map<OsConfiguration, NativeLoader.Configuration> CONFIGURATIONS = Map.of(
        OsConfiguration.LINUX_AMD64, new NativeLoader.Configuration(
            Natives.class.getResource("ffmpeg-master-latest-linux64-lgpl-shared.tar.xz"),
            NativeExtractor.TAR_XZ,
            // These libraries *must* be loaded in a specific order
            new NativeLoader.Library[]{
                new NativeLoader.Library("libavutil", "ffmpeg-master-latest-linux64-lgpl-shared/lib/libavutil.so"),
                new NativeLoader.Library("libswresample",
                    "ffmpeg-master-latest-linux64-lgpl-shared/lib/libswresample.so"),
                new NativeLoader.Library("libswscale", "ffmpeg-master-latest-linux64-lgpl-shared/lib/libswscale.so"),
                new NativeLoader.Library("libavcodec", "ffmpeg-master-latest-linux64-lgpl-shared/lib/libavcodec.so"),
                new NativeLoader.Library("libavformat", "ffmpeg-master-latest-linux64-lgpl-shared/lib/libavformat.so")
            }
        ),
        OsConfiguration.WINDOWS_AMD64, new NativeLoader.Configuration(
            Natives.class.getResource("ffmpeg-master-latest-win64-lgpl-shared.zip"),
            NativeExtractor.ZIP,
            new NativeLoader.Library[]{
                new NativeLoader.Library("libavutil", "ffmpeg-master-latest-win64-lgpl-shared/bin/avutil-58.dll"),
                new NativeLoader.Library("libswresample",
                    "ffmpeg-master-latest-win64-lgpl-shared/bin/swresample-4.dll"),
                new NativeLoader.Library("libswscale", "ffmpeg-master-latest-win64-lgpl-shared/bin/swscale-7.dll"),
                new NativeLoader.Library("libavcodec", "ffmpeg-master-latest-win64-lgpl-shared/bin/avcodec-60.dll"),
                new NativeLoader.Library("libavformat", "ffmpeg-master-latest-win64-lgpl-shared/bin/avformat-60.dll")
            }
        )
    );

    public static void load() {
        NativeLoader.load(CONFIGURATIONS);
    }
}
