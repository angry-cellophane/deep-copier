package org.dcopier;


public class CopyUtil {

    private static final ObjectCopier shallowCopier = new ObjectCopier(true);
    private static final ObjectCopier deepCopier = new ObjectCopier(false);

    public static <T> T deepCopy(T object) {
        if (object == null) throw new IllegalArgumentException("Null cannot be passed");
        return deepCopier.copy(object);
    }

    public static <T> T shallowCopy(T object) {
        if (object == null) throw new IllegalArgumentException("Null cannot be passed");
        return shallowCopier.copy(object);
    }


}
