cmake_minimum_required(VERSION 3.13)
project(maze C)

set(CMAKE_C_STANDARD 99)

add_executable(maze main.c Queue.h Stack.h)