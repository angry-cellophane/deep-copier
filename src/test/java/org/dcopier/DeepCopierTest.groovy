package org.dcopier

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

import static java.util.Arrays.asList
import static org.junit.Assert.assertTrue
import static org.junit.runners.Parameterized.Parameters
import static org.junit.runners.Parameterized.Parameter

@RunWith(Parameterized.class)
class DeepCopierTest {

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
                [new Node(index: 1, name: "node1", next: new Node(index: 2, name: "node2"))] as Object[],
                [new FinalFieldClass(1, 2.0, "ffc1", new FinalFieldClass(2, 5.21, "ffc2", null))] as Object[],
                [new SerializableClass(another: new FinalFieldClass(1, 2.0, "final", null))] as Object[],
        )
        return a
    }

    private final DeepCopier copier = new Copier()
    @Parameter public source

    @Test void test() {
        def copy = copier.copy(source)
        assertTrue(!source.is(copy))
        assertTrue(source == copy)
    }
}
