package br.com.alura.clientelo.store.core.entity.category;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;
    private CategoryStatus active = CategoryStatus.ACTIVE;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return this.active.equals(CategoryStatus.ACTIVE);
    }

    public void setActive(CategoryStatus active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return active == category.active && Objects.equals(id, category.id) && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, active);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                '}';
    }

    public Category changeStatus() {
        this.active = active.equals(CategoryStatus.ACTIVE) ? CategoryStatus.INACTIVE : CategoryStatus.ACTIVE;
        return this;
    }
}
