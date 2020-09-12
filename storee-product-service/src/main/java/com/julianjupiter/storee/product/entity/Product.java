package com.julianjupiter.storee.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long quantity;
    private OffsetDateTime createdAt;

    public Long getId() {
        return id;
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Product setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public Product setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}

