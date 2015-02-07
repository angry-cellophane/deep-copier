package org.dcopier.deep;

import org.dcopier.CopyUtil;
import org.dcopier.util.deep.Child;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ParentChildTest {

    @Test
    public void test() {
        Child source = new Child(1, 1);
        source.setParentName("name");
        source.setParentGroup("group name");

        Child copy = CopyUtil.deepCopy(source);
        assertTrue(source != copy);
        assertTrue(source.equals(copy));
    }

}
