package org.dcopier;


public class CopyUtil {

    public static <T> T deepCopy(T object) {
        return copy(object, false);
    }

    public static <T> T shallowCopy(T object) {
        return copy(object, true);
    }

    private static <T> T copy(T source, boolean isShallow) {
        if (source == null) throw new IllegalArgumentException("Null cannot be passed");
        return new ObjectCopier<T>(source, isShallow).getCopy();
    }


}
