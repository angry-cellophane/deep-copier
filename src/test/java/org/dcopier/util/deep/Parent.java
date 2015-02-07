package org.dcopier.util.deep;

public class Parent {
    private final int parentId;
    private String parentName;
    protected String parentGroup;

    public Parent(int parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(String parentGroup) {
        this.parentGroup = parentGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parent parent = (Parent) o;

        if (parentId != parent.parentId) return false;
        if (parentGroup != null ? !parentGroup.equals(parent.parentGroup) : parent.parentGroup != null) return false;
        if (parentName != null ? !parentName.equals(parent.parentName) : parent.parentName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parentId;
        result = 31 * result + (parentName != null ? parentName.hashCode() : 0);
        result = 31 * result + (parentGroup != null ? parentGroup.hashCode() : 0);
        return result;
    }
}
