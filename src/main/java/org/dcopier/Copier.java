package org.dcopier;

public class Copier {
    public static <T> T deepCopy(T object) {
        return MagicCopier.<T>getInstance().copy(object);
    }
}
