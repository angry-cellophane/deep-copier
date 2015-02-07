package org.dcopier.util.deep


class FinalClass {

    final String name

    FinalClass(String name) {
        this.name = name
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        FinalClass that = (FinalClass) o

        if (name != that.name) return false

        return true
    }

    int hashCode() {
        return name.hashCode()
    }
}
