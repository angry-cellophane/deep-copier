package org.dcopier

class SerializableClass implements Serializable {
    FinalFieldClass another

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        SerializableClass that = (SerializableClass) o

        if (another != that.another) return false

        return true
    }

    int hashCode() {
        return (another != null ? another.hashCode() : 0)
    }
}
