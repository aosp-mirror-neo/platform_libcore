/*
 * Copyright (C) 2025 The Android Open Source Project
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
package dalvik.system;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link Thread} holds this object to indicate that the thread is running a virtual
 * thread.
 *
 * @hide
 */
public final class VirtualThreadContext implements Runnable {

    /**
     * Currently, id is only used for debugging purpose, and the carrier thread name.
     * When {@link Thread} represents a virtual thread in a future implementation,
     * this id can be accessed via {@link Thread#threadId()}.
     */
    public final long id;

    /**
     * The name of the carrier thread. The name is cached here and re-used for all carrier threads.
     */
    public final String carrierName;
    /**
     * The object whose run() method gets called
     */
    public final Runnable target;
    /**
     * parkedStates stores the stack frames when a virtual thread is parked.
     * For simplicity, other platform threads read this field to determine if a virtual thread
     * is parked or unparked, and thus we use volatile to ensure the memory order here.
     */
    public volatile VirtualThreadParkedStates parkedStates;

    private VirtualThreadContext(Runnable target, long id) {
        Objects.requireNonNull(target);
        this.id = id;
        this.target = target;
        this.carrierName = "VirtualThread-" + id;
    }

    public VirtualThreadContext(Runnable target) {
        this(target, nextVirtualThreadId());
    }


    private static final AtomicLong NEXT_ID = new AtomicLong(0L);
    public static long nextVirtualThreadId() {
        return NEXT_ID.incrementAndGet();
    }

    @Override
    public void run() {
        target.run();
    }
}
