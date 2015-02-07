package org.dcopier;

public interface DeepCopier<T> {
    T copy(T object);
}
