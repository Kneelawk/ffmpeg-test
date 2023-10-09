package com.kneelawk.ffmpegtest;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;

public class AvFormat {
    private static final String LIB_NAME = "ffmpeg-master-latest-linux64-lgpl-shared/lib/libavformat.so";

    private static final Linker LINKER = Linker.nativeLinker();
    private static final SymbolLookup LOOKUP = SymbolLookup.libraryLookup(Extract.load(LIB_NAME), Arena.global());

    private static MethodHandle lookup(String name, FunctionDescriptor descriptor, Linker.Option... options) {
        return LINKER.downcallHandle(
            LOOKUP.find(name).orElseThrow(() -> new RuntimeException("Unable to find '" + name + "' method!")),
            descriptor, options);
    }

//    private static final MethodHandle AV_REGISTER_ALL = lookup("av_register_all", FunctionDescriptor.ofVoid());
//    public static void avRegisterAll() {
//        try {
//            AV_REGISTER_ALL.invoke();
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
//    }

    private static final MethodHandle AVFORMAT_OPEN_INPUT = lookup("avformat_open_input",
        FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS,
            ValueLayout.ADDRESS));

    public static int avformatOpenInput(MemorySegment ps, MemorySegment url, MemorySegment fmt, MemorySegment options) {
        try {
            return (int) AVFORMAT_OPEN_INPUT.invoke(ps, url, fmt, options);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static final MethodHandle AVFORMAT_FIND_STREAM_INFO = lookup("avformat_find_stream_info",
        FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS));

    public static int avformatFindStreamInfo(MemorySegment ic, MemorySegment options) {
        try {
            return (int) AVFORMAT_FIND_STREAM_INFO.invoke(ic, options);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static final MethodHandle AV_DUMP_FORMAT = lookup("av_dump_format",
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS,
            ValueLayout.JAVA_INT));
    public static void avDumpFormat(MemorySegment ic, int index, MemorySegment url, int isOutput) {
        try {
            AV_DUMP_FORMAT.invoke(ic, index, url, isOutput);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
