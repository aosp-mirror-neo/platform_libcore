/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#define LOG_TAG "OsConstantsTest"

#include <errno.h>
#include <fcntl.h>
#include <netdb.h>
#include <netinet/icmp6.h>
#include <netinet/if_ether.h>
#include <netinet/in.h>
#include <netinet/ip_icmp.h>
#include <netinet/tcp.h>
#include <netinet/udp.h>
#include <netpacket/packet.h>
#include <net/if.h>
#include <net/if_arp.h>
#include <poll.h>
#include <signal.h>
#include <stdlib.h>
#include <sys/capability.h>
#include <sys/ioctl.h>
#include <sys/mman.h>
#include <sys/prctl.h>
#include <sys/resource.h>
#include <sys/socket.h>
#include <sys/stat.h>
#include <sys/un.h>
#include <sys/wait.h>
#include <sys/xattr.h>
#include <unistd.h>

#include <linux/if_addr.h>
#include <linux/rtnetlink.h>

#include <nativehelper/JNIHelp.h>
#include <nativehelper/jni_macros.h>

#include "android-base/logging.h"

#if defined(__GLIBC__)
// MADV_SOFT_OFFLINE is otherwise unavailable from glibc.
#include <asm-generic/mman-common.h>
#endif

// Taken from Portability.h
#if __has_include(<linux/vm_sockets.h>)
#include <linux/vm_sockets.h>
#else  // __has_include(<linux/vm_sockets.h>)
// the platform does not support virtio-vsock
#define AF_VSOCK (-1)
#define VMADDR_PORT_ANY (-1)
#define VMADDR_CID_ANY (-1)
#define VMADDR_CID_LOCAL (-1)
#define VMADDR_CID_HOST (-1)
#endif  // __has_include(<linux/vm_sockets.h>)


// Constants whose value does not depend on architecture and is set in Java code.
#define JAVA_INITIALIZED_FIELDS(V) \
  V(AF_INET) \
  V(AF_INET6) \
  V(AF_NETLINK) \
  V(AF_PACKET) \
  V(AF_UNIX) \
  V(AF_VSOCK) \
  V(AF_UNSPEC) \

static void validateConstant(JNIEnv* env, jclass c, const char* fieldName, int value) {
    jfieldID field = env->GetStaticFieldID(c, fieldName, "I");
    int actual = env->GetStaticIntField(c, field);
    if (actual != value) {
        LOG(FATAL) << "Value of " << fieldName << " is wrong. "
                   << "Expected " << value << ", got " << actual;
    }
}

static void validateConstants(JNIEnv* env, jclass c) {
#define VALIDATE_CONSTANTS(Name) \
    validateConstant(env, c, # Name, Name);
    JAVA_INITIALIZED_FIELDS(VALIDATE_CONSTANTS)
#undef VALIDATE_CONSTANTS
}

extern "C"
JNIEXPORT void JNICALL Java_libcore_android_system_OsConstantsTest_checkConsistency(JNIEnv* env,
                                                                                    jclass) {
    jclass os_constants_class = env->FindClass("android/system/OsConstants");
    if (os_constants_class == nullptr) {
        LOG(FATAL) << "Could not find OsConstants";
    }
    validateConstants(env, os_constants_class);
    // TODO(b/383285151): check that amount of fields initialized in
    // OsConstantsHolder_initConstants and in Java(JAVA_INITIALIZED_FIELDS) matches with total field
    // count in OsConstants.
}
