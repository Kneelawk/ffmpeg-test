// Generated by jextract

package org.ffmpeg.libavformat;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$13 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$13() {}
    static final VarHandle const$0 = constants$2.const$4.varHandle(MemoryLayout.PathElement.groupElement("output_ts_offset"));
    static final VarHandle const$1 = constants$2.const$4.varHandle(MemoryLayout.PathElement.groupElement("dump_separator"));
    static final VarHandle const$2 = constants$2.const$4.varHandle(MemoryLayout.PathElement.groupElement("data_codec_id"));
    static final VarHandle const$3 = constants$2.const$4.varHandle(MemoryLayout.PathElement.groupElement("protocol_whitelist"));
    static final FunctionDescriptor const$4 = FunctionDescriptor.of(JAVA_INT,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        JAVA_INT,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$5 = RuntimeHelper.upcallHandle(AVFormatContext.io_open.class, "apply", constants$13.const$4);
}


