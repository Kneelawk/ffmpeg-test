// Generated by jextract

package org.ffmpeg.libavcodec;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$4 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$4() {}
    static final VarHandle const$0 = constants$0.const$0.varHandle(PathElement.groupElement("gop_size"));
    static final VarHandle const$1 = constants$0.const$0.varHandle(PathElement.groupElement("pix_fmt"));
    static final FunctionDescriptor const$2 = FunctionDescriptor.ofVoid(
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        JAVA_INT,
        JAVA_INT,
        JAVA_INT
    );
    static final MethodHandle const$3 = RuntimeHelper.upcallHandle(AVCodecContext.draw_horiz_band.class, "apply", constants$4.const$2);
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        constants$4.const$2
    );
    static final VarHandle const$5 = constants$0.const$0.varHandle(PathElement.groupElement("draw_horiz_band"));
}