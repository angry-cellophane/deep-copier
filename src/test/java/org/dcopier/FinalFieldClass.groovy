package org.dcopier


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
}
