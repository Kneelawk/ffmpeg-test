// Generated by jextract

package org.ffmpeg.libavcodec;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$16 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$16() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.of(JAVA_INT,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$1 = RuntimeHelper.upcallHandle(AVCodecContext.get_format.class, "apply", constants$16.const$0);
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        constants$16.const$0
    );
    static final VarHandle const$3 = constants$11.const$0.varHandle(MemoryLayout.PathElement.groupElement("get_format"));
    static final VarHandle const$4 = constants$11.const$0.varHandle(MemoryLayout.PathElement.groupElement("max_b_frames"));
    static final VarHandle const$5 = constants$11.const$0.varHandle(MemoryLayout.PathElement.groupElement("b_quant_factor"));
}


