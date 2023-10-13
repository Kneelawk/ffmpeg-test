// Generated by jextract

package org.ffmpeg.libavcodec;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public class avcodec_h  {

    public static final OfByte C_CHAR = JAVA_BYTE;
    public static final OfShort C_SHORT = JAVA_SHORT;
    public static final OfInt C_INT = JAVA_INT;
    public static final OfLong C_LONG = JAVA_LONG;
    public static final OfLong C_LONG_LONG = JAVA_LONG;
    public static final OfFloat C_FLOAT = JAVA_FLOAT;
    public static final OfDouble C_DOUBLE = JAVA_DOUBLE;
    public static final AddressLayout C_POINTER = RuntimeHelper.POINTER;
    public static MethodHandle av_packet_alloc$MH() {
        return RuntimeHelper.requireNonNull(constants$5.const$1,"av_packet_alloc");
    }
    /**
     * {@snippet :
     * AVPacket* av_packet_alloc();
     * }
     */
    public static MemorySegment av_packet_alloc() {
        var mh$ = av_packet_alloc$MH();
        try {
            return (java.lang.foreign.MemorySegment)mh$.invokeExact();
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
    public static MethodHandle av_packet_free$MH() {
        return RuntimeHelper.requireNonNull(constants$5.const$3,"av_packet_free");
    }
    /**
     * {@snippet :
     * void av_packet_free(AVPacket** pkt);
     * }
     */
    public static void av_packet_free(MemorySegment pkt) {
        var mh$ = av_packet_free$MH();
        try {
            mh$.invokeExact(pkt);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
    public static MethodHandle av_packet_unref$MH() {
        return RuntimeHelper.requireNonNull(constants$5.const$4,"av_packet_unref");
    }
    /**
     * {@snippet :
     * void av_packet_unref(AVPacket* pkt);
     * }
     */
    public static void av_packet_unref(MemorySegment pkt) {
        var mh$ = av_packet_unref$MH();
        try {
            mh$.invokeExact(pkt);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
    public static MethodHandle avcodec_alloc_context3$MH() {
        return RuntimeHelper.requireNonNull(constants$39.const$3,"avcodec_alloc_context3");
    }
    /**
     * {@snippet :
     * AVCodecContext* avcodec_alloc_context3(const AVCodec* codec);
     * }
     */
    public static MemorySegment avcodec_alloc_context3(MemorySegment codec) {
        var mh$ = avcodec_alloc_context3$MH();
        try {
            return (java.lang.foreign.MemorySegment)mh$.invokeExact(codec);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
    public static MethodHandle avcodec_free_context$MH() {
        return RuntimeHelper.requireNonNull(constants$39.const$4,"avcodec_free_context");
    }
    /**
     * {@snippet :
     * void avcodec_free_context(AVCodecContext** avctx);
     * }
     */
    public static void avcodec_free_context(MemorySegment avctx) {
        var mh$ = avcodec_free_context$MH();
        try {
            mh$.invokeExact(avctx);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
    public static MethodHandle avcodec_parameters_to_context$MH() {
        return RuntimeHelper.requireNonNull(constants$39.const$5,"avcodec_parameters_to_context");
    }
    /**
     * {@snippet :
     * int avcodec_parameters_to_context(AVCodecContext* codec, struct AVCodecParameters* par);
     * }
     */
    public static int avcodec_parameters_to_context(MemorySegment codec, MemorySegment par) {
        var mh$ = avcodec_parameters_to_context$MH();
        try {
            return (int)mh$.invokeExact(codec, par);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
    public static MethodHandle avcodec_open2$MH() {
        return RuntimeHelper.requireNonNull(constants$40.const$1,"avcodec_open2");
    }
    /**
     * {@snippet :
     * int avcodec_open2(AVCodecContext* avctx, const AVCodec* codec, AVDictionary** options);
     * }
     */
    public static int avcodec_open2(MemorySegment avctx, MemorySegment codec, MemorySegment options) {
        var mh$ = avcodec_open2$MH();
        try {
            return (int)mh$.invokeExact(avctx, codec, options);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
    public static MethodHandle avcodec_send_packet$MH() {
        return RuntimeHelper.requireNonNull(constants$40.const$2,"avcodec_send_packet");
    }
    /**
     * {@snippet :
     * int avcodec_send_packet(AVCodecContext* avctx, const AVPacket* avpkt);
     * }
     */
    public static int avcodec_send_packet(MemorySegment avctx, MemorySegment avpkt) {
        var mh$ = avcodec_send_packet$MH();
        try {
            return (int)mh$.invokeExact(avctx, avpkt);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
    public static MethodHandle avcodec_receive_frame$MH() {
        return RuntimeHelper.requireNonNull(constants$40.const$3,"avcodec_receive_frame");
    }
    /**
     * {@snippet :
     * int avcodec_receive_frame(AVCodecContext* avctx, AVFrame* frame);
     * }
     */
    public static int avcodec_receive_frame(MemorySegment avctx, MemorySegment frame) {
        var mh$ = avcodec_receive_frame$MH();
        try {
            return (int)mh$.invokeExact(avctx, frame);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
}


