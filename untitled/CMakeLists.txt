cmake_minimum_required(VERSION 3.7)
project(untitled)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES main.cpp Triangle.cpp Triangle.h Vector3d.cpp Vector3d.h)
add_executable(untitled ${SOURCE_FILES})