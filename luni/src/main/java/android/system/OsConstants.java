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

package android.system;

import static android.annotation.SystemApi.Client.MODULE_LIBRARIES;

import android.annotation.SystemApi;
import android.compat.annotation.UnsupportedAppUsage;

/**
 * Constants and helper functions for use with {@link Os}.
 */
public final class OsConstants {
    @UnsupportedAppUsage
    private OsConstants() {
    }

    /**
     * Returns the index of the element in the {@link StructCapUserData} (cap_user_data)
     * array that this capability is stored in.
     *
     * @param x capability
     * @return index of the element in the {@link StructCapUserData} array storing this capability
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    public static int CAP_TO_INDEX(int x) { return x >>> 5; }

    /**
     * Returns the mask for the given capability. This is relative to the capability's
     * {@link StructCapUserData} (cap_user_data) element, the index of which can be
     * retrieved with {@link CAP_TO_INDEX}.
     *
     * @param x capability
     * @return mask for given capability
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    public static int CAP_TO_MASK(int x) { return 1 << (x & 31); }

    /**
     * Tests whether the given mode is a block device.
     */
    public static boolean S_ISBLK(int mode) { return (mode & S_IFMT) == S_IFBLK; }

    /**
     * Tests whether the given mode is a character device.
     */
    public static boolean S_ISCHR(int mode) { return (mode & S_IFMT) == S_IFCHR; }

    /**
     * Tests whether the given mode is a directory.
     */
    public static boolean S_ISDIR(int mode) { return (mode & S_IFMT) == S_IFDIR; }

    /**
     * Tests whether the given mode is a FIFO.
     */
    public static boolean S_ISFIFO(int mode) { return (mode & S_IFMT) == S_IFIFO; }

    /**
     * Tests whether the given mode is a regular file.
     */
    public static boolean S_ISREG(int mode) { return (mode & S_IFMT) == S_IFREG; }

    /**
     * Tests whether the given mode is a symbolic link.
     */
    public static boolean S_ISLNK(int mode) { return (mode & S_IFMT) == S_IFLNK; }

    /**
     * Tests whether the given mode is a socket.
     */
    public static boolean S_ISSOCK(int mode) { return (mode & S_IFMT) == S_IFSOCK; }

    /**
     * Extracts the exit status of a child. Only valid if WIFEXITED returns true.
     */
    public static int WEXITSTATUS(int status) { return (status & 0xff00) >> 8; }

    /**
     * Tests whether the child dumped core. Only valid if WIFSIGNALED returns true.
     */
    public static boolean WCOREDUMP(int status) { return (status & 0x80) != 0; }

    /**
     * Returns the signal that caused the child to exit. Only valid if WIFSIGNALED returns true.
     */
    public static int WTERMSIG(int status) { return status & 0x7f; }

    /**
     * Returns the signal that cause the child to stop. Only valid if WIFSTOPPED returns true.
     */
    public static int WSTOPSIG(int status) { return WEXITSTATUS(status); }

    /**
     * Tests whether the child exited normally.
     */
    public static boolean WIFEXITED(int status) { return (WTERMSIG(status) == 0); }

    /**
     * Tests whether the child was stopped (not terminated) by a signal.
     */
    public static boolean WIFSTOPPED(int status) { return (WTERMSIG(status) == 0x7f); }

    /**
     * Tests whether the child was terminated by a signal.
     */
    public static boolean WIFSIGNALED(int status) { return (WTERMSIG(status + 1) >= 2); }

    /*
     * Public fields of this class are defined in native and are part of ABI. However, in certain
     * cases, bionic and glibc disagree so it is not always possible to set field to an exact value
     * and it has to be obtained using JNI.
     *
     * Creating a native method per each field is not viable: there are more than 500 fields. But
     * static final fields have to be initialized in java code. Previously they were set to 0 and
     * overwritten using JNI's SetStaticIntField method. That, however, is an undefined
     * behaviour [1].
     *
     * And hence this inelegant workaround.
     *
     * [1] https://openjdk.org/jeps/8349536#Mutating-final-fields-from-native-code
     */
    public static final int AF_INET = OsConstantsHolder.AF_INET;
    public static final int AF_INET6 = OsConstantsHolder.AF_INET6;
    public static final int AF_NETLINK = OsConstantsHolder.AF_NETLINK;
    public static final int AF_PACKET = OsConstantsHolder.AF_PACKET;
    public static final int AF_UNIX = OsConstantsHolder.AF_UNIX;

    /**
     * The virt-vsock address family, linux specific.
     * It is used with {@code struct sockaddr_vm} from uapi/linux/vm_sockets.h.
     *
     * @see <a href="https://man7.org/linux/man-pages/man7/vsock.7.html">vsock(7)</a>
     * @see VmSocketAddress
     */
    public static final int AF_VSOCK = OsConstantsHolder.AF_VSOCK;
    public static final int AF_UNSPEC = OsConstantsHolder.AF_UNSPEC;
    public static final int AI_ADDRCONFIG = OsConstantsHolder.AI_ADDRCONFIG;
    public static final int AI_ALL = OsConstantsHolder.AI_ALL;
    public static final int AI_CANONNAME = OsConstantsHolder.AI_CANONNAME;
    public static final int AI_NUMERICHOST = OsConstantsHolder.AI_NUMERICHOST;
    public static final int AI_NUMERICSERV = OsConstantsHolder.AI_NUMERICSERV;
    public static final int AI_PASSIVE = OsConstantsHolder.AI_PASSIVE;
    public static final int AI_V4MAPPED = OsConstantsHolder.AI_V4MAPPED;
    public static final int ARPHRD_ETHER = OsConstantsHolder.ARPHRD_ETHER;

    /**
      * The virtio-vsock {@code svmPort} value to bind for any available port.
      *
      * @see <a href="https://man7.org/linux/man-pages/man7/vsock.7.html">vsock(7)</a>
      * @see VmSocketAddress
      */
    public static final int VMADDR_PORT_ANY = OsConstantsHolder.VMADDR_PORT_ANY;

    /**
      * The virtio-vsock {@code svmCid} value to listens for all CIDs.
      *
      * @see <a href="https://man7.org/linux/man-pages/man7/vsock.7.html">vsock(7)</a>
      * @see VmSocketAddress
      */
    public static final int VMADDR_CID_ANY = OsConstantsHolder.VMADDR_CID_ANY;

    /**
      * The virtio-vsock {@code svmCid} value for host communication.
      *
      * @see <a href="https://man7.org/linux/man-pages/man7/vsock.7.html">vsock(7)</a>
      * @see VmSocketAddress
      */
    public static final int VMADDR_CID_LOCAL = OsConstantsHolder.VMADDR_CID_LOCAL;

    /**
      * The virtio-vsock {@code svmCid} value for loopback communication.
      *
      * @see <a href="https://man7.org/linux/man-pages/man7/vsock.7.html">vsock(7)</a>
      * @see VmSocketAddress
      */
    public static final int VMADDR_CID_HOST = OsConstantsHolder.VMADDR_CID_HOST;

    /**
     * ARP protocol loopback device identifier.
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    public static final int ARPHRD_LOOPBACK = OsConstantsHolder.ARPHRD_LOOPBACK;
    public static final int CAP_AUDIT_CONTROL = OsConstantsHolder.CAP_AUDIT_CONTROL;
    public static final int CAP_AUDIT_WRITE = OsConstantsHolder.CAP_AUDIT_WRITE;
    public static final int CAP_BLOCK_SUSPEND = OsConstantsHolder.CAP_BLOCK_SUSPEND;
    public static final int CAP_CHOWN = OsConstantsHolder.CAP_CHOWN;
    public static final int CAP_DAC_OVERRIDE = OsConstantsHolder.CAP_DAC_OVERRIDE;
    public static final int CAP_DAC_READ_SEARCH = OsConstantsHolder.CAP_DAC_READ_SEARCH;
    public static final int CAP_FOWNER = OsConstantsHolder.CAP_FOWNER;
    public static final int CAP_FSETID = OsConstantsHolder.CAP_FSETID;
    public static final int CAP_IPC_LOCK = OsConstantsHolder.CAP_IPC_LOCK;
    public static final int CAP_IPC_OWNER = OsConstantsHolder.CAP_IPC_OWNER;
    public static final int CAP_KILL = OsConstantsHolder.CAP_KILL;
    public static final int CAP_LAST_CAP = OsConstantsHolder.CAP_LAST_CAP;
    public static final int CAP_LEASE = OsConstantsHolder.CAP_LEASE;
    public static final int CAP_LINUX_IMMUTABLE = OsConstantsHolder.CAP_LINUX_IMMUTABLE;
    public static final int CAP_MAC_ADMIN = OsConstantsHolder.CAP_MAC_ADMIN;
    public static final int CAP_MAC_OVERRIDE = OsConstantsHolder.CAP_MAC_OVERRIDE;
    public static final int CAP_MKNOD = OsConstantsHolder.CAP_MKNOD;
    public static final int CAP_NET_ADMIN = OsConstantsHolder.CAP_NET_ADMIN;
    public static final int CAP_NET_BIND_SERVICE = OsConstantsHolder.CAP_NET_BIND_SERVICE;
    public static final int CAP_NET_BROADCAST = OsConstantsHolder.CAP_NET_BROADCAST;
    public static final int CAP_NET_RAW = OsConstantsHolder.CAP_NET_RAW;
    public static final int CAP_SETFCAP = OsConstantsHolder.CAP_SETFCAP;
    public static final int CAP_SETGID = OsConstantsHolder.CAP_SETGID;
    public static final int CAP_SETPCAP = OsConstantsHolder.CAP_SETPCAP;
    public static final int CAP_SETUID = OsConstantsHolder.CAP_SETUID;
    public static final int CAP_SYS_ADMIN = OsConstantsHolder.CAP_SYS_ADMIN;
    public static final int CAP_SYS_BOOT = OsConstantsHolder.CAP_SYS_BOOT;
    public static final int CAP_SYS_CHROOT = OsConstantsHolder.CAP_SYS_CHROOT;
    public static final int CAP_SYSLOG = OsConstantsHolder.CAP_SYSLOG;
    public static final int CAP_SYS_MODULE = OsConstantsHolder.CAP_SYS_MODULE;
    public static final int CAP_SYS_NICE = OsConstantsHolder.CAP_SYS_NICE;
    public static final int CAP_SYS_PACCT = OsConstantsHolder.CAP_SYS_PACCT;
    public static final int CAP_SYS_PTRACE = OsConstantsHolder.CAP_SYS_PTRACE;
    public static final int CAP_SYS_RAWIO = OsConstantsHolder.CAP_SYS_RAWIO;
    public static final int CAP_SYS_RESOURCE = OsConstantsHolder.CAP_SYS_RESOURCE;
    public static final int CAP_SYS_TIME = OsConstantsHolder.CAP_SYS_TIME;
    public static final int CAP_SYS_TTY_CONFIG = OsConstantsHolder.CAP_SYS_TTY_CONFIG;
    public static final int CAP_WAKE_ALARM = OsConstantsHolder.CAP_WAKE_ALARM;
    public static final int E2BIG = OsConstantsHolder.E2BIG;
    public static final int EACCES = OsConstantsHolder.EACCES;
    public static final int EADDRINUSE = OsConstantsHolder.EADDRINUSE;
    public static final int EADDRNOTAVAIL = OsConstantsHolder.EADDRNOTAVAIL;
    public static final int EAFNOSUPPORT = OsConstantsHolder.EAFNOSUPPORT;
    public static final int EAGAIN = OsConstantsHolder.EAGAIN;
    public static final int EAI_AGAIN = OsConstantsHolder.EAI_AGAIN;
    public static final int EAI_BADFLAGS = OsConstantsHolder.EAI_BADFLAGS;
    public static final int EAI_FAIL = OsConstantsHolder.EAI_FAIL;
    public static final int EAI_FAMILY = OsConstantsHolder.EAI_FAMILY;
    public static final int EAI_MEMORY = OsConstantsHolder.EAI_MEMORY;
    public static final int EAI_NODATA = OsConstantsHolder.EAI_NODATA;
    public static final int EAI_NONAME = OsConstantsHolder.EAI_NONAME;
    public static final int EAI_OVERFLOW = OsConstantsHolder.EAI_OVERFLOW;
    public static final int EAI_SERVICE = OsConstantsHolder.EAI_SERVICE;
    public static final int EAI_SOCKTYPE = OsConstantsHolder.EAI_SOCKTYPE;
    public static final int EAI_SYSTEM = OsConstantsHolder.EAI_SYSTEM;
    public static final int EALREADY = OsConstantsHolder.EALREADY;
    public static final int EBADF = OsConstantsHolder.EBADF;
    public static final int EBADMSG = OsConstantsHolder.EBADMSG;
    public static final int EBUSY = OsConstantsHolder.EBUSY;
    public static final int ECANCELED = OsConstantsHolder.ECANCELED;
    public static final int ECHILD = OsConstantsHolder.ECHILD;
    public static final int ECONNABORTED = OsConstantsHolder.ECONNABORTED;
    public static final int ECONNREFUSED = OsConstantsHolder.ECONNREFUSED;
    public static final int ECONNRESET = OsConstantsHolder.ECONNRESET;
    public static final int EDEADLK = OsConstantsHolder.EDEADLK;
    public static final int EDESTADDRREQ = OsConstantsHolder.EDESTADDRREQ;
    public static final int EDOM = OsConstantsHolder.EDOM;
    public static final int EDQUOT = OsConstantsHolder.EDQUOT;
    public static final int EEXIST = OsConstantsHolder.EEXIST;
    public static final int EFAULT = OsConstantsHolder.EFAULT;
    public static final int EFBIG = OsConstantsHolder.EFBIG;
    public static final int EHOSTUNREACH = OsConstantsHolder.EHOSTUNREACH;
    public static final int EIDRM = OsConstantsHolder.EIDRM;
    public static final int EILSEQ = OsConstantsHolder.EILSEQ;
    public static final int EINPROGRESS = OsConstantsHolder.EINPROGRESS;
    public static final int EINTR = OsConstantsHolder.EINTR;
    public static final int EINVAL = OsConstantsHolder.EINVAL;
    public static final int EIO = OsConstantsHolder.EIO;
    public static final int EISCONN = OsConstantsHolder.EISCONN;
    public static final int EISDIR = OsConstantsHolder.EISDIR;
    public static final int ELOOP = OsConstantsHolder.ELOOP;
    public static final int EMFILE = OsConstantsHolder.EMFILE;
    public static final int EMLINK = OsConstantsHolder.EMLINK;
    public static final int EMSGSIZE = OsConstantsHolder.EMSGSIZE;
    public static final int EMULTIHOP = OsConstantsHolder.EMULTIHOP;
    public static final int ENAMETOOLONG = OsConstantsHolder.ENAMETOOLONG;
    public static final int ENETDOWN = OsConstantsHolder.ENETDOWN;
    public static final int ENETRESET = OsConstantsHolder.ENETRESET;
    public static final int ENETUNREACH = OsConstantsHolder.ENETUNREACH;
    public static final int ENFILE = OsConstantsHolder.ENFILE;
    public static final int ENOBUFS = OsConstantsHolder.ENOBUFS;
    public static final int ENODATA = OsConstantsHolder.ENODATA;
    public static final int ENODEV = OsConstantsHolder.ENODEV;
    public static final int ENOENT = OsConstantsHolder.ENOENT;
    public static final int ENOEXEC = OsConstantsHolder.ENOEXEC;
    public static final int ENOLCK = OsConstantsHolder.ENOLCK;
    public static final int ENOLINK = OsConstantsHolder.ENOLINK;
    public static final int ENOMEM = OsConstantsHolder.ENOMEM;
    public static final int ENOMSG = OsConstantsHolder.ENOMSG;
    public static final int ENONET = OsConstantsHolder.ENONET;
    public static final int ENOPROTOOPT = OsConstantsHolder.ENOPROTOOPT;
    public static final int ENOSPC = OsConstantsHolder.ENOSPC;
    public static final int ENOSR = OsConstantsHolder.ENOSR;
    public static final int ENOSTR = OsConstantsHolder.ENOSTR;
    public static final int ENOSYS = OsConstantsHolder.ENOSYS;
    public static final int ENOTCONN = OsConstantsHolder.ENOTCONN;
    public static final int ENOTDIR = OsConstantsHolder.ENOTDIR;
    public static final int ENOTEMPTY = OsConstantsHolder.ENOTEMPTY;
    public static final int ENOTSOCK = OsConstantsHolder.ENOTSOCK;
    public static final int ENOTSUP = OsConstantsHolder.ENOTSUP;
    public static final int ENOTTY = OsConstantsHolder.ENOTTY;
    public static final int ENXIO = OsConstantsHolder.ENXIO;
    public static final int EOPNOTSUPP = OsConstantsHolder.EOPNOTSUPP;
    public static final int EOVERFLOW = OsConstantsHolder.EOVERFLOW;
    public static final int EPERM = OsConstantsHolder.EPERM;
    public static final int EPIPE = OsConstantsHolder.EPIPE;
    public static final int EPROTO = OsConstantsHolder.EPROTO;
    public static final int EPROTONOSUPPORT = OsConstantsHolder.EPROTONOSUPPORT;
    public static final int EPROTOTYPE = OsConstantsHolder.EPROTOTYPE;
    public static final int ERANGE = OsConstantsHolder.ERANGE;
    public static final int EROFS = OsConstantsHolder.EROFS;
    public static final int ESPIPE = OsConstantsHolder.ESPIPE;
    public static final int ESRCH = OsConstantsHolder.ESRCH;
    public static final int ESTALE = OsConstantsHolder.ESTALE;
    public static final int ETH_P_ALL = OsConstantsHolder.ETH_P_ALL;
    public static final int ETH_P_ARP = OsConstantsHolder.ETH_P_ARP;
    public static final int ETH_P_IP = OsConstantsHolder.ETH_P_IP;
    public static final int ETH_P_IPV6 = OsConstantsHolder.ETH_P_IPV6;
    public static final int ETIME = OsConstantsHolder.ETIME;
    public static final int ETIMEDOUT = OsConstantsHolder.ETIMEDOUT;
    public static final int ETXTBSY = OsConstantsHolder.ETXTBSY;
    /**
     * "Too many users" error.
     * See <a href="https://man7.org/linux/man-pages/man3/errno.3.html">errno(3)</a>.
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    public static final int EUSERS = OsConstantsHolder.EUSERS;
    // On Linux, EWOULDBLOCK == EAGAIN. Use EAGAIN instead, to reduce confusion.
    public static final int EXDEV = OsConstantsHolder.EXDEV;
    public static final int EXIT_FAILURE = OsConstantsHolder.EXIT_FAILURE;
    public static final int EXIT_SUCCESS = OsConstantsHolder.EXIT_SUCCESS;
    public static final int FD_CLOEXEC = OsConstantsHolder.FD_CLOEXEC;
    public static final int FIONREAD = OsConstantsHolder.FIONREAD;
    public static final int F_DUPFD = OsConstantsHolder.F_DUPFD;
    public static final int F_DUPFD_CLOEXEC = OsConstantsHolder.F_DUPFD_CLOEXEC;
    public static final int F_GETFD = OsConstantsHolder.F_GETFD;
    public static final int F_GETFL = OsConstantsHolder.F_GETFL;
    public static final int F_GETLK = OsConstantsHolder.F_GETLK;
    public static final int F_GETLK64 = OsConstantsHolder.F_GETLK64;
    public static final int F_GETOWN = OsConstantsHolder.F_GETOWN;
    public static final int F_OK = OsConstantsHolder.F_OK;
    public static final int F_RDLCK = OsConstantsHolder.F_RDLCK;
    public static final int F_SETFD = OsConstantsHolder.F_SETFD;
    public static final int F_SETFL = OsConstantsHolder.F_SETFL;
    public static final int F_SETLK = OsConstantsHolder.F_SETLK;
    public static final int F_SETLK64 = OsConstantsHolder.F_SETLK64;
    public static final int F_SETLKW = OsConstantsHolder.F_SETLKW;
    public static final int F_SETLKW64 = OsConstantsHolder.F_SETLKW64;
    public static final int F_SETOWN = OsConstantsHolder.F_SETOWN;
    public static final int F_UNLCK = OsConstantsHolder.F_UNLCK;
    public static final int F_WRLCK = OsConstantsHolder.F_WRLCK;
    public static final int ICMP_ECHO = OsConstantsHolder.ICMP_ECHO;
    public static final int ICMP_ECHOREPLY = OsConstantsHolder.ICMP_ECHOREPLY;
    public static final int ICMP6_ECHO_REQUEST = OsConstantsHolder.ICMP6_ECHO_REQUEST;
    public static final int ICMP6_ECHO_REPLY = OsConstantsHolder.ICMP6_ECHO_REPLY;
    public static final int IFA_F_DADFAILED = OsConstantsHolder.IFA_F_DADFAILED;
    public static final int IFA_F_DEPRECATED = OsConstantsHolder.IFA_F_DEPRECATED;
    public static final int IFA_F_HOMEADDRESS = OsConstantsHolder.IFA_F_HOMEADDRESS;
    public static final int IFA_F_MANAGETEMPADDR = OsConstantsHolder.IFA_F_MANAGETEMPADDR;
    public static final int IFA_F_NODAD = OsConstantsHolder.IFA_F_NODAD;
    public static final int IFA_F_NOPREFIXROUTE = OsConstantsHolder.IFA_F_NOPREFIXROUTE;
    public static final int IFA_F_OPTIMISTIC = OsConstantsHolder.IFA_F_OPTIMISTIC;
    public static final int IFA_F_PERMANENT = OsConstantsHolder.IFA_F_PERMANENT;
    public static final int IFA_F_SECONDARY = OsConstantsHolder.IFA_F_SECONDARY;
    public static final int IFA_F_TEMPORARY = OsConstantsHolder.IFA_F_TEMPORARY;
    public static final int IFA_F_TENTATIVE = OsConstantsHolder.IFA_F_TENTATIVE;
    public static final int IFF_ALLMULTI = OsConstantsHolder.IFF_ALLMULTI;
    public static final int IFF_AUTOMEDIA = OsConstantsHolder.IFF_AUTOMEDIA;
    public static final int IFF_BROADCAST = OsConstantsHolder.IFF_BROADCAST;
    public static final int IFF_DEBUG = OsConstantsHolder.IFF_DEBUG;
    public static final int IFF_DYNAMIC = OsConstantsHolder.IFF_DYNAMIC;
    public static final int IFF_LOOPBACK = OsConstantsHolder.IFF_LOOPBACK;
    public static final int IFF_MASTER = OsConstantsHolder.IFF_MASTER;
    public static final int IFF_MULTICAST = OsConstantsHolder.IFF_MULTICAST;
    public static final int IFF_NOARP = OsConstantsHolder.IFF_NOARP;
    public static final int IFF_NOTRAILERS = OsConstantsHolder.IFF_NOTRAILERS;
    public static final int IFF_POINTOPOINT = OsConstantsHolder.IFF_POINTOPOINT;
    public static final int IFF_PORTSEL = OsConstantsHolder.IFF_PORTSEL;
    public static final int IFF_PROMISC = OsConstantsHolder.IFF_PROMISC;
    public static final int IFF_RUNNING = OsConstantsHolder.IFF_RUNNING;
    public static final int IFF_SLAVE = OsConstantsHolder.IFF_SLAVE;
    public static final int IFF_UP = OsConstantsHolder.IFF_UP;
    public static final int IPPROTO_ICMP = OsConstantsHolder.IPPROTO_ICMP;
    public static final int IPPROTO_ICMPV6 = OsConstantsHolder.IPPROTO_ICMPV6;
    public static final int IPPROTO_IP = OsConstantsHolder.IPPROTO_IP;
    public static final int IPPROTO_IPV6 = OsConstantsHolder.IPPROTO_IPV6;
    public static final int IPPROTO_RAW = OsConstantsHolder.IPPROTO_RAW;
    public static final int IPPROTO_TCP = OsConstantsHolder.IPPROTO_TCP;
    public static final int IPPROTO_UDP = OsConstantsHolder.IPPROTO_UDP;

    /**
     * Encapsulation Security Payload protocol
     *
     * <p>Defined in /uapi/linux/in.h
     */
    public static final int IPPROTO_ESP = OsConstantsHolder.IPPROTO_ESP;

    public static final int IPV6_CHECKSUM = OsConstantsHolder.IPV6_CHECKSUM;
    public static final int IPV6_MULTICAST_HOPS = OsConstantsHolder.IPV6_MULTICAST_HOPS;
    public static final int IPV6_MULTICAST_IF = OsConstantsHolder.IPV6_MULTICAST_IF;
    public static final int IPV6_MULTICAST_LOOP = OsConstantsHolder.IPV6_MULTICAST_LOOP;
    public static final int IPV6_PKTINFO = OsConstantsHolder.IPV6_PKTINFO;
    public static final int IPV6_RECVDSTOPTS = OsConstantsHolder.IPV6_RECVDSTOPTS;
    public static final int IPV6_RECVHOPLIMIT = OsConstantsHolder.IPV6_RECVHOPLIMIT;
    public static final int IPV6_RECVHOPOPTS = OsConstantsHolder.IPV6_RECVHOPOPTS;
    public static final int IPV6_RECVPKTINFO = OsConstantsHolder.IPV6_RECVPKTINFO;
    public static final int IPV6_RECVRTHDR = OsConstantsHolder.IPV6_RECVRTHDR;
    public static final int IPV6_RECVTCLASS = OsConstantsHolder.IPV6_RECVTCLASS;
    public static final int IPV6_TCLASS = OsConstantsHolder.IPV6_TCLASS;
    public static final int IPV6_UNICAST_HOPS = OsConstantsHolder.IPV6_UNICAST_HOPS;
    public static final int IPV6_V6ONLY = OsConstantsHolder.IPV6_V6ONLY;
    /** @hide */
    @UnsupportedAppUsage
    public static final int IP_MULTICAST_ALL = OsConstantsHolder.IP_MULTICAST_ALL;
    public static final int IP_MULTICAST_IF = OsConstantsHolder.IP_MULTICAST_IF;
    public static final int IP_MULTICAST_LOOP = OsConstantsHolder.IP_MULTICAST_LOOP;
    public static final int IP_MULTICAST_TTL = OsConstantsHolder.IP_MULTICAST_TTL;
    /** @hide */
    @UnsupportedAppUsage
    public static final int IP_RECVTOS = OsConstantsHolder.IP_RECVTOS;
    public static final int IP_TOS = OsConstantsHolder.IP_TOS;
    public static final int IP_TTL = OsConstantsHolder.IP_TTL;

    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_NORMAL = OsConstantsHolder.MADV_NORMAL;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_RANDOM = OsConstantsHolder.MADV_RANDOM;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_SEQUENTIAL = OsConstantsHolder.MADV_SEQUENTIAL;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_WILLNEED = OsConstantsHolder.MADV_WILLNEED;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_DONTNEED = OsConstantsHolder.MADV_DONTNEED;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_REMOVE = OsConstantsHolder.MADV_REMOVE;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_DONTFORK = OsConstantsHolder.MADV_DONTFORK;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_DOFORK = OsConstantsHolder.MADV_DOFORK;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_HWPOISON = OsConstantsHolder.MADV_HWPOISON;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_MERGEABLE = OsConstantsHolder.MADV_MERGEABLE;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_UNMERGEABLE = OsConstantsHolder.MADV_UNMERGEABLE;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_SOFT_OFFLINE = OsConstantsHolder.MADV_SOFT_OFFLINE;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_HUGEPAGE = OsConstantsHolder.MADV_HUGEPAGE;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_NOHUGEPAGE = OsConstantsHolder.MADV_NOHUGEPAGE;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_COLLAPSE = OsConstantsHolder.MADV_COLLAPSE;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_DONTDUMP = OsConstantsHolder.MADV_DONTDUMP;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_DODUMP = OsConstantsHolder.MADV_DODUMP;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_FREE = OsConstantsHolder.MADV_FREE;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_WIPEONFORK = OsConstantsHolder.MADV_WIPEONFORK;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_KEEPONFORK = OsConstantsHolder.MADV_KEEPONFORK;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_COLD = OsConstantsHolder.MADV_COLD;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_PAGEOUT = OsConstantsHolder.MADV_PAGEOUT;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_POPULATE_READ = OsConstantsHolder.MADV_POPULATE_READ;
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_MADVISE_API)
    public static final int MADV_POPULATE_WRITE = OsConstantsHolder.MADV_POPULATE_WRITE;

    /**
     * Version constant to be used in {@link StructCapUserHeader} with
     * {@link Os#capset(StructCapUserHeader, StructCapUserData[])} and
     * {@link Os#capget(StructCapUserHeader)}.
     *
     * See <a href="https://man7.org/linux/man-pages/man2/capget.2.html">capget(2)</a>.
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    public static final int _LINUX_CAPABILITY_VERSION_3 = OsConstantsHolder._LINUX_CAPABILITY_VERSION_3;
    public static final int MAP_FIXED = OsConstantsHolder.MAP_FIXED;
    public static final int MAP_ANONYMOUS = OsConstantsHolder.MAP_ANONYMOUS;
    /**
     * Flag argument for {@code mmap(long, long, int, int, FileDescriptor, long)}.
     *
     * See <a href="http://man7.org/linux/man-pages/man2/mmap.2.html">mmap(2)</a>.
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    public static final int MAP_POPULATE = OsConstantsHolder.MAP_POPULATE;
    public static final int MAP_PRIVATE = OsConstantsHolder.MAP_PRIVATE;
    public static final int MAP_SHARED = OsConstantsHolder.MAP_SHARED;
    public static final int MCAST_JOIN_GROUP = OsConstantsHolder.MCAST_JOIN_GROUP;
    public static final int MCAST_LEAVE_GROUP = OsConstantsHolder.MCAST_LEAVE_GROUP;
    public static final int MCAST_JOIN_SOURCE_GROUP = OsConstantsHolder.MCAST_JOIN_SOURCE_GROUP;
    public static final int MCAST_LEAVE_SOURCE_GROUP = OsConstantsHolder.MCAST_LEAVE_SOURCE_GROUP;
    public static final int MCAST_BLOCK_SOURCE = OsConstantsHolder.MCAST_BLOCK_SOURCE;
    public static final int MCAST_UNBLOCK_SOURCE = OsConstantsHolder.MCAST_UNBLOCK_SOURCE;
    public static final int MCL_CURRENT = OsConstantsHolder.MCL_CURRENT;
    public static final int MCL_FUTURE = OsConstantsHolder.MCL_FUTURE;
    public static final int MFD_CLOEXEC = OsConstantsHolder.MFD_CLOEXEC;
    public static final int MSG_CTRUNC = OsConstantsHolder.MSG_CTRUNC;
    public static final int MSG_DONTROUTE = OsConstantsHolder.MSG_DONTROUTE;
    public static final int MSG_EOR = OsConstantsHolder.MSG_EOR;
    public static final int MSG_OOB = OsConstantsHolder.MSG_OOB;
    public static final int MSG_PEEK = OsConstantsHolder.MSG_PEEK;
    public static final int MSG_TRUNC = OsConstantsHolder.MSG_TRUNC;
    public static final int MSG_WAITALL = OsConstantsHolder.MSG_WAITALL;
    public static final int MS_ASYNC = OsConstantsHolder.MS_ASYNC;
    public static final int MS_INVALIDATE = OsConstantsHolder.MS_INVALIDATE;
    public static final int MS_SYNC = OsConstantsHolder.MS_SYNC;
    public static final int NETLINK_NETFILTER = OsConstantsHolder.NETLINK_NETFILTER;
    public static final int NETLINK_ROUTE = OsConstantsHolder.NETLINK_ROUTE;
    /**
     * SELinux enforces that only system_server and netd may use this netlink socket type.
     */
    public static final int NETLINK_INET_DIAG = OsConstantsHolder.NETLINK_INET_DIAG;

    /**
     * SELinux enforces that only system_server and netd may use this netlink socket type.
     *
     * @see <a href="https://man7.org/linux/man-pages/man7/netlink.7.html">netlink(7)</a>
     */
    public static final int NETLINK_XFRM = OsConstantsHolder.NETLINK_XFRM;

    public static final int NI_DGRAM = OsConstantsHolder.NI_DGRAM;
    public static final int NI_NAMEREQD = OsConstantsHolder.NI_NAMEREQD;
    public static final int NI_NOFQDN = OsConstantsHolder.NI_NOFQDN;
    public static final int NI_NUMERICHOST = OsConstantsHolder.NI_NUMERICHOST;
    public static final int NI_NUMERICSERV = OsConstantsHolder.NI_NUMERICSERV;
    public static final int O_ACCMODE = OsConstantsHolder.O_ACCMODE;
    public static final int O_APPEND = OsConstantsHolder.O_APPEND;
    public static final int O_CLOEXEC = OsConstantsHolder.O_CLOEXEC;
    public static final int O_CREAT = OsConstantsHolder.O_CREAT;
    /**
     * Flag for {@code Os#open(String, int, int)}.
     *
     * When enabled, tries to minimize cache effects of the I/O to and from this
     * file. In general this will degrade performance, but it is
     * useful in special situations, such as when applications do
     * their own caching. File I/O is done directly to/from
     * user-space buffers. The {@link O_DIRECT} flag on its own makes an
     * effort to transfer data synchronously, but does not give
     * the guarantees of the {@link O_SYNC} flag that data and necessary
     * metadata are transferred. To guarantee synchronous I/O,
     * {@link O_SYNC} must be used in addition to {@link O_DIRECT}.
     *
     * See <a href="https://man7.org/linux/man-pages/man2/open.2.html">open(2)</a>.
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    public static final int O_DIRECT = OsConstantsHolder.O_DIRECT;
    public static final int O_EXCL = OsConstantsHolder.O_EXCL;
    public static final int O_NOCTTY = OsConstantsHolder.O_NOCTTY;
    public static final int O_NOFOLLOW = OsConstantsHolder.O_NOFOLLOW;
    public static final int O_NONBLOCK = OsConstantsHolder.O_NONBLOCK;
    public static final int O_RDONLY = OsConstantsHolder.O_RDONLY;
    public static final int O_RDWR = OsConstantsHolder.O_RDWR;
    public static final int O_SYNC = OsConstantsHolder.O_SYNC;
    public static final int O_DSYNC = OsConstantsHolder.O_DSYNC;
    public static final int O_TRUNC = OsConstantsHolder.O_TRUNC;
    public static final int O_WRONLY = OsConstantsHolder.O_WRONLY;
    public static final int POLLERR = OsConstantsHolder.POLLERR;
    public static final int POLLHUP = OsConstantsHolder.POLLHUP;
    public static final int POLLIN = OsConstantsHolder.POLLIN;
    public static final int POLLNVAL = OsConstantsHolder.POLLNVAL;
    public static final int POLLOUT = OsConstantsHolder.POLLOUT;
    public static final int POLLPRI = OsConstantsHolder.POLLPRI;
    public static final int POLLRDBAND = OsConstantsHolder.POLLRDBAND;
    public static final int POLLRDNORM = OsConstantsHolder.POLLRDNORM;
    public static final int POLLWRBAND = OsConstantsHolder.POLLWRBAND;
    public static final int POLLWRNORM = OsConstantsHolder.POLLWRNORM;
    /**
     * Reads or changes the ambient capability set of the calling thread.
     * Has to be used as a first argument for {@link Os#prctl(int, long, long, long, long)}.
     *
     * See <a href="https://man7.org/linux/man-pages/man2/prctl.2.html">prctl(2)</a>.
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    public static final int PR_CAP_AMBIENT = OsConstantsHolder.PR_CAP_AMBIENT;
    /**
     * The capability specified in {@code arg3} of {@link Os#prctl(int, long, long, long, long)}
     * is added to the ambient set. The specified capability must already
     * be present in both the permitted and the inheritable sets of the process.
     * Has to be used as a second argument for {@link Os#prctl(int, long, long, long, long)}.
     *
     * See <a href="https://man7.org/linux/man-pages/man2/prctl.2.html">prctl(2)</a>.
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    public static final int PR_CAP_AMBIENT_RAISE = OsConstantsHolder.PR_CAP_AMBIENT_RAISE;
    public static final int PR_GET_DUMPABLE = OsConstantsHolder.PR_GET_DUMPABLE;
    public static final int PR_SET_DUMPABLE = OsConstantsHolder.PR_SET_DUMPABLE;
    public static final int PR_SET_NO_NEW_PRIVS = OsConstantsHolder.PR_SET_NO_NEW_PRIVS;
    public static final int PROT_EXEC = OsConstantsHolder.PROT_EXEC;
    public static final int PROT_NONE = OsConstantsHolder.PROT_NONE;
    public static final int PROT_READ = OsConstantsHolder.PROT_READ;
    public static final int PROT_WRITE = OsConstantsHolder.PROT_WRITE;
    public static final int R_OK = OsConstantsHolder.R_OK;
    /**
     * Specifies a value one greater than the maximum file
     * descriptor number that can be opened by this process.
     *
     * <p>Attempts ({@link Os#open(String, int, int)}, {@link Os#pipe()},
     * {@link Os#dup(java.io.FileDescriptor)}, etc.) to exceed this
     * limit yield the error {@link EMFILE}.
     *
     * See <a href="https://man7.org/linux/man-pages/man3/vlimit.3.html">getrlimit(2)</a>.
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    public static final int RLIMIT_NOFILE = OsConstantsHolder.RLIMIT_NOFILE;
    /** @hide */
    @android.annotation.FlaggedApi(com.android.libcore.Flags.FLAG_OPENJDK_21_V1_APIS)
    @SystemApi(client = MODULE_LIBRARIES)
    public static final int RLIMIT_RTPRIO = OsConstantsHolder.RLIMIT_RTPRIO;
    public static final int RT_SCOPE_HOST = OsConstantsHolder.RT_SCOPE_HOST;
    public static final int RT_SCOPE_LINK = OsConstantsHolder.RT_SCOPE_LINK;
    public static final int RT_SCOPE_NOWHERE = OsConstantsHolder.RT_SCOPE_NOWHERE;
    public static final int RT_SCOPE_SITE = OsConstantsHolder.RT_SCOPE_SITE;
    public static final int RT_SCOPE_UNIVERSE = OsConstantsHolder.RT_SCOPE_UNIVERSE;
    /**
     * Bitmask for IPv4 addresses add/delete events multicast groups mask.
     * Used in {@link NetlinkSocketAddress}.
     *
     * See <a href="https://man7.org/linux/man-pages/man7/netlink.7.html">netlink(7)</a>.
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    public static final int RTMGRP_IPV4_IFADDR = OsConstantsHolder.RTMGRP_IPV4_IFADDR;
    /** @hide */
    @UnsupportedAppUsage
    public static final int RTMGRP_IPV4_MROUTE = OsConstantsHolder.RTMGRP_IPV4_MROUTE;
    /** @hide */
    @UnsupportedAppUsage
    public static final int RTMGRP_IPV4_ROUTE = OsConstantsHolder.RTMGRP_IPV4_ROUTE;
    /** @hide */
    @UnsupportedAppUsage
    public static final int RTMGRP_IPV4_RULE = OsConstantsHolder.RTMGRP_IPV4_RULE;
    /** @hide */
    @UnsupportedAppUsage
    public static final int RTMGRP_IPV6_IFADDR = OsConstantsHolder.RTMGRP_IPV6_IFADDR;
    /** @hide */
    @UnsupportedAppUsage
    public static final int RTMGRP_IPV6_IFINFO = OsConstantsHolder.RTMGRP_IPV6_IFINFO;
    /** @hide */
    @UnsupportedAppUsage
    public static final int RTMGRP_IPV6_MROUTE = OsConstantsHolder.RTMGRP_IPV6_MROUTE;
    /** @hide */
    @UnsupportedAppUsage
    public static final int RTMGRP_IPV6_PREFIX = OsConstantsHolder.RTMGRP_IPV6_PREFIX;
    /** @hide */
    @UnsupportedAppUsage
    public static final int RTMGRP_IPV6_ROUTE = OsConstantsHolder.RTMGRP_IPV6_ROUTE;
    /** @hide */
    @UnsupportedAppUsage
    public static final int RTMGRP_LINK = OsConstantsHolder.RTMGRP_LINK;
    public static final int RTMGRP_NEIGH = OsConstantsHolder.RTMGRP_NEIGH;
    /** @hide */
    @UnsupportedAppUsage
    public static final int RTMGRP_NOTIFY = OsConstantsHolder.RTMGRP_NOTIFY;
    /** @hide */
    @UnsupportedAppUsage
    public static final int RTMGRP_TC = OsConstantsHolder.RTMGRP_TC;
    public static final int SEEK_CUR = OsConstantsHolder.SEEK_CUR;
    public static final int SEEK_END = OsConstantsHolder.SEEK_END;
    public static final int SEEK_SET = OsConstantsHolder.SEEK_SET;
    public static final int SHUT_RD = OsConstantsHolder.SHUT_RD;
    public static final int SHUT_RDWR = OsConstantsHolder.SHUT_RDWR;
    public static final int SHUT_WR = OsConstantsHolder.SHUT_WR;
    public static final int SIGABRT = OsConstantsHolder.SIGABRT;
    public static final int SIGALRM = OsConstantsHolder.SIGALRM;
    public static final int SIGBUS = OsConstantsHolder.SIGBUS;
    public static final int SIGCHLD = OsConstantsHolder.SIGCHLD;
    public static final int SIGCONT = OsConstantsHolder.SIGCONT;
    public static final int SIGFPE = OsConstantsHolder.SIGFPE;
    public static final int SIGHUP = OsConstantsHolder.SIGHUP;
    public static final int SIGILL = OsConstantsHolder.SIGILL;
    public static final int SIGINT = OsConstantsHolder.SIGINT;
    public static final int SIGIO = OsConstantsHolder.SIGIO;
    public static final int SIGKILL = OsConstantsHolder.SIGKILL;
    public static final int SIGPIPE = OsConstantsHolder.SIGPIPE;
    public static final int SIGPROF = OsConstantsHolder.SIGPROF;
    public static final int SIGPWR = OsConstantsHolder.SIGPWR;
    public static final int SIGQUIT = OsConstantsHolder.SIGQUIT;
    public static final int SIGRTMAX = OsConstantsHolder.SIGRTMAX;
    public static final int SIGRTMIN = OsConstantsHolder.SIGRTMIN;
    public static final int SIGSEGV = OsConstantsHolder.SIGSEGV;
    public static final int SIGSTKFLT = OsConstantsHolder.SIGSTKFLT;
    public static final int SIGSTOP = OsConstantsHolder.SIGSTOP;
    public static final int SIGSYS = OsConstantsHolder.SIGSYS;
    public static final int SIGTERM = OsConstantsHolder.SIGTERM;
    public static final int SIGTRAP = OsConstantsHolder.SIGTRAP;
    public static final int SIGTSTP = OsConstantsHolder.SIGTSTP;
    public static final int SIGTTIN = OsConstantsHolder.SIGTTIN;
    public static final int SIGTTOU = OsConstantsHolder.SIGTTOU;
    public static final int SIGURG = OsConstantsHolder.SIGURG;
    public static final int SIGUSR1 = OsConstantsHolder.SIGUSR1;
    public static final int SIGUSR2 = OsConstantsHolder.SIGUSR2;
    public static final int SIGVTALRM = OsConstantsHolder.SIGVTALRM;
    public static final int SIGWINCH = OsConstantsHolder.SIGWINCH;
    public static final int SIGXCPU = OsConstantsHolder.SIGXCPU;
    public static final int SIGXFSZ = OsConstantsHolder.SIGXFSZ;
    public static final int SIOCGIFADDR = OsConstantsHolder.SIOCGIFADDR;
    public static final int SIOCGIFBRDADDR = OsConstantsHolder.SIOCGIFBRDADDR;
    public static final int SIOCGIFDSTADDR = OsConstantsHolder.SIOCGIFDSTADDR;
    public static final int SIOCGIFNETMASK = OsConstantsHolder.SIOCGIFNETMASK;

    /**
     * Set the close-on-exec ({@code FD_CLOEXEC}) flag on the new file
     * descriptor created by {@link Os#socket(int,int,int)} or
     * {@link Os#socketpair(int,int,int,java.io.FileDescriptor,java.io.FileDescriptor)}.
     * See the description of the O_CLOEXEC flag in
     * <a href="http://man7.org/linux/man-pages/man2/open.2.html">open(2)</a>
     * for reasons why this may be useful.
     *
     * <p>Applications wishing to make use of this flag on older API versions
     * may use {@link #O_CLOEXEC} instead. On Android, {@code O_CLOEXEC} and
     * {@code SOCK_CLOEXEC} are the same value.
     */
    public static final int SOCK_CLOEXEC = OsConstantsHolder.SOCK_CLOEXEC;
    public static final int SOCK_DGRAM = OsConstantsHolder.SOCK_DGRAM;

    /**
     * Set the O_NONBLOCK file status flag on the file descriptor
     * created by {@link Os#socket(int,int,int)} or
     * {@link Os#socketpair(int,int,int,java.io.FileDescriptor,java.io.FileDescriptor)}.
     *
     * <p>Applications wishing to make use of this flag on older API versions
     * may use {@link #O_NONBLOCK} instead. On Android, {@code O_NONBLOCK}
     * and {@code SOCK_NONBLOCK} are the same value.
     */
    public static final int SOCK_NONBLOCK = OsConstantsHolder.SOCK_NONBLOCK;
    public static final int SOCK_RAW = OsConstantsHolder.SOCK_RAW;
    public static final int SOCK_SEQPACKET = OsConstantsHolder.SOCK_SEQPACKET;
    public static final int SOCK_STREAM = OsConstantsHolder.SOCK_STREAM;
    public static final int SOL_SOCKET = OsConstantsHolder.SOL_SOCKET;
    public static final int SOL_UDP = OsConstantsHolder.SOL_UDP;
    public static final int SOL_PACKET = OsConstantsHolder.SOL_PACKET;
    public static final int SO_BINDTODEVICE = OsConstantsHolder.SO_BINDTODEVICE;
    public static final int SO_BROADCAST = OsConstantsHolder.SO_BROADCAST;
    public static final int SO_DEBUG = OsConstantsHolder.SO_DEBUG;
    /** @hide */
    @UnsupportedAppUsage
    public static final int SO_DOMAIN = OsConstantsHolder.SO_DOMAIN;
    public static final int SO_DONTROUTE = OsConstantsHolder.SO_DONTROUTE;
    public static final int SO_ERROR = OsConstantsHolder.SO_ERROR;
    public static final int SO_KEEPALIVE = OsConstantsHolder.SO_KEEPALIVE;
    public static final int SO_LINGER = OsConstantsHolder.SO_LINGER;
    public static final int SO_OOBINLINE = OsConstantsHolder.SO_OOBINLINE;
    public static final int SO_PASSCRED = OsConstantsHolder.SO_PASSCRED;
    public static final int SO_PEERCRED = OsConstantsHolder.SO_PEERCRED;
    /** @hide */
    @UnsupportedAppUsage
    public static final int SO_PROTOCOL = OsConstantsHolder.SO_PROTOCOL;
    public static final int SO_RCVBUF = OsConstantsHolder.SO_RCVBUF;
    public static final int SO_RCVLOWAT = OsConstantsHolder.SO_RCVLOWAT;
    public static final int SO_RCVTIMEO = OsConstantsHolder.SO_RCVTIMEO;
    public static final int SO_REUSEADDR = OsConstantsHolder.SO_REUSEADDR;
    public static final int SO_SNDBUF = OsConstantsHolder.SO_SNDBUF;
    public static final int SO_SNDLOWAT = OsConstantsHolder.SO_SNDLOWAT;
    public static final int SO_SNDTIMEO = OsConstantsHolder.SO_SNDTIMEO;
    public static final int SO_TYPE = OsConstantsHolder.SO_TYPE;
    public static final int PACKET_IGNORE_OUTGOING = OsConstantsHolder.PACKET_IGNORE_OUTGOING;
    /**
     * Bitmask for flags argument of
     * {@link splice(java.io.FileDescriptor, Int64Ref, FileDescriptor, Int64Ref, long, int)}.
     *
     * Attempt to move pages instead of copying.  This is only a
     * hint to the kernel: pages may still be copied if the
     * kernel cannot move the pages from the pipe, or if the pipe
     * buffers don't refer to full pages.
     *
     * See <a href="https://man7.org/linux/man-pages/man2/splice.2.html">splice(2)</a>.
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    public static final int SPLICE_F_MOVE = OsConstantsHolder.SPLICE_F_MOVE;
    /** @hide */
    @UnsupportedAppUsage
    public static final int SPLICE_F_NONBLOCK = OsConstantsHolder.SPLICE_F_NONBLOCK;
    /**
     * Bitmask for flags argument of
     * {@link splice(java.io.FileDescriptor, Int64Ref, FileDescriptor, Int64Ref, long, int)}.
     *
     * <p>Indicates that more data will be coming in a subsequent splice. This is
     * a helpful hint when the {@code fdOut} refers to a socket.
     *
     * See <a href="https://man7.org/linux/man-pages/man2/splice.2.html">splice(2)</a>.
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    public static final int SPLICE_F_MORE = OsConstantsHolder.SPLICE_F_MORE;
    public static final int STDERR_FILENO = OsConstantsHolder.STDERR_FILENO;
    public static final int STDIN_FILENO = OsConstantsHolder.STDIN_FILENO;
    public static final int STDOUT_FILENO = OsConstantsHolder.STDOUT_FILENO;
    public static final int ST_MANDLOCK = OsConstantsHolder.ST_MANDLOCK;
    public static final int ST_NOATIME = OsConstantsHolder.ST_NOATIME;
    public static final int ST_NODEV = OsConstantsHolder.ST_NODEV;
    public static final int ST_NODIRATIME = OsConstantsHolder.ST_NODIRATIME;
    public static final int ST_NOEXEC = OsConstantsHolder.ST_NOEXEC;
    public static final int ST_NOSUID = OsConstantsHolder.ST_NOSUID;
    public static final int ST_RDONLY = OsConstantsHolder.ST_RDONLY;
    public static final int ST_RELATIME = OsConstantsHolder.ST_RELATIME;
    public static final int ST_SYNCHRONOUS = OsConstantsHolder.ST_SYNCHRONOUS;
    public static final int S_IFBLK = OsConstantsHolder.S_IFBLK;
    public static final int S_IFCHR = OsConstantsHolder.S_IFCHR;
    public static final int S_IFDIR = OsConstantsHolder.S_IFDIR;
    public static final int S_IFIFO = OsConstantsHolder.S_IFIFO;
    public static final int S_IFLNK = OsConstantsHolder.S_IFLNK;
    public static final int S_IFMT = OsConstantsHolder.S_IFMT;
    public static final int S_IFREG = OsConstantsHolder.S_IFREG;
    public static final int S_IFSOCK = OsConstantsHolder.S_IFSOCK;
    public static final int S_IRGRP = OsConstantsHolder.S_IRGRP;
    public static final int S_IROTH = OsConstantsHolder.S_IROTH;
    public static final int S_IRUSR = OsConstantsHolder.S_IRUSR;
    public static final int S_IRWXG = OsConstantsHolder.S_IRWXG;
    public static final int S_IRWXO = OsConstantsHolder.S_IRWXO;
    public static final int S_IRWXU = OsConstantsHolder.S_IRWXU;
    public static final int S_ISGID = OsConstantsHolder.S_ISGID;
    public static final int S_ISUID = OsConstantsHolder.S_ISUID;
    public static final int S_ISVTX = OsConstantsHolder.S_ISVTX;
    public static final int S_IWGRP = OsConstantsHolder.S_IWGRP;
    public static final int S_IWOTH = OsConstantsHolder.S_IWOTH;
    public static final int S_IWUSR = OsConstantsHolder.S_IWUSR;
    public static final int S_IXGRP = OsConstantsHolder.S_IXGRP;
    public static final int S_IXOTH = OsConstantsHolder.S_IXOTH;
    public static final int S_IXUSR = OsConstantsHolder.S_IXUSR;
    public static final int TCP_NODELAY = OsConstantsHolder.TCP_NODELAY;
    public static final int TCP_USER_TIMEOUT = OsConstantsHolder.TCP_USER_TIMEOUT;
    public static final int UDP_GRO = OsConstantsHolder.UDP_GRO;
    public static final int UDP_SEGMENT = OsConstantsHolder.UDP_SEGMENT;
    /**
     * Get the number of bytes in the output buffer.
     *
     * See <a href="https://man7.org/linux/man-pages/man2/ioctl.2.html">ioctl(2)</a>.
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    public static final int TIOCOUTQ = OsConstantsHolder.TIOCOUTQ;
    /**
     * Sockopt option to encapsulate ESP packets in UDP.
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    public static final int UDP_ENCAP = OsConstantsHolder.UDP_ENCAP;
    /** @hide */
    @UnsupportedAppUsage
    public static final int UDP_ENCAP_ESPINUDP_NON_IKE = OsConstantsHolder.UDP_ENCAP_ESPINUDP_NON_IKE;
    /** @hide */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    public static final int UDP_ENCAP_ESPINUDP = OsConstantsHolder.UDP_ENCAP_ESPINUDP;
    /** @hide */
    @UnsupportedAppUsage
    public static final int UNIX_PATH_MAX = OsConstantsHolder.UNIX_PATH_MAX;
    public static final int WCONTINUED = OsConstantsHolder.WCONTINUED;
    public static final int WEXITED = OsConstantsHolder.WEXITED;
    public static final int WNOHANG = OsConstantsHolder.WNOHANG;
    public static final int WNOWAIT = OsConstantsHolder.WNOWAIT;
    public static final int WSTOPPED = OsConstantsHolder.WSTOPPED;
    public static final int WUNTRACED = OsConstantsHolder.WUNTRACED;
    public static final int W_OK = OsConstantsHolder.W_OK;
    /**
     * {@code flags} option for {@link Os#setxattr(String, String, byte[], int)}.
     *
     * <p>Performs a pure create, which fails if the named attribute exists already.
     *
     * See <a href="http://man7.org/linux/man-pages/man2/setxattr.2.html">setxattr(2)</a>.
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    public static final int XATTR_CREATE = OsConstantsHolder.XATTR_CREATE;
    /**
     * {@code flags} option for {@link Os#setxattr(String, String, byte[], int)}.
     *
     * <p>Perform a pure replace operation, which fails if the named attribute
     * does not already exist.
     *
     * See <a href="http://man7.org/linux/man-pages/man2/setxattr.2.html">setxattr(2)</a>.
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    public static final int XATTR_REPLACE = OsConstantsHolder.XATTR_REPLACE;
    public static final int X_OK = OsConstantsHolder.X_OK;
    public static final int _SC_2_CHAR_TERM = OsConstantsHolder._SC_2_CHAR_TERM;
    public static final int _SC_2_C_BIND = OsConstantsHolder._SC_2_C_BIND;
    public static final int _SC_2_C_DEV = OsConstantsHolder._SC_2_C_DEV;
    public static final int _SC_2_C_VERSION = OsConstantsHolder._SC_2_C_VERSION;
    public static final int _SC_2_FORT_DEV = OsConstantsHolder._SC_2_FORT_DEV;
    public static final int _SC_2_FORT_RUN = OsConstantsHolder._SC_2_FORT_RUN;
    public static final int _SC_2_LOCALEDEF = OsConstantsHolder._SC_2_LOCALEDEF;
    public static final int _SC_2_SW_DEV = OsConstantsHolder._SC_2_SW_DEV;
    public static final int _SC_2_UPE = OsConstantsHolder._SC_2_UPE;
    public static final int _SC_2_VERSION = OsConstantsHolder._SC_2_VERSION;
    public static final int _SC_AIO_LISTIO_MAX = OsConstantsHolder._SC_AIO_LISTIO_MAX;
    public static final int _SC_AIO_MAX = OsConstantsHolder._SC_AIO_MAX;
    public static final int _SC_AIO_PRIO_DELTA_MAX = OsConstantsHolder._SC_AIO_PRIO_DELTA_MAX;
    public static final int _SC_ARG_MAX = OsConstantsHolder._SC_ARG_MAX;
    public static final int _SC_ASYNCHRONOUS_IO = OsConstantsHolder._SC_ASYNCHRONOUS_IO;
    public static final int _SC_ATEXIT_MAX = OsConstantsHolder._SC_ATEXIT_MAX;
    public static final int _SC_AVPHYS_PAGES = OsConstantsHolder._SC_AVPHYS_PAGES;
    public static final int _SC_BC_BASE_MAX = OsConstantsHolder._SC_BC_BASE_MAX;
    public static final int _SC_BC_DIM_MAX = OsConstantsHolder._SC_BC_DIM_MAX;
    public static final int _SC_BC_SCALE_MAX = OsConstantsHolder._SC_BC_SCALE_MAX;
    public static final int _SC_BC_STRING_MAX = OsConstantsHolder._SC_BC_STRING_MAX;
    public static final int _SC_CHILD_MAX = OsConstantsHolder._SC_CHILD_MAX;
    public static final int _SC_CLK_TCK = OsConstantsHolder._SC_CLK_TCK;
    public static final int _SC_COLL_WEIGHTS_MAX = OsConstantsHolder._SC_COLL_WEIGHTS_MAX;
    public static final int _SC_DELAYTIMER_MAX = OsConstantsHolder._SC_DELAYTIMER_MAX;
    public static final int _SC_EXPR_NEST_MAX = OsConstantsHolder._SC_EXPR_NEST_MAX;
    public static final int _SC_FSYNC = OsConstantsHolder._SC_FSYNC;
    public static final int _SC_GETGR_R_SIZE_MAX = OsConstantsHolder._SC_GETGR_R_SIZE_MAX;
    public static final int _SC_GETPW_R_SIZE_MAX = OsConstantsHolder._SC_GETPW_R_SIZE_MAX;
    public static final int _SC_IOV_MAX = OsConstantsHolder._SC_IOV_MAX;
    public static final int _SC_JOB_CONTROL = OsConstantsHolder._SC_JOB_CONTROL;
    public static final int _SC_LINE_MAX = OsConstantsHolder._SC_LINE_MAX;
    public static final int _SC_LOGIN_NAME_MAX = OsConstantsHolder._SC_LOGIN_NAME_MAX;
    public static final int _SC_MAPPED_FILES = OsConstantsHolder._SC_MAPPED_FILES;
    public static final int _SC_MEMLOCK = OsConstantsHolder._SC_MEMLOCK;
    public static final int _SC_MEMLOCK_RANGE = OsConstantsHolder._SC_MEMLOCK_RANGE;
    public static final int _SC_MEMORY_PROTECTION = OsConstantsHolder._SC_MEMORY_PROTECTION;
    public static final int _SC_MESSAGE_PASSING = OsConstantsHolder._SC_MESSAGE_PASSING;
    public static final int _SC_MQ_OPEN_MAX = OsConstantsHolder._SC_MQ_OPEN_MAX;
    public static final int _SC_MQ_PRIO_MAX = OsConstantsHolder._SC_MQ_PRIO_MAX;
    public static final int _SC_NGROUPS_MAX = OsConstantsHolder._SC_NGROUPS_MAX;
    public static final int _SC_NPROCESSORS_CONF = OsConstantsHolder._SC_NPROCESSORS_CONF;
    public static final int _SC_NPROCESSORS_ONLN = OsConstantsHolder._SC_NPROCESSORS_ONLN;
    public static final int _SC_OPEN_MAX = OsConstantsHolder._SC_OPEN_MAX;
    public static final int _SC_PAGESIZE = OsConstantsHolder._SC_PAGESIZE;
    public static final int _SC_PAGE_SIZE = OsConstantsHolder._SC_PAGE_SIZE;
    public static final int _SC_PASS_MAX = OsConstantsHolder._SC_PASS_MAX;
    public static final int _SC_PHYS_PAGES = OsConstantsHolder._SC_PHYS_PAGES;
    public static final int _SC_PRIORITIZED_IO = OsConstantsHolder._SC_PRIORITIZED_IO;
    public static final int _SC_PRIORITY_SCHEDULING = OsConstantsHolder._SC_PRIORITY_SCHEDULING;
    public static final int _SC_REALTIME_SIGNALS = OsConstantsHolder._SC_REALTIME_SIGNALS;
    public static final int _SC_RE_DUP_MAX = OsConstantsHolder._SC_RE_DUP_MAX;
    public static final int _SC_RTSIG_MAX = OsConstantsHolder._SC_RTSIG_MAX;
    public static final int _SC_SAVED_IDS = OsConstantsHolder._SC_SAVED_IDS;
    public static final int _SC_SEMAPHORES = OsConstantsHolder._SC_SEMAPHORES;
    public static final int _SC_SEM_NSEMS_MAX = OsConstantsHolder._SC_SEM_NSEMS_MAX;
    public static final int _SC_SEM_VALUE_MAX = OsConstantsHolder._SC_SEM_VALUE_MAX;
    public static final int _SC_SHARED_MEMORY_OBJECTS = OsConstantsHolder._SC_SHARED_MEMORY_OBJECTS;
    public static final int _SC_SIGQUEUE_MAX = OsConstantsHolder._SC_SIGQUEUE_MAX;
    public static final int _SC_STREAM_MAX = OsConstantsHolder._SC_STREAM_MAX;
    public static final int _SC_SYNCHRONIZED_IO = OsConstantsHolder._SC_SYNCHRONIZED_IO;
    public static final int _SC_THREADS = OsConstantsHolder._SC_THREADS;
    public static final int _SC_THREAD_ATTR_STACKADDR = OsConstantsHolder._SC_THREAD_ATTR_STACKADDR;
    public static final int _SC_THREAD_ATTR_STACKSIZE = OsConstantsHolder._SC_THREAD_ATTR_STACKSIZE;
    public static final int _SC_THREAD_DESTRUCTOR_ITERATIONS = OsConstantsHolder._SC_THREAD_DESTRUCTOR_ITERATIONS;
    public static final int _SC_THREAD_KEYS_MAX = OsConstantsHolder._SC_THREAD_KEYS_MAX;
    public static final int _SC_THREAD_PRIORITY_SCHEDULING = OsConstantsHolder._SC_THREAD_PRIORITY_SCHEDULING;
    public static final int _SC_THREAD_PRIO_INHERIT = OsConstantsHolder._SC_THREAD_PRIO_INHERIT;
    public static final int _SC_THREAD_PRIO_PROTECT = OsConstantsHolder._SC_THREAD_PRIO_PROTECT;
    public static final int _SC_THREAD_SAFE_FUNCTIONS = OsConstantsHolder._SC_THREAD_SAFE_FUNCTIONS;
    public static final int _SC_THREAD_STACK_MIN = OsConstantsHolder._SC_THREAD_STACK_MIN;
    public static final int _SC_THREAD_THREADS_MAX = OsConstantsHolder._SC_THREAD_THREADS_MAX;
    public static final int _SC_TIMERS = OsConstantsHolder._SC_TIMERS;
    public static final int _SC_TIMER_MAX = OsConstantsHolder._SC_TIMER_MAX;
    public static final int _SC_TTY_NAME_MAX = OsConstantsHolder._SC_TTY_NAME_MAX;
    public static final int _SC_TZNAME_MAX = OsConstantsHolder._SC_TZNAME_MAX;
    public static final int _SC_VERSION = OsConstantsHolder._SC_VERSION;
    public static final int _SC_XBS5_ILP32_OFF32 = OsConstantsHolder._SC_XBS5_ILP32_OFF32;
    public static final int _SC_XBS5_ILP32_OFFBIG = OsConstantsHolder._SC_XBS5_ILP32_OFFBIG;
    public static final int _SC_XBS5_LP64_OFF64 = OsConstantsHolder._SC_XBS5_LP64_OFF64;
    public static final int _SC_XBS5_LPBIG_OFFBIG = OsConstantsHolder._SC_XBS5_LPBIG_OFFBIG;
    public static final int _SC_XOPEN_CRYPT = OsConstantsHolder._SC_XOPEN_CRYPT;
    public static final int _SC_XOPEN_ENH_I18N = OsConstantsHolder._SC_XOPEN_ENH_I18N;
    public static final int _SC_XOPEN_LEGACY = OsConstantsHolder._SC_XOPEN_LEGACY;
    public static final int _SC_XOPEN_REALTIME = OsConstantsHolder._SC_XOPEN_REALTIME;
    public static final int _SC_XOPEN_REALTIME_THREADS = OsConstantsHolder._SC_XOPEN_REALTIME_THREADS;
    public static final int _SC_XOPEN_SHM = OsConstantsHolder._SC_XOPEN_SHM;
    public static final int _SC_XOPEN_UNIX = OsConstantsHolder._SC_XOPEN_UNIX;
    public static final int _SC_XOPEN_VERSION = OsConstantsHolder._SC_XOPEN_VERSION;
    public static final int _SC_XOPEN_XCU_VERSION = OsConstantsHolder._SC_XOPEN_XCU_VERSION;

    /**
     * Returns the string name of a getaddrinfo(3) error value.
     * For example, "EAI_AGAIN".
     */
    public static String gaiName(int error) {
        if (error == EAI_AGAIN) {
            return "EAI_AGAIN";
        }
        if (error == EAI_BADFLAGS) {
            return "EAI_BADFLAGS";
        }
        if (error == EAI_FAIL) {
            return "EAI_FAIL";
        }
        if (error == EAI_FAMILY) {
            return "EAI_FAMILY";
        }
        if (error == EAI_MEMORY) {
            return "EAI_MEMORY";
        }
        if (error == EAI_NODATA) {
            return "EAI_NODATA";
        }
        if (error == EAI_NONAME) {
            return "EAI_NONAME";
        }
        if (error == EAI_OVERFLOW) {
            return "EAI_OVERFLOW";
        }
        if (error == EAI_SERVICE) {
            return "EAI_SERVICE";
        }
        if (error == EAI_SOCKTYPE) {
            return "EAI_SOCKTYPE";
        }
        if (error == EAI_SYSTEM) {
            return "EAI_SYSTEM";
        }
        return null;
    }

    /**
     * Returns the string name of an errno value.
     * For example, "EACCES". See {@link Os#strerror} for human-readable errno descriptions.
     */
    public static String errnoName(int errno) {
        if (errno == E2BIG) {
            return "E2BIG";
        }
        if (errno == EACCES) {
            return "EACCES";
        }
        if (errno == EADDRINUSE) {
            return "EADDRINUSE";
        }
        if (errno == EADDRNOTAVAIL) {
            return "EADDRNOTAVAIL";
        }
        if (errno == EAFNOSUPPORT) {
            return "EAFNOSUPPORT";
        }
        if (errno == EAGAIN) {
            return "EAGAIN";
        }
        if (errno == EALREADY) {
            return "EALREADY";
        }
        if (errno == EBADF) {
            return "EBADF";
        }
        if (errno == EBADMSG) {
            return "EBADMSG";
        }
        if (errno == EBUSY) {
            return "EBUSY";
        }
        if (errno == ECANCELED) {
            return "ECANCELED";
        }
        if (errno == ECHILD) {
            return "ECHILD";
        }
        if (errno == ECONNABORTED) {
            return "ECONNABORTED";
        }
        if (errno == ECONNREFUSED) {
            return "ECONNREFUSED";
        }
        if (errno == ECONNRESET) {
            return "ECONNRESET";
        }
        if (errno == EDEADLK) {
            return "EDEADLK";
        }
        if (errno == EDESTADDRREQ) {
            return "EDESTADDRREQ";
        }
        if (errno == EDOM) {
            return "EDOM";
        }
        if (errno == EDQUOT) {
            return "EDQUOT";
        }
        if (errno == EEXIST) {
            return "EEXIST";
        }
        if (errno == EFAULT) {
            return "EFAULT";
        }
        if (errno == EFBIG) {
            return "EFBIG";
        }
        if (errno == EHOSTUNREACH) {
            return "EHOSTUNREACH";
        }
        if (errno == EIDRM) {
            return "EIDRM";
        }
        if (errno == EILSEQ) {
            return "EILSEQ";
        }
        if (errno == EINPROGRESS) {
            return "EINPROGRESS";
        }
        if (errno == EINTR) {
            return "EINTR";
        }
        if (errno == EINVAL) {
            return "EINVAL";
        }
        if (errno == EIO) {
            return "EIO";
        }
        if (errno == EISCONN) {
            return "EISCONN";
        }
        if (errno == EISDIR) {
            return "EISDIR";
        }
        if (errno == ELOOP) {
            return "ELOOP";
        }
        if (errno == EMFILE) {
            return "EMFILE";
        }
        if (errno == EMLINK) {
            return "EMLINK";
        }
        if (errno == EMSGSIZE) {
            return "EMSGSIZE";
        }
        if (errno == EMULTIHOP) {
            return "EMULTIHOP";
        }
        if (errno == ENAMETOOLONG) {
            return "ENAMETOOLONG";
        }
        if (errno == ENETDOWN) {
            return "ENETDOWN";
        }
        if (errno == ENETRESET) {
            return "ENETRESET";
        }
        if (errno == ENETUNREACH) {
            return "ENETUNREACH";
        }
        if (errno == ENFILE) {
            return "ENFILE";
        }
        if (errno == ENOBUFS) {
            return "ENOBUFS";
        }
        if (errno == ENODATA) {
            return "ENODATA";
        }
        if (errno == ENODEV) {
            return "ENODEV";
        }
        if (errno == ENOENT) {
            return "ENOENT";
        }
        if (errno == ENOEXEC) {
            return "ENOEXEC";
        }
        if (errno == ENOLCK) {
            return "ENOLCK";
        }
        if (errno == ENOLINK) {
            return "ENOLINK";
        }
        if (errno == ENOMEM) {
            return "ENOMEM";
        }
        if (errno == ENOMSG) {
            return "ENOMSG";
        }
        if (errno == ENONET) {
            return "ENONET";
        }
        if (errno == ENOPROTOOPT) {
            return "ENOPROTOOPT";
        }
        if (errno == ENOSPC) {
            return "ENOSPC";
        }
        if (errno == ENOSR) {
            return "ENOSR";
        }
        if (errno == ENOSTR) {
            return "ENOSTR";
        }
        if (errno == ENOSYS) {
            return "ENOSYS";
        }
        if (errno == ENOTCONN) {
            return "ENOTCONN";
        }
        if (errno == ENOTDIR) {
            return "ENOTDIR";
        }
        if (errno == ENOTEMPTY) {
            return "ENOTEMPTY";
        }
        if (errno == ENOTSOCK) {
            return "ENOTSOCK";
        }
        if (errno == ENOTSUP) {
            return "ENOTSUP";
        }
        if (errno == ENOTTY) {
            return "ENOTTY";
        }
        if (errno == ENXIO) {
            return "ENXIO";
        }
        if (errno == EOPNOTSUPP) {
            return "EOPNOTSUPP";
        }
        if (errno == EOVERFLOW) {
            return "EOVERFLOW";
        }
        if (errno == EPERM) {
            return "EPERM";
        }
        if (errno == EPIPE) {
            return "EPIPE";
        }
        if (errno == EPROTO) {
            return "EPROTO";
        }
        if (errno == EPROTONOSUPPORT) {
            return "EPROTONOSUPPORT";
        }
        if (errno == EPROTOTYPE) {
            return "EPROTOTYPE";
        }
        if (errno == ERANGE) {
            return "ERANGE";
        }
        if (errno == EROFS) {
            return "EROFS";
        }
        if (errno == ESPIPE) {
            return "ESPIPE";
        }
        if (errno == ESRCH) {
            return "ESRCH";
        }
        if (errno == ESTALE) {
            return "ESTALE";
        }
        if (errno == ETIME) {
            return "ETIME";
        }
        if (errno == ETIMEDOUT) {
            return "ETIMEDOUT";
        }
        if (errno == ETXTBSY) {
            return "ETXTBSY";
        }
        if (errno == EXDEV) {
            return "EXDEV";
        }
        return null;
    }
}
