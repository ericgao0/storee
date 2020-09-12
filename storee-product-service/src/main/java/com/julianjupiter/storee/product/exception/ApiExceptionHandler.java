package com.julianjupiter.storee.product.exception;

import com.julianjupiter.storee.product.util.MessageSourceProperties;
import com.julianjupiter.storee.product.util.UriUtil;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {
    private final MessageSource messageSource;

    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BeanValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ExceptionResponse handleBeanValidation(BeanValidationException exception) {
        var bindingResult = exception.getBindingResult();
        var fieldErrors = bindingResult.getFieldErrors();
        var messages = fieldErrors.stream()
                .map(fieldError -> {
                    var fieldErrorCode = fieldError.getCode();
                    var field = fieldError.getField();
                    var resolveMessageCodes = bindingResult.resolveMessageCodes(fieldErrorCode);
                    var message = this.messageSource.getMessage(resolveMessageCodes[0] + "." + field, new Object[] { fieldError.getRejectedValue() }, Locale.ENGLISH);
                    return Message.of(message);
                })
                .collect(Collectors.toUnmodifiableList());

        return ExceptionResponse.of(HttpStatus.BAD_REQUEST, OffsetDateTime.now(), UriUtil.path(), messages);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleProductNotFound(ProductNotFoundException exception) {
        var params = new Long[] { exception.getProductId() };
        var message = Message.of(messageSource.getMessage(MessageSourceProperties.PRODUCT_NOT_FOUND.toString(), params, Locale.ENGLISH));
        var messages = List.of(message);

        return ExceptionResponse.of(HttpStatus.NOT_FOUND, OffsetDateTime.now(), UriUtil.path(), messages);
    }
}
