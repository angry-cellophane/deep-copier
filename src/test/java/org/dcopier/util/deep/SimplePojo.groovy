package org.dcopier.util.deep

class SimplePojo {
    int id
    String name
    FinalClass finalClass

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        SimplePojo that = (SimplePojo) o

        if (id != that.id) return false
        if (finalClass != that.finalClass) return false
        if (name != that.name) return false

        return true
    }

    int hashCode() {
        int result = id
        result = 31 * result + (name != null ? name.hashCode() : 0)
        result = 31 * result + (finalClass != null ? finalClass.hashCode() : 0)
        return result
    }
}
