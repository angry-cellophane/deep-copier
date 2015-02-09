//package org.dcopier;
//
//import org.openjdk.jmh.annotations.Benchmark;
//import org.openjdk.jmh.annotations.Scope;
//import org.openjdk.jmh.annotations.State;
//import org.openjdk.jmh.annotations.Setup;
//import org.openjdk.jmh.annotations.OutputTimeUnit;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.concurrent.TimeUnit;
//
//@State(Scope.Thread)
//@OutputTimeUnit(TimeUnit.MILLISECONDS)
//public class SampleBenchmark {
//
//    private Object object;
//
//    @Setup
//    public void setup() {
//        ComplexClass cc1 = new ComplexClass(1);
//        cc1.name = "complex_class#1";
//        cc1.set = new HashSet<Object>();
//
//        for (int i = 0; i < 100_000; i++) {
//            cc1.set.add(i);
//        }
//
//        cc1.list = new ArrayList<>();
//        for (char i = 'A'; i <= 'z'; i++) {
//            cc1.list.add(i);
//        }
//
//        int objCount = 1_000_000;
//
//        Object[] objs2 = new Object[objCount];
//        for (int i = 0; i < objs2.length; i++) {
//            ComplexClass cc2 = new ComplexClass(2);
//            cc2.name = "complex_class#1";
//            cc2.set = new HashSet<Object>() {{
//                add(4);
//                add(5);
//                add(6);
//            }};
//            cc2.list = Arrays.<Object>asList('z','y','x','w','Ы');
//
//            objs2[i] = cc2;
//        }
//
//        Object[] objs = new Object[objCount];
//        for (int i = 0; i < objs.length; i++) {
//            ComplexClass cc2 = new ComplexClass(2);
//            cc2.name = "complex_class#1";
//            cc2.set = new HashSet<Object>() {{
//                add(4);
//                add(5);
//                add(6);
//            }};
//            cc2.list = Arrays.<Object>asList('z','y','x','w','Ы');
//            cc2.objects = objs2;
//
//            objs[i] = cc2;
//        }
//
//        cc1.objects = objs;
//
//        object = cc1;
//    }
//
//    @Benchmark
//    public Object deepCopy() {
//        return CopyUtil.deepCopy(object);
//    }
//
//    @Benchmark
//    public Object shallowCopy() {
//        return CopyUtil.shallowCopy(object);
//    }
//
//    public static void main(String[] args) {
//
//    }
//}
