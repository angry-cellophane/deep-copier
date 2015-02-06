package org.dcopier;

import java.io.Serializable;

public class Copier implements DeepCopier {

    @Override
    public <T> T copy(T object) {
        DeepCopier delegate = object instanceof Serializable
                ?  SerializingCopier.getInstance()
                : null;
        return delegate.copy(object);
    }
}
