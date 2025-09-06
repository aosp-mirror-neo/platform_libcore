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

package libcore.java.lang.foreign;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import libcore.test.annotation.NonCts;
import libcore.test.reasons.NonCtsReasons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
@NonCts(bug = 443192271, reason = NonCtsReasons.INTERNAL_APIS)
public class MemorySegmentTest {
    private static final long BYTE_SIZE = 100;
    private static final long BYTE_ALIGNMENT = 1;
    private final Arena globalArena = Arena.global();
    private final MemorySegment segment = globalArena.allocate(BYTE_SIZE, BYTE_ALIGNMENT);
    private final MemorySegment segment2 = globalArena.allocate(BYTE_SIZE, BYTE_ALIGNMENT);

    @Test
    public void nullSegment_equals_ofAddressZero() {
        MemorySegment nullSegment = MemorySegment.NULL;

        assertNotNull(nullSegment);
        assertEquals(0, nullSegment.byteSize());
        assertTrue(nullSegment.scope().isAlive());
        assertEquals(nullSegment.address(), MemorySegment.ofAddress(0L).address());
        assertEquals(nullSegment, MemorySegment.ofAddress(0L));
    }

    @Test
    public void memorySegments_allocated_areUnique() {
        assertNotEquals(segment, segment2);
        assertNotEquals(segment.address(), segment2.address());
    }

}