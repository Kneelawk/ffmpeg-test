// Generated by jextract

package org.ffmpeg.libavutil;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * struct AVRational {
 *     int num;
 *     int den;
 * };
 * }
 */
public class AVRational {

    public static MemoryLayout $LAYOUT() {
        return constants$0.const$0;
    }
    public static VarHandle num$VH() {
        return constants$0.const$1;
    }
    /**
     * Getter for field:
     * {@snippet :
     * int num;
     * }
     */
    public static int num$get(MemorySegment seg) {
        return (int)constants$0.const$1.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * int num;
     * }
     */
    public static void num$set(MemorySegment seg, int x) {
        constants$0.const$1.set(seg, x);
    }
    public static int num$get(MemorySegment seg, long index) {
        return (int)constants$0.const$1.get(seg.asSlice(index*sizeof()));
    }
    public static void num$set(MemorySegment seg, long index, int x) {
        constants$0.const$1.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle den$VH() {
        return constants$0.const$2;
    }
    /**
     * Getter for field:
     * {@snippet :
     * int den;
     * }
     */
    public static int den$get(MemorySegment seg) {
        return (int)constants$0.const$2.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * int den;
     * }
     */
    public static void den$set(MemorySegment seg, int x) {
        constants$0.const$2.set(seg, x);
    }
    public static int den$get(MemorySegment seg, long index) {
        return (int)constants$0.const$2.get(seg.asSlice(index*sizeof()));
    }
    public static void den$set(MemorySegment seg, long index, int x) {
        constants$0.const$2.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}

