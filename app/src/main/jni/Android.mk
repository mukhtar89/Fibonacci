LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES := com_platinumforge_fibonacci_FibLib.c
LOCAL_SRC_FILES += com_platinumforge_fibonacci_empty.c
LOCAL_MODULE := com_platinumforge_fibonacci_FibLib

include $(BUILD_SHARED_LIBRARY)