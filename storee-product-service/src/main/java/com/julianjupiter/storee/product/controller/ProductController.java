package com.julianjupiter.storee.product.controller;

import com.julianjupiter.storee.product.dto.ProductDto;
import com.julianjupiter.storee.product.exception.BeanValidationException;
import com.julianjupiter.storee.product.exception.ProductNotFoundException;
import com.julianjupiter.storee.product.service.ProductService;
import com.julianjupiter.storee.product.util.UriUtil;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final MessageSource messageSource;
    private final ProductService productService;

    public ProductController(MessageSource messageSource, ProductService productService) {
        this.messageSource = messageSource;
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> findAll() {
        return this.productService.findAll();
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody @Valid ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BeanValidationException(bindingResult);
        }

        var createdProductDto = this.productService.create(productDto);
        var uri = UriUtil.path("/{id}", createdProductDto.getId());

        return ResponseEntity.created(uri)
                .body(createdProductDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        return this.productService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody @Valid ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BeanValidationException(bindingResult);
        }

        return this.productService.findById(id)
                .map(existingProductDto -> {
                    productDto
                            .setId(id)
                            .setCreatedAt(existingProductDto.getCreatedAt());
                    return ResponseEntity.ok(this.productService.update(productDto));
                }).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return this.productService.findById(id)
                .map(productDto -> {
                    this.productService.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}

