package com.kneelawk.ffmpegtest;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

import org.ffmpeg.libavcodec.AVCodec;
import org.ffmpeg.libavcodec.AVCodecContext;
import org.ffmpeg.libavcodec.AVCodecParameters;
import org.ffmpeg.libavcodec.AVPacket;
import org.ffmpeg.libavcodec.avcodec_h;
import org.ffmpeg.libavformat.AVFormatContext;
import org.ffmpeg.libavformat.AVStream;
import org.ffmpeg.libavformat.avformat_h;
import org.ffmpeg.libavutil.AVFrame;
import org.ffmpeg.libavutil.AVRational;
import org.ffmpeg.libavutil.avutil_h;

import com.kneelawk.ffmpegtest.natives.Natives;

public class FfmpegTest {
    private static long videoFramesRead = 0;
    private static long audioFramesRead = 0;

    public static void main(String[] args) {
        System.out.println("Starting up ffmpeg...");

        Natives.load();

        System.out.println("Loading file...");

        try (Arena confined = Arena.ofConfined()) {
            MemorySegment ctxRef = confined.allocate(ValueLayout.ADDRESS, MemorySegment.NULL);
            MemorySegment url = confined.allocateUtf8String("imagination-engine-1920x1080-av1.webm");

            int res = avformat_h.avformat_open_input(ctxRef, url, MemorySegment.NULL, MemorySegment.NULL);
            if (res != 0) {
                System.err.println("Error opening file.");
                return;
            }

            MemorySegment ctx = ctxRef.get(ValueLayout.ADDRESS.withTargetLayout(AVFormatContext.$LAYOUT()), 0);

            try {
                res = avformat_h.avformat_find_stream_info(ctx, MemorySegment.NULL);
                if (res < 0) {
                    System.err.println("Error finding stream info.");
                    return;
                }

                avformat_h.av_dump_format(ctx, 0, url, 0);

                int streamCount = AVFormatContext.nb_streams$get(ctx);
                System.out.println("Stream count: " + streamCount);

                MemorySegment videoCodecRef = confined.allocate(ValueLayout.ADDRESS, MemorySegment.NULL);
                MemorySegment audioCodecRef = confined.allocate(ValueLayout.ADDRESS, MemorySegment.NULL);
                int videoIndex =
                    avformat_h.av_find_best_stream(ctx, avutil_h.AVMEDIA_TYPE_VIDEO(), -1, -1, videoCodecRef, 0);
                int audioIndex =
                    avformat_h.av_find_best_stream(ctx, avutil_h.AVMEDIA_TYPE_AUDIO(), -1, -1, audioCodecRef, 0);

                if (videoIndex < 0) {
                    System.err.println("Contains no video streams.");
                    return;
                }

                if (audioIndex < 0) {
                    System.err.println("Contains no audio streams.");
                    return;
                }

                MemorySegment videoCodec =
                    videoCodecRef.get(ValueLayout.ADDRESS.withTargetLayout(AVCodec.$LAYOUT()), 0);
                MemorySegment audioCodec =
                    audioCodecRef.get(ValueLayout.ADDRESS.withTargetLayout(AVCodec.$LAYOUT()), 0);

                MemorySegment streams = AVFormatContext.streams$get(ctx);
                MemorySegment videoStream =
                    streams.getAtIndex(ValueLayout.ADDRESS.withTargetLayout(AVStream.$LAYOUT()), videoIndex);
                MemorySegment audioStream =
                    streams.getAtIndex(ValueLayout.ADDRESS.withTargetLayout(AVStream.$LAYOUT()), audioIndex);
                MemorySegment videoCodecParam =
                    AVCodecParameters.ofAddress(AVStream.codecpar$get(videoStream), confined);
                MemorySegment audioCodecParam =
                    AVCodecParameters.ofAddress(AVStream.codecpar$get(audioStream), confined);

                System.out.println("Video: (" + videoIndex + ") " + AVCodec.long_name$get(videoCodec).getUtf8String(0));
                System.out.println("Audio: (" + audioIndex + ") " + AVCodec.long_name$get(audioCodec).getUtf8String(0));

                MemorySegment videoTimeBase = AVRational.ofAddress(AVStream.time_base$slice(videoStream), confined);
                int videoNum = AVRational.num$get(videoTimeBase);
                int videoDen = AVRational.den$get(videoTimeBase);
                MemorySegment audioTimeBase = AVRational.ofAddress(AVStream.time_base$slice(audioStream), confined);
                int audioNum = AVRational.num$get(audioTimeBase);
                int audioDen = AVRational.den$get(audioTimeBase);

                MemorySegment videoCtx =
                    AVCodecContext.ofAddress(avcodec_h.avcodec_alloc_context3(videoCodec), confined);
                MemorySegment audioCtx =
                    AVCodecContext.ofAddress(avcodec_h.avcodec_alloc_context3(audioCodec), confined);

                if (videoCtx.address() == 0) {
                    System.err.println("Error allocating video context.");
                    return;
                }
                if (audioCtx.address() == 0) {
                    System.err.println("Error allocating audio context.");
                    return;
                }

                try {
                    if (avcodec_h.avcodec_parameters_to_context(videoCtx, videoCodecParam) < 0) {
                        System.err.println("Error copying video parameters.");
                        return;
                    }
                    if (avcodec_h.avcodec_parameters_to_context(audioCtx, audioCodecParam) < 0) {
                        System.err.println("Error copying audio parameters.");
                        return;
                    }

                    if (avcodec_h.avcodec_open2(videoCtx, videoCodec, MemorySegment.NULL) < 0) {
                        System.err.println("Error opening video codec.");
                        return;
                    }
                    if (avcodec_h.avcodec_open2(audioCtx, audioCodec, MemorySegment.NULL) < 0) {
                        System.err.println("Error opening audio codec.");
                        return;
                    }

                    MemorySegment packet = AVPacket.ofAddress(avcodec_h.av_packet_alloc(), confined);
                    if (packet.address() == 0) {
                        System.err.println("Error allocating packet.");
                        return;
                    }
                    MemorySegment frame = AVFrame.ofAddress(avutil_h.av_frame_alloc(), confined);
                    if (frame.address() == 0) {
                        System.err.println("Error allocating frame.");
                        return;
                    }

                    System.out.println("Reading frames...");
                    try {
                        while (avformat_h.av_read_frame(ctx, packet) >= 0) {
                            int streamIndex = AVPacket.stream_index$get(packet);
                            MemorySegment codecCtx;
                            if (streamIndex == videoIndex) {
                                codecCtx = videoCtx;
                            } else if (streamIndex == audioIndex) {
                                codecCtx = audioCtx;
                            } else {
                                // packet belongs to neither stream
                                continue;
                            }

                            res = avcodec_h.avcodec_send_packet(codecCtx, packet);
                            if (res < 0 && res != -avutil_h.EAGAIN()) {
                                System.err.println("Error reading packet.");
                                continue;
                            }

                            receiveFrames(codecCtx, frame, streamIndex, videoIndex, videoNum, videoDen, audioNum,
                                audioDen);

                            avcodec_h.av_packet_unref(packet);
                        }

                        System.out.println("Draining codecs...");

                        while ((res = avcodec_h.avcodec_send_packet(videoCtx, MemorySegment.NULL)) >= 0) {
                            receiveFrames(videoCtx, frame, videoIndex, videoIndex, videoNum, videoDen, audioNum,
                                audioDen);
                        }
                        if (res != avutil_h.AVERROR_EOF()) {
                            System.err.println("Error draining video codec.");
                        }

                        while ((res = avcodec_h.avcodec_send_packet(audioCtx, MemorySegment.NULL)) >= 0) {
                            receiveFrames(videoCtx, frame, audioIndex, videoIndex, videoNum, videoDen, audioNum,
                                audioDen);
                        }
                        if (res != avutil_h.AVERROR_EOF()) {
                            System.err.println("Error draining audio codec.");
                        }

                        System.out.println("Finished reading.");
                    } finally {
                        // free packet & frame
                        MemorySegment packetRef = confined.allocate(ValueLayout.ADDRESS, packet);
                        MemorySegment frameRef = confined.allocate(ValueLayout.ADDRESS, frame);
                        avcodec_h.av_packet_free(packetRef);
                        avutil_h.av_frame_free(frameRef);
                    }
                } finally {
                    // close decoder contexts
                    MemorySegment videoCtxRef = confined.allocate(ValueLayout.ADDRESS, videoCtx);
                    MemorySegment audioCtxRef = confined.allocate(ValueLayout.ADDRESS, audioCtx);

                    avcodec_h.avcodec_free_context(videoCtxRef);
                    avcodec_h.avcodec_free_context(audioCtxRef);
                }
            } finally {
                // close demuxer context
                avformat_h.avformat_close_input(ctxRef);
            }
        }
    }

    private static void receiveFrames(MemorySegment codecCtx, MemorySegment frame, int streamIndex, int videoIndex,
                                      int videoNum, double videoDen, int audioNum, double audioDen) {
        int res;
        while ((res = avcodec_h.avcodec_receive_frame(codecCtx, frame)) >= 0) {
            // TODO: use frame

            if (streamIndex == videoIndex) {
                videoFramesRead++;
                if (videoFramesRead % 100 == 0) {
                    System.out.println(
                        "VIDEO: (" + videoFramesRead + ") " + (AVFrame.pts$get(frame) * videoNum / videoDen) + "s");
                }
            } else {
                audioFramesRead++;
                if (audioFramesRead % 100 == 0) {
                    System.out.println(
                        "AUDIO: (" + audioFramesRead + ") " + (AVFrame.pts$get(frame) * audioNum / audioDen) + "s");
                }
            }
        }

        if (res != -avutil_h.EAGAIN() && res != avutil_h.AVERROR_EOF()) {
            System.err.println("Error reading frame.");
        }
    }
}
