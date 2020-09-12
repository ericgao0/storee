package com.julianjupiter.storee.product.service;

import com.julianjupiter.storee.product.dto.ProductDto;
import com.julianjupiter.storee.product.mapper.ProductMapper;
import com.julianjupiter.storee.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> findAll() {
        return this.productRepository.findAll().stream()
                .map(this.productMapper::fromEntityToDto)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return this.productRepository.findById(id)
                .map(this.productMapper::fromEntityToDto);
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        productDto.setId(null);

        if (productDto.getCreatedAt() == null) {
            productDto.setCreatedAt(OffsetDateTime.now());
        }

        var newProduct = this.productMapper.fromDtoToEntity(productDto);
        var createdProduct = this.productRepository.save(newProduct);

        return this.productMapper.fromEntityToDto(createdProduct);
    }

    @Override
    public ProductDto update(ProductDto productDto) {
        var productUpdate = this.productMapper.fromDtoToEntity(productDto);
        var updatedProduct = this.productRepository.save(productUpdate);

        return this.productMapper.fromEntityToDto(updatedProduct);
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
