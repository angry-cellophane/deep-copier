package org.dcopier.util

class Node {
    Long index
    String name
    Node next

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Node node = (Node) o

        if (index != node.index) return false
        if (name != node.name) return false
        if (next != node.next) return false

        return true
    }

    int hashCode() {
        int result
        result = (index != null ? index.hashCode() : 0)
        result = 31 * result + (name != null ? name.hashCode() : 0)
        result = 31 * result + (next != null ? next.hashCode() : 0)
        return result
    }
}
