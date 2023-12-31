// Generated by jextract

package org.ffmpeg.libavutil;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public class avutil_h  {

    public static final OfByte C_CHAR = JAVA_BYTE;
    public static final OfShort C_SHORT = JAVA_SHORT;
    public static final OfInt C_INT = JAVA_INT;
    public static final OfLong C_LONG = JAVA_LONG;
    public static final OfLong C_LONG_LONG = JAVA_LONG;
    public static final OfFloat C_FLOAT = JAVA_FLOAT;
    public static final OfDouble C_DOUBLE = JAVA_DOUBLE;
    public static final AddressLayout C_POINTER = RuntimeHelper.POINTER;
    /**
     * {@snippet :
     * #define EAGAIN 11
     * }
     */
    public static int EAGAIN() {
        return (int)11L;
    }
    /**
     * {@snippet :
     * #define AV_ERROR_MAX_STRING_SIZE 64
     * }
     */
    public static int AV_ERROR_MAX_STRING_SIZE() {
        return (int)64L;
    }
    /**
     * {@snippet :
     * enum AVMediaType.AVMEDIA_TYPE_UNKNOWN = -1;
     * }
     */
    public static int AVMEDIA_TYPE_UNKNOWN() {
        return (int)-1L;
    }
    /**
     * {@snippet :
     * enum AVMediaType.AVMEDIA_TYPE_VIDEO = 0;
     * }
     */
    public static int AVMEDIA_TYPE_VIDEO() {
        return (int)0L;
    }
    /**
     * {@snippet :
     * enum AVMediaType.AVMEDIA_TYPE_AUDIO = 1;
     * }
     */
    public static int AVMEDIA_TYPE_AUDIO() {
        return (int)1L;
    }
    /**
     * {@snippet :
     * enum AVMediaType.AVMEDIA_TYPE_DATA = 2;
     * }
     */
    public static int AVMEDIA_TYPE_DATA() {
        return (int)2L;
    }
    /**
     * {@snippet :
     * enum AVMediaType.AVMEDIA_TYPE_SUBTITLE = 3;
     * }
     */
    public static int AVMEDIA_TYPE_SUBTITLE() {
        return (int)3L;
    }
    /**
     * {@snippet :
     * enum AVMediaType.AVMEDIA_TYPE_ATTACHMENT = 4;
     * }
     */
    public static int AVMEDIA_TYPE_ATTACHMENT() {
        return (int)4L;
    }
    /**
     * {@snippet :
     * enum AVMediaType.AVMEDIA_TYPE_NB = 5;
     * }
     */
    public static int AVMEDIA_TYPE_NB() {
        return (int)5L;
    }
    public static MethodHandle av_strerror$MH() {
        return RuntimeHelper.requireNonNull(constants$0.const$1,"av_strerror");
    }
    /**
     * {@snippet :
     * int av_strerror(int errnum, char* errbuf, size_t errbuf_size);
     * }
     */
    public static int av_strerror(int errnum, MemorySegment errbuf, long errbuf_size) {
        var mh$ = av_strerror$MH();
        try {
            return (int)mh$.invokeExact(errnum, errbuf, errbuf_size);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
    public static MethodHandle av_channel_layout_from_mask$MH() {
        return RuntimeHelper.requireNonNull(constants$2.const$1,"av_channel_layout_from_mask");
    }
    /**
     * {@snippet :
     * int av_channel_layout_from_mask(AVChannelLayout* channel_layout, uint64_t mask);
     * }
     */
    public static int av_channel_layout_from_mask(MemorySegment channel_layout, long mask) {
        var mh$ = av_channel_layout_from_mask$MH();
        try {
            return (int)mh$.invokeExact(channel_layout, mask);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
    public static MethodHandle av_channel_layout_uninit$MH() {
        return RuntimeHelper.requireNonNull(constants$2.const$3,"av_channel_layout_uninit");
    }
    /**
     * {@snippet :
     * void av_channel_layout_uninit(AVChannelLayout* channel_layout);
     * }
     */
    public static void av_channel_layout_uninit(MemorySegment channel_layout) {
        var mh$ = av_channel_layout_uninit$MH();
        try {
            mh$.invokeExact(channel_layout);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
    /**
     * {@snippet :
     * enum AVSampleFormat.AV_SAMPLE_FMT_NONE = -1;
     * }
     */
    public static int AV_SAMPLE_FMT_NONE() {
        return (int)-1L;
    }
    /**
     * {@snippet :
     * enum AVSampleFormat.AV_SAMPLE_FMT_U8 = 0;
     * }
     */
    public static int AV_SAMPLE_FMT_U8() {
        return (int)0L;
    }
    /**
     * {@snippet :
     * enum AVSampleFormat.AV_SAMPLE_FMT_S16 = 1;
     * }
     */
    public static int AV_SAMPLE_FMT_S16() {
        return (int)1L;
    }
    /**
     * {@snippet :
     * enum AVSampleFormat.AV_SAMPLE_FMT_S32 = 2;
     * }
     */
    public static int AV_SAMPLE_FMT_S32() {
        return (int)2L;
    }
    /**
     * {@snippet :
     * enum AVSampleFormat.AV_SAMPLE_FMT_FLT = 3;
     * }
     */
    public static int AV_SAMPLE_FMT_FLT() {
        return (int)3L;
    }
    /**
     * {@snippet :
     * enum AVSampleFormat.AV_SAMPLE_FMT_DBL = 4;
     * }
     */
    public static int AV_SAMPLE_FMT_DBL() {
        return (int)4L;
    }
    /**
     * {@snippet :
     * enum AVSampleFormat.AV_SAMPLE_FMT_U8P = 5;
     * }
     */
    public static int AV_SAMPLE_FMT_U8P() {
        return (int)5L;
    }
    /**
     * {@snippet :
     * enum AVSampleFormat.AV_SAMPLE_FMT_S16P = 6;
     * }
     */
    public static int AV_SAMPLE_FMT_S16P() {
        return (int)6L;
    }
    /**
     * {@snippet :
     * enum AVSampleFormat.AV_SAMPLE_FMT_S32P = 7;
     * }
     */
    public static int AV_SAMPLE_FMT_S32P() {
        return (int)7L;
    }
    /**
     * {@snippet :
     * enum AVSampleFormat.AV_SAMPLE_FMT_FLTP = 8;
     * }
     */
    public static int AV_SAMPLE_FMT_FLTP() {
        return (int)8L;
    }
    /**
     * {@snippet :
     * enum AVSampleFormat.AV_SAMPLE_FMT_DBLP = 9;
     * }
     */
    public static int AV_SAMPLE_FMT_DBLP() {
        return (int)9L;
    }
    /**
     * {@snippet :
     * enum AVSampleFormat.AV_SAMPLE_FMT_S64 = 10;
     * }
     */
    public static int AV_SAMPLE_FMT_S64() {
        return (int)10L;
    }
    /**
     * {@snippet :
     * enum AVSampleFormat.AV_SAMPLE_FMT_S64P = 11;
     * }
     */
    public static int AV_SAMPLE_FMT_S64P() {
        return (int)11L;
    }
    public static MethodHandle av_samples_get_buffer_size$MH() {
        return RuntimeHelper.requireNonNull(constants$2.const$5,"av_samples_get_buffer_size");
    }
    /**
     * {@snippet :
     * int av_samples_get_buffer_size(int* linesize, int nb_channels, int nb_samples, enum AVSampleFormat sample_fmt, int align);
     * }
     */
    public static int av_samples_get_buffer_size(MemorySegment linesize, int nb_channels, int nb_samples, int sample_fmt, int align) {
        var mh$ = av_samples_get_buffer_size$MH();
        try {
            return (int)mh$.invokeExact(linesize, nb_channels, nb_samples, sample_fmt, align);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
    public static MethodHandle av_samples_fill_arrays$MH() {
        return RuntimeHelper.requireNonNull(constants$3.const$1,"av_samples_fill_arrays");
    }
    /**
     * {@snippet :
     * int av_samples_fill_arrays(uint8_t** audio_data, int* linesize, const uint8_t* buf, int nb_channels, int nb_samples, enum AVSampleFormat sample_fmt, int align);
     * }
     */
    public static int av_samples_fill_arrays(MemorySegment audio_data, MemorySegment linesize, MemorySegment buf, int nb_channels, int nb_samples, int sample_fmt, int align) {
        var mh$ = av_samples_fill_arrays$MH();
        try {
            return (int)mh$.invokeExact(audio_data, linesize, buf, nb_channels, nb_samples, sample_fmt, align);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
    public static MethodHandle av_frame_alloc$MH() {
        return RuntimeHelper.requireNonNull(constants$11.const$1,"av_frame_alloc");
    }
    /**
     * {@snippet :
     * AVFrame* av_frame_alloc();
     * }
     */
    public static MemorySegment av_frame_alloc() {
        var mh$ = av_frame_alloc$MH();
        try {
            return (java.lang.foreign.MemorySegment)mh$.invokeExact();
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
    public static MethodHandle av_frame_free$MH() {
        return RuntimeHelper.requireNonNull(constants$11.const$2,"av_frame_free");
    }
    /**
     * {@snippet :
     * void av_frame_free(AVFrame** frame);
     * }
     */
    public static void av_frame_free(MemorySegment frame) {
        var mh$ = av_frame_free$MH();
        try {
            mh$.invokeExact(frame);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
    /**
     * {@snippet :
     * #define AVERROR_EOF -541478725
     * }
     */
    public static int AVERROR_EOF() {
        return (int)-541478725L;
    }
    /**
     * {@snippet :
     * #define AV_CH_LAYOUT_STEREO 3
     * }
     */
    public static long AV_CH_LAYOUT_STEREO() {
        return 3L;
    }
}


