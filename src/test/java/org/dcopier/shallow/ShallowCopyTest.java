package org.dcopier.shallow;

import org.dcopier.CopyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ShallowCopyTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {1},
                {2},
                {3},
                {4},
                {5},
                {(short)13},
                {(byte)42},
                {42L},
                {2.0f},
                {2.0d},
                {"THIS IS SPARTA!!!"},
                {"COPY ME DEEPER"},
                {"COPY ME DEEPER"},
                {'c'},
                {'\n'},
                {new BigDecimal(123)},
                {Arrays.asList(1,2,3,4)},
                {Arrays.asList("aaa","bbb","ccc")}
        });
    }

    @Parameter public Object source;

    @Test
    public void test() {
        Object copy = CopyUtil.shallowCopy(source);
        Assert.assertTrue(""+source, copy != source);
        Assert.assertTrue(""+source, source.equals(copy));
    }
}
