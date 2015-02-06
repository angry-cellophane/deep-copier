package org.dcopier;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

class SerializingCopier implements DeepCopier {

    private static class Holder {
        static SerializingCopier INSTANCE = new SerializingCopier();
    }

    static SerializingCopier getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public <T> T copy(T object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = null;
            ByteArrayInputStream bais = null;
            ObjectInputStream ois = null;
            try {
                oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                baos.flush();
                oos.flush();

                bais = new ByteArrayInputStream(baos.toByteArray());
                ois = new ObjectInputStream(bais);
                @SuppressWarnings("unchecked") T copy = (T) object.getClass().cast(ois.readObject());
                return copy;
            } finally {
                baos.close();
                if (oos != null) oos.close();
                if (bais != null) bais.close();
                if (ois != null) ois.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private SerializingCopier() {}
}
