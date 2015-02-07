package org.dcopier.deep;


import org.dcopier.CopyUtil;
import org.dcopier.util.deep.FinalClass;
import org.dcopier.util.deep.SimplePojo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.runners.Parameterized.Parameter;

@RunWith(Parameterized.class)
public class DeepCopyTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        FinalClass finalClass = new FinalClass("final class");

        SimplePojo pojo = new SimplePojo();
        pojo.setId(1);
        pojo.setName("pojo#1");
        pojo.setFinalClass(finalClass);

        SimplePojo pojoWithNull = new SimplePojo();
        pojo.setId(2);
        pojo.setName("pojo#2");

        return Arrays.asList(new Object[][] {
                {finalClass},
                {pojo},
                {pojoWithNull}
        });
    }

    @Parameter public Object source;

    @Test
    public void test() {
        Object copy = CopyUtil.deepCopy(source);
        Assert.assertTrue(""+source, source != copy);
        Assert.assertTrue(""+source, source.equals(copy));
    }

}
