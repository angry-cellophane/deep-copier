package org.dcopier.util.shallow


class FinalFieldClass {
    final int integer
    final double aDouble
    final String string
    final FinalFieldClass that

    FinalFieldClass(int integer, double aDouble, String string, FinalFieldClass that){
        this.integer = integer
        this.aDouble = aDouble
        this.string = string
        this.that = that
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        FinalFieldClass that1 = (FinalFieldClass) o

        if (Double.compare(that1.aDouble, aDouble) != 0) return false
        if (integer != that1.integer) return false
        if (string != that1.string) return false
        if (that != that1.that) return false

        return true
    }

    int hashCode() {
        int result
        long temp
        result = integer
        temp = aDouble != +0.0d ? Double.doubleToLongBits(aDouble) : 0L
        result = 31 * result + (int) (temp ^ (temp >>> 32))
        result = 31 * result + (string != null ? string.hashCode() : 0)
        result = 31 * result + (that != null ? that.hashCode() : 0)
        return result
    }
}
