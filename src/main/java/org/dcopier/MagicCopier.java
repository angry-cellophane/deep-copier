package org.dcopier;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class MagicCopier<T> implements DeepCopier<T> {

    private static class Holder {
        static final MagicCopier INSTANCE = new MagicCopier();
    }

    static <T> MagicCopier<T> getInstance() {
        return Holder.INSTANCE;
    }

    private static final Unsafe U;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            U = (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T copy(T object) {
        try {
            @SuppressWarnings("uncehcked") T instance = (T) object.getClass().cast(_copy(object));
            return instance;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Object _copy(Object object) throws InstantiationException, IllegalAccessException {
        Class<?> aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();

        Object copy = U.allocateInstance(aClass);
        for (Field field : fields) {
            int mods = field.getModifiers();
            if (Modifier.isStatic(mods)) continue;

            field.setAccessible(true);
            copyField(object, field, copy);
            field.setAccessible(false);
        }

        return copy;
    }

    private void copyField(Object object, Field field, Object copy) throws IllegalAccessException {
        long offset = U.objectFieldOffset(field);
        if (field.getType().isPrimitive()) {
            copyPrimitive(object, field, offset, copy);
        } else {
            copyObject(object, field, offset, copy);
        }
    }

    private void copyPrimitive(Object object, Field field, long offset, Object copy) throws IllegalAccessException {
        Class<?> type = field.getType();
        if (type.equals(int.class)) {
            U.putInt(copy, offset, field.getInt(object));
        } else if (type.equals(long.class)) {
            U.putLong(copy, offset, field.getLong(object));
        } else if (type.equals(double.class)) {
            U.putDouble(copy, offset, field.getDouble(object));
        } else if (type.equals(char.class)) {
            U.putChar(copy, offset, field.getChar(object));
        }
    }

    private void copyObject(Object object, Field field, long offset, Object copy) throws IllegalAccessException {
        U.putObject(copy, offset, field.get(object));
    }
}
