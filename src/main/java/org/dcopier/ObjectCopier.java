package org.dcopier;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ObjectCopier {

    private static final Unsafe U;
    private static final Class<?> META_CLASS;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            U = (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Class<?> groovy = null;
        try {
            groovy = Class.forName("groovy.lang.MetaObjectProtocol");
        } catch (ClassNotFoundException e) {
//            no groovy
        }
        META_CLASS = groovy;
    }

    private final boolean isShallow;

    public ObjectCopier(boolean isShallow) {
        this.isShallow = isShallow;
    }

    public <T> T copy(T object) {
        try {
            @SuppressWarnings("unchecked") T instance = (T) object.getClass().cast(_copy(object, true));
            return instance;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Object _copy(Object object, boolean isRoot) throws InstantiationException, IllegalAccessException {
        Class<?> aClass = object.getClass();
        if (object instanceof String) {
            return isShallow && !isRoot
                    ? object
                    : copyString((String) object);
        }
        if (object instanceof Object[]) {
            return isShallow && !isRoot
                    ? object
                    : copyArray((Object[]) object);
        }
        if (object instanceof Class
                || (META_CLASS != null && META_CLASS.isAssignableFrom(aClass))) {
            return object;
        }

        Object copy = U.allocateInstance(aClass);

        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            int mods = field.getModifiers();
            if (Modifier.isStatic(mods)) continue;

            copyField(object, field, copy);
        }

        return copy;
    }

    private void copyField(Object object, Field field, Object copy) throws IllegalAccessException, InstantiationException {
        if (field.getType().isPrimitive()) {
            copyPrimitiveValue(object, field, copy);
        } else {
            copyObjectValue(object, field, copy);
        }
    }

    private void copyPrimitiveValue(Object source, Field field, Object target) throws IllegalAccessException {
        long offset = U.objectFieldOffset(field);

        Class<?> aClass = field.getType();
        if (aClass == short.class) {
            U.putShort(target, offset, U.getByte(source, offset));
        } else if (aClass == byte.class) {
            U.putByte(target, offset, U.getByte(source, offset));
        } else if (aClass == int.class) {
            U.putInt(target, offset, U.getInt(source, offset));
        } else if (aClass == long.class) {
            U.putLong(target, offset, U.getLong(source, offset));
        } else if (aClass == float.class) {
            U.putFloat(target, offset, U.getFloat(source, offset));
        } else if (aClass == double.class) {
            U.putDouble(target, offset, U.getDouble(source, offset));
        } else if (aClass == char.class) {
            U.putChar(target, offset, U.getChar(source, offset));
        } else if (aClass == void.class) {
//            intentionally left blank
        } else {
            throw new IllegalAccessException("Unknown primitive type: "+aClass);
        }
    }

    private void copyObjectValue(Object object, Field field, Object copy) throws IllegalAccessException, InstantiationException {
        long offset = U.objectFieldOffset(field);

        Object sourceValue = U.getObject(object, offset);
        if (sourceValue == null) return;

        Object newValue = isShallow ? sourceValue : deepObjectCopy(sourceValue);

        U.putObject(copy, offset, newValue);
    }

    private Object deepObjectCopy(Object sourceValue) throws IllegalAccessException, InstantiationException {
        Class<?> c = sourceValue.getClass();
        if (c == String.class) {
            return copyString((String) sourceValue);
        } else if (Object[].class.isAssignableFrom(c)) {
            return copyArray((Object[]) sourceValue);
        }
        return _copy(sourceValue, false);
    }

    private String copyString(String s) {
        return new String(s.toCharArray());
    }

    private Object[] copyArray(Object[] o) {
        return Arrays.copyOf(o, o.length);
    }
}
