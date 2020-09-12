package com.julianjupiter.storee.product.service;

import com.julianjupiter.storee.product.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDto> findAll();

    Optional<ProductDto> findById(Long id);

    ProductDto create(ProductDto productDto);

    ProductDto update(ProductDto productDto);

    void deleteById(Long id);
}
