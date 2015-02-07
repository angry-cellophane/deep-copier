package org.dcopier.shallow

import org.dcopier.CopyUtil
import org.dcopier.util.shallow.FinalFieldClass
import org.dcopier.util.shallow.SerializableClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

import static java.util.Arrays.asList
import static org.junit.Assert.assertTrue
import static org.junit.runners.Parameterized.Parameters
import static org.junit.runners.Parameterized.Parameter

@RunWith(Parameterized.class)
class GShallowCopyTest {

    @Parameters
    static Collection<Object[]> data() {
        Collection<Object[]> a = asList(
                [1] as Object[],
                [2] as Object[],
                [123] as Object[],
                [123.12] as Object[],
                ['c' as Character] as Object[],
                ['\n' as Character] as Object[],
                ["Hello"] as Object[],
                ["Test\ntest"] as Object[],
                [new org.dcopier.util.shallow.Node(index: 1, name: "node1", next: new org.dcopier.util.shallow.Node(index: 2, name: "node2"))] as Object[],
                [new FinalFieldClass(1, 2.0, "ffc1", new FinalFieldClass(2, 5.21, "ffc2", null))] as Object[],
                [new SerializableClass(another: new FinalFieldClass(1, 2.0, "final", null))] as Object[],
        )
        return a
    }

    @Parameter public source

    @Test void test() {
        def copy = CopyUtil.shallowCopy(source)
        assertTrue("${source}", !source.is(copy))
        assertTrue("${source}",source == copy)
    }
}
