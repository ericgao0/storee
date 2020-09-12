package com.julianjupiter.storee.product.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class ProductDto {
    private Long id;
    @NotBlank(message = "{NotBlank.productDto.name}")
    private String name;
    private String description;
    @NotNull(message = "{NotNull.productDto.price}")
    private BigDecimal price;
    @NotNull(message = "{NotNull.productDto.quantity}")
    private Long quantity;
    private OffsetDateTime createdAt;

    public Long getId() {
        return id;
    }

    public ProductDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public ProductDto setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public ProductDto setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
