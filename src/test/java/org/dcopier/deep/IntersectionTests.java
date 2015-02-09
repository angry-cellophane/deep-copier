package org.dcopier.deep;

import org.dcopier.CopyUtil;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class IntersectionTests {

    static class A {
        public B b;
        public C c;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            A a = (A) o;

            if (!b.equals(a.b)) return false;
            if (!c.equals(a.c)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = b.hashCode();
            result = 31 * result + c.hashCode();
            return result;
        }
    }

    static class B {
        public C c;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            B b = (B) o;

            if (!c.equals(b.c)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return c.hashCode();
        }
    }


    static class C {
        public int i;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            C c = (C) o;

            if (i != c.i) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return i;
        }
    }

    @Test
    public void test() {
        C c = new C();
        c.i = 42;

        B b = new B();
        b.c = c;

        A a = new A();
        a.b = b;
        a.c = c;

        A copy = CopyUtil.deepCopy(a);
        assertTrue(a != copy);
        assertTrue(a.equals(copy));

        assertTrue(copy.b.c == copy.c);
    }

}
