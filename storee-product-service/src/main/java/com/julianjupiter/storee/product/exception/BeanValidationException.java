package com.julianjupiter.storee.product.exception;

import org.springframework.validation.BindingResult;

public class BeanValidationException extends ApiException {
    private final BindingResult bindingResult;

    public BeanValidationException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
