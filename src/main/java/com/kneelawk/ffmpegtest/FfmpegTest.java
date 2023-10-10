package com.kneelawk.ffmpegtest;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

import org.ffmpeg.libavformat.AVFormatContext;
import org.ffmpeg.libavformat.avformat_h;

public class FfmpegTest {
    public static void main(String[] args) {
        System.out.println("Starting up ffmpeg...");

        Loader.load(new Loader.Library[]{
            new Loader.Library("libavutil", "ffmpeg-master-latest-linux64-lgpl-shared/lib/libavutil.so"),
            new Loader.Library("libavcodec", "ffmpeg-master-latest-linux64-lgpl-shared/lib/libavcodec.so"),
            new Loader.Library("libavformat", "ffmpeg-master-latest-linux64-lgpl-shared/lib/libavformat.so")
        });

        System.out.println("Loading file...");

        try (Arena confined = Arena.ofConfined()) {
            MemorySegment ctxPtrPtr = confined.allocate(ValueLayout.ADDRESS);
            MemorySegment url = confined.allocateUtf8String("tears_of_steel_1080p.webm");

            int res = avformat_h.avformat_open_input(ctxPtrPtr, url, MemorySegment.NULL, MemorySegment.NULL);
            if (res != 0) {
                System.err.println("Error opening file.");
                return;
            }

            MemorySegment ctxPtr = ctxPtrPtr.get(ValueLayout.ADDRESS, 0);

            res = avformat_h.avformat_find_stream_info(ctxPtr, MemorySegment.NULL);
            if (res < 0) {
                System.err.println("Error finding stream info.");
                return;
            }

            avformat_h.av_dump_format(ctxPtr, 0, url, 0);

            MemorySegment ctx = AVFormatContext.ofAddress(ctxPtr, confined);

            int streamCount = AVFormatContext.nb_streams$get(ctx);
            System.out.println("Stream count: " + streamCount);
        }
    }
}
