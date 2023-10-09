package com.kneelawk.ffmpegtest;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class FfmpegTest {
    public static void main(String[] args) {
        System.out.println("Starting up ffmpeg...");

        try (Arena confined = Arena.ofConfined()) {
            MemorySegment ctxHolder = confined.allocate(ValueLayout.ADDRESS);
            MemorySegment url = confined.allocateUtf8String("tears_of_steel_1080p.webm");

            int res = AvFormat.avformatOpenInput(ctxHolder, url, MemorySegment.NULL, MemorySegment.NULL);
            if (res != 0) {
                System.err.println("Error opening file.");
                return;
            }

            MemorySegment ctx = ctxHolder.get(ValueLayout.ADDRESS, 0);

            res = AvFormat.avformatFindStreamInfo(ctx, MemorySegment.NULL);
            if (res < 0) {
                System.err.println("Error finding stream info.");
                return;
            }

            AvFormat.avDumpFormat(ctx, 0, url, 0);
        }
    }
}
