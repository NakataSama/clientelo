package br.com.alura.clientelo.store.product;

import br.com.alura.clientelo.store.category.Category;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;
    private BigDecimal price;
    private String description;
    private Integer itemsInStock;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getItemsInStock() {
        return itemsInStock;
    }

    public void setItemsInStock(Integer itemsInStock) {
        this.itemsInStock = itemsInStock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product() {}

    public Product(String name, BigDecimal price, String description, Integer itemsInStock, Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.itemsInStock = itemsInStock;
        this.category = category;
    }
}
