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

package java.lang.invoke;

import java.lang.foreign.MemorySegment;
import java.nio.ByteOrder;

/**
 * A VarHandle to access the region of memory referenced by MemorySegment
 * @hide
 */
public final class MemorySegmentVarHandle extends VarHandle {
    private final long byteAlignment;
    private final boolean nativeByteOrder;

    private MemorySegmentVarHandle(Class<?> varType, ByteOrder byteOrder, long byteAlignment) {
        super(varType, MemorySegment.class, long.class);
        this.byteAlignment = byteAlignment;
        this.nativeByteOrder = byteOrder.equals(ByteOrder.nativeOrder());
    }

    public static MemorySegmentVarHandle create(Class<?> varType,
                                                ByteOrder byteOrder,
                                                long byteAlignment) {
        return new MemorySegmentVarHandle(varType, byteOrder, byteAlignment);
    }

}
