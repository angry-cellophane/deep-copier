package org.dcopier;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class SampleBenchmark {

    private Object object;

    @Setup
    public void setup() {
        ComplexClass cc1 = new ComplexClass(1);
        cc1.name = "complex_class#1";
        cc1.set = new HashSet<Object>() {{
            add(1);
            add(2);
            add(3);
        }};

        cc1.list = Arrays.<Object>asList('a','b','c','d','e');

        int objCount = 50_000;

        Object[] objs = new Object[objCount];
        for (int i = 0; i < objs.length; i++) {
            ComplexClass cc2 = new ComplexClass(2);
            cc2.name = "complex_class#1";
            cc2.set = new HashSet<Object>() {{
                add(4);
                add(5);
                add(6);
            }};
            cc2.list = Arrays.<Object>asList('z','y','x','w','Ы');

            objs[i] = cc2;
        }

        cc1.objects = objs;

        object = cc1;
    }

    @Benchmark
    public Object benchmark() {
        return CopyUtil.deepCopy(object);
    }

    public static void main(String[] args) {

    }
}
