// Generated by jextract

package org.ffmpeg.libavcodec;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$13 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$13() {}
    static final MethodHandle const$0 = RuntimeHelper.upcallHandle(AVCodecContext.get_format.class, "apply", constants$12.const$5);
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        constants$12.const$5
    );
    static final VarHandle const$2 = constants$7.const$5.varHandle(MemoryLayout.PathElement.groupElement("get_format"));
    static final VarHandle const$3 = constants$7.const$5.varHandle(MemoryLayout.PathElement.groupElement("max_b_frames"));
    static final VarHandle const$4 = constants$7.const$5.varHandle(MemoryLayout.PathElement.groupElement("b_quant_factor"));
    static final VarHandle const$5 = constants$7.const$5.varHandle(MemoryLayout.PathElement.groupElement("b_quant_offset"));
}


