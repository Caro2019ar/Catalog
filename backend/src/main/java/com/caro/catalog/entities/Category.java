package com.caro.catalog.entities;
import java.io.Serializable;
import java.util.Objects;

public class Category implements Serializable {
    private Long id;
    private String name;

    public Category() {
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return id.equals(category.id) && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        final int prime =31;
        int result =1;
        result = prime *result+((id==null)?0:id.hashCode());
        return result;
    }
}
