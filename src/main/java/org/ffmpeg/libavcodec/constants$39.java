// Generated by jextract

package org.ffmpeg.libavcodec;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$39 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$39() {}
    static final VarHandle const$0 = constants$11.const$0.varHandle(MemoryLayout.PathElement.groupElement("get_encode_buffer"));
    static final VarHandle const$1 = constants$11.const$0.varHandle(MemoryLayout.PathElement.groupElement("frame_num"));
    static final FunctionDescriptor const$2 = FunctionDescriptor.of(RuntimeHelper.POINTER,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "avcodec_alloc_context3",
        constants$39.const$2
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "avcodec_free_context",
        constants$5.const$2
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "avcodec_parameters_to_context",
        constants$16.const$0
    );
}


