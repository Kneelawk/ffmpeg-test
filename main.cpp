#include <iostream>

extern "C" {
#include <libavformat/avformat.h>
#include <libavcodec/avcodec.h>
#include <libavutil/avutil.h>
}

int main() {
    std::cout << "Hello ffmpeg!" << std::endl;

    AVFormatContext *ctx = nullptr;
    const char *filename = "../tears_of_steel_1080p.webm";

    if (avformat_open_input(&ctx, filename, nullptr, nullptr)) {
        std::cerr << "Error opening input." << std::endl;
        return -1;
    }

    if (avformat_find_stream_info(ctx, nullptr)) {
        std::cerr << "Error finding stream info." << std::endl;
        return -1;
    }

    av_dump_format(ctx, 0, filename, false);

    AVStream **streams = ctx->streams;

    const AVCodec *videoCodec = nullptr;
    int videoStreamIndex = av_find_best_stream(ctx, AVMEDIA_TYPE_VIDEO, -1, -1, &videoCodec, 0);
    if (videoStreamIndex < 0) {
        std::cerr << "Unable to find video stream." << std::endl;
        return -1;
    }

    AVStream *videoStream = streams[videoStreamIndex];
    AVCodecParameters *videoParams = videoStream->codecpar;

    AVCodecContext *videoCtx = avcodec_alloc_context3(videoCodec);
    if (videoCtx == nullptr) {
        std::cerr << "Failed to allocate context." << std::endl;
        return -1;
    }

    if (avcodec_parameters_to_context(videoCtx, videoParams) < 0) {
        std::cerr << "Unable to copy parameters to context." << std::endl;
        return -1;
    }

    if (avcodec_open2(videoCtx, videoCodec, nullptr) < 0) {
        std::cerr << "Failed to open the codec." << std::endl;
        return -1;
    }

    AVRational timeBase = videoStream->time_base;

    AVPacket *packet = av_packet_alloc();
    AVFrame *frame = av_frame_alloc();

    std::cout << "Reading frames..." << std::endl;

    uint64_t framesRead = 0;
    while (av_read_frame(ctx, packet) >= 0) {
        if (packet->stream_index == videoStreamIndex) {
            int res = avcodec_send_packet(videoCtx, packet);
            if (res < 0 && res != AVERROR(EAGAIN)) {
                continue;
            }

            while ((res = avcodec_receive_frame(videoCtx, frame)) >= 0) {
                // TODO: use frame

                framesRead++;
                if (framesRead % 100 == 0) {
                    long double seconds = frame->pts * timeBase.num / (long double) timeBase.den;
                    std::cout << "(" << framesRead << ") " << seconds << "s" << std::endl;
                }
            }

            if (res != AVERROR(EAGAIN)) {
                std::cerr << "Failed to read frame." << std::endl;
            }
        }

        av_packet_unref(packet);
    }

    std::cout << "Draining codec..." << std::endl;

    int res;
    while ((res = avcodec_send_packet(videoCtx, nullptr)) >= 0) {
        while ((res = avcodec_receive_frame(videoCtx, frame)) >= 0) {
            // TODO: use frame

            framesRead++;
            if (framesRead % 100 == 0) {
                long double seconds = frame->pts * timeBase.num / (long double) timeBase.den;
                std::cout << "(" << framesRead << ") " << seconds << "s" << std::endl;
            }
        }

        if (res != AVERROR(EAGAIN) && res != AVERROR_EOF) {
            std::cerr << "Failed to read frame." << std::endl;
        }
    }

    if (res != AVERROR_EOF) {
        std::cerr << "Error decoding." << std::endl;
    }

    std::cout << "Finished reading." << std::endl;

    av_frame_free(&frame);
    av_packet_free(&packet);
    avcodec_free_context(&videoCtx);
    avformat_close_input(&ctx);

    return 0;
}
