package org.dcopier;

import java.util.List;
import java.util.Set;

public class ComplexClass {
    
    public final int id;
    public String name;
    public List<Object> list;
    public Set<Object> set;
    public Object[] objects;

    public ComplexClass(int id) {
        this.id = id;
    }
}
