package org.dcopier.util.deep;

public class Child extends Parent {

    private final int childId;
    private String childName;

    public Child(int id, int childId) {
        super(id);
        this.childId = childId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Child child = (Child) o;

        if (childId != child.childId) return false;
        if (childName != null ? !childName.equals(child.childName) : child.childName != null) return false;
        if (parentGroup != null ? !parentGroup.equals(child.parentGroup) : child.parentGroup != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + childId;
        result = 31 * result + (childName != null ? childName.hashCode() : 0);
        result = 31 * result + (parentGroup != null ? parentGroup.hashCode() : 0);
        return result;
    }
}
