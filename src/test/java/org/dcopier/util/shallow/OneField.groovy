package org.dcopier.util.shallow

class OneField {
    Object object

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        OneField oneField = (OneField) o

        if (object != oneField.object) return false

        return true
    }

    int hashCode() {
        return (object != null ? object.hashCode() : 0)
    }
}
