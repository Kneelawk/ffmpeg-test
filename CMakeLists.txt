cmake_minimum_required(VERSION 3.26)
project(ffmpegtest)

set(CMAKE_CXX_STANDARD 17)

find_package(PkgConfig REQUIRED)
pkg_check_modules(ffmpeg REQUIRED IMPORTED_TARGET libavformat libavcodec libavutil)

add_executable(ffmpegtest main.cpp)
target_link_libraries(ffmpegtest PRIVATE PkgConfig::ffmpeg)
