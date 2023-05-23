package ru.training.customvalidation.classvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class ValidatorNameAndDescrFieldsAreNull implements ConstraintValidator<ValidationNameAndDescrFieldsAreNull, Object> {
    private String field;
    private String fieldMatch;

    @Override
    public void initialize(ValidationNameAndDescrFieldsAreNull constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value)
                .getPropertyValue(fieldMatch);
        return (fieldValue != null && !fieldValue.toString().isBlank()) ||
                (fieldMatchValue != null && !fieldMatchValue.toString().isBlank());
    }
}
