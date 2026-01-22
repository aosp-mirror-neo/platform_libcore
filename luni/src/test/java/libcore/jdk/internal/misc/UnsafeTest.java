/*
 * Copyright (C) 2024 The Android Open Source Project
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
package libcore.jdk.internal.misc;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import jdk.internal.misc.Unsafe;

import libcore.test.annotation.NonCts;
import libcore.test.reasons.NonCtsReasons;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@NonCts(bug = 287231726, reason = NonCtsReasons.INTERNAL_APIS)
@RunWith(JUnit4.class)
public class UnsafeTest {

    @SuppressWarnings("unused")
    private static class TestFixture {
        public static boolean staticBooleanVar = false;
        public static byte staticByteVar = 40;
        public static int staticIntVar = 2056;
        public static long staticLongVar = 1234567890;
        public static float staticFloatVar = 2.618f;
        public static double staticDoubleVar = 3.1415;

        public boolean booleanVar = true;
        public byte byteVar = 42;
        public int intVar = 2046;
        public long longVar = 123456789;
        public float floatVar = 1.618f;
        public double doubleVar = 3.141;
        public Object objectVar = new Object();
    }


    @Test
    public void testCompareAndExchangeLong() throws Exception {
        Unsafe unsafe = getUnsafe();
        TestFixture tf = new TestFixture();
        long offset = unsafe.objectFieldOffset(TestFixture.class, "longVar");
        assertEquals(123456789, unsafe.compareAndExchangeLong(tf, offset, 0, -1));
        assertEquals(123456789, tf.longVar);
        assertEquals(123456789, unsafe.compareAndExchangeLong(tf, offset, 123456789, -1));
        assertEquals(-1, tf.longVar);
        assertEquals(-1, unsafe.compareAndExchangeLong(tf, offset, 0, 1));
        assertEquals(-1, tf.longVar);
        assertEquals(-1, unsafe.compareAndExchangeLong(tf, offset, -1, 1));
        assertEquals(1, tf.longVar);
    }

    @Test
    public void testStaticOffset() throws Exception {
        Unsafe unsafe = getUnsafe();
        Class c = Class.forName("libcore.jdk.internal.misc.UnsafeTest$TestFixture");
        Field f = c.getDeclaredField("staticIntVar");
        Object obj = unsafe.staticFieldBase(f);
        long offset = unsafe.staticFieldOffset(f);

        assertEquals(2056, unsafe.getInt(obj, offset));
        assertEquals(2056, unsafe.getAndSetInt(obj, offset, 0));
        assertEquals(0, TestFixture.staticIntVar);

        assertEquals(0, unsafe.getAndSetInt(obj, offset, 1));
        assertEquals(1, TestFixture.staticIntVar);

        assertEquals(1, unsafe.getAndSetInt(obj, offset, 2056));
        assertEquals(2056, TestFixture.staticIntVar);
    }

    @Test
    public void testArrayBaseOffsetsForEqualsOptimization() throws Exception {
        // This test verifies an assumption made in the vectorized implementation of
        // Arrays.equals for various primitive array types. The implementation contains an
        // optimization for 32-bit architectures where unaligned memory access is not supported.

        Unsafe unsafe = getUnsafe();

        if (unsafe.addressSize() == 8) {
            // The optimization is for 32-bit architectures, so this test is only relevant there.
            // On 64-bit systems, unaligned access is generally supported, and this check is not
            // needed.
            return;
        }

        // The optimization assumes that on 32-bit Android, the base offset for most primitive
        // arrays is 12. This is because it is a 4-byte aligned address. By reading a single
        // 4-byte integer, the memory address is advanced to 16, which is an 8-byte aligned
        // address. This alignment allows the subsequent vectorized loop to perform aligned
        // 8-byte memory accesses, which is critical for both performance and correctness on
        // architectures that do not support unaligned access.
        long expectedOffset12 = 12L;

        class TypeInfo {
            final Class<?> arrayClass;
            final String typeName;

            TypeInfo(Class<?> arrayClass, String typeName) {
                this.arrayClass = arrayClass;
                this.typeName = typeName;
            }
        }

        TypeInfo[] typesToTest = {
            new TypeInfo(boolean[].class, "boolean"),
            new TypeInfo(byte[].class, "byte"),
            new TypeInfo(char[].class, "char"),
            new TypeInfo(short[].class, "short"),
            new TypeInfo(int[].class, "int"),
        };

        for (TypeInfo typeInfo : typesToTest) {
            long offset = unsafe.arrayBaseOffset(typeInfo.arrayClass);
            assertEquals(
                "The base offset for " + typeInfo.typeName + " arrays on 32-bit must be "
                    + expectedOffset12 + " for the Arrays.equals(" + typeInfo.typeName
                    + "[], " + typeInfo.typeName + "[]) optimization to work correctly.",
                expectedOffset12, offset);
        }
    }

    private static Unsafe getUnsafe() throws Exception {
        Class<?> unsafeClass = Class.forName("jdk.internal.misc.Unsafe");
        Field f = unsafeClass.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (Unsafe) f.get(null);
    }

}
