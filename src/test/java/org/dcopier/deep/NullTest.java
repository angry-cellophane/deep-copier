package org.dcopier.deep;

import org.dcopier.CopyUtil;
import org.junit.Test;

public class NullTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNull() {
        Object copy = CopyUtil.deepCopy(null);
    }
}
