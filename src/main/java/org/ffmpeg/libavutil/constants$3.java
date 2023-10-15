// Generated by jextract

package org.ffmpeg.libavutil;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$3 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$3() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.of(JAVA_INT,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        JAVA_INT,
        JAVA_INT,
        JAVA_INT,
        JAVA_INT
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "av_samples_fill_arrays",
        constants$3.const$0
    );
    static final StructLayout const$2 = MemoryLayout.structLayout(
        MemoryLayout.sequenceLayout(8, RuntimeHelper.POINTER).withName("data"),
        MemoryLayout.sequenceLayout(8, JAVA_INT).withName("linesize"),
        RuntimeHelper.POINTER.withName("extended_data"),
        JAVA_INT.withName("width"),
        JAVA_INT.withName("height"),
        JAVA_INT.withName("nb_samples"),
        JAVA_INT.withName("format"),
        JAVA_INT.withName("key_frame"),
        JAVA_INT.withName("pict_type"),
        MemoryLayout.structLayout(
            JAVA_INT.withName("num"),
            JAVA_INT.withName("den")
        ).withName("sample_aspect_ratio"),
        JAVA_LONG.withName("pts"),
        JAVA_LONG.withName("pkt_dts"),
        MemoryLayout.structLayout(
            JAVA_INT.withName("num"),
            JAVA_INT.withName("den")
        ).withName("time_base"),
        JAVA_INT.withName("coded_picture_number"),
        JAVA_INT.withName("display_picture_number"),
        JAVA_INT.withName("quality"),
        MemoryLayout.paddingLayout(4),
        RuntimeHelper.POINTER.withName("opaque"),
        JAVA_INT.withName("repeat_pict"),
        JAVA_INT.withName("interlaced_frame"),
        JAVA_INT.withName("top_field_first"),
        JAVA_INT.withName("palette_has_changed"),
        JAVA_LONG.withName("reordered_opaque"),
        JAVA_INT.withName("sample_rate"),
        MemoryLayout.paddingLayout(4),
        JAVA_LONG.withName("channel_layout"),
        MemoryLayout.sequenceLayout(8, RuntimeHelper.POINTER).withName("buf"),
        RuntimeHelper.POINTER.withName("extended_buf"),
        JAVA_INT.withName("nb_extended_buf"),
        MemoryLayout.paddingLayout(4),
        RuntimeHelper.POINTER.withName("side_data"),
        JAVA_INT.withName("nb_side_data"),
        JAVA_INT.withName("flags"),
        JAVA_INT.withName("color_range"),
        JAVA_INT.withName("color_primaries"),
        JAVA_INT.withName("color_trc"),
        JAVA_INT.withName("colorspace"),
        JAVA_INT.withName("chroma_location"),
        MemoryLayout.paddingLayout(4),
        JAVA_LONG.withName("best_effort_timestamp"),
        JAVA_LONG.withName("pkt_pos"),
        JAVA_LONG.withName("pkt_duration"),
        RuntimeHelper.POINTER.withName("metadata"),
        JAVA_INT.withName("decode_error_flags"),
        JAVA_INT.withName("channels"),
        JAVA_INT.withName("pkt_size"),
        MemoryLayout.paddingLayout(4),
        RuntimeHelper.POINTER.withName("hw_frames_ctx"),
        RuntimeHelper.POINTER.withName("opaque_ref"),
        JAVA_LONG.withName("crop_top"),
        JAVA_LONG.withName("crop_bottom"),
        JAVA_LONG.withName("crop_left"),
        JAVA_LONG.withName("crop_right"),
        RuntimeHelper.POINTER.withName("private_ref"),
        MemoryLayout.structLayout(
            JAVA_INT.withName("order"),
            JAVA_INT.withName("nb_channels"),
            MemoryLayout.unionLayout(
                JAVA_LONG.withName("mask"),
                RuntimeHelper.POINTER.withName("map")
            ).withName("u"),
            RuntimeHelper.POINTER.withName("opaque")
        ).withName("ch_layout"),
        JAVA_LONG.withName("duration")
    ).withName("AVFrame");
    static final VarHandle const$3 = constants$3.const$2.varHandle(MemoryLayout.PathElement.groupElement("extended_data"));
    static final VarHandle const$4 = constants$3.const$2.varHandle(MemoryLayout.PathElement.groupElement("width"));
    static final VarHandle const$5 = constants$3.const$2.varHandle(MemoryLayout.PathElement.groupElement("height"));
}


