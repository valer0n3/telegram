package ru.training.categories.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Set;

class DtoCategoryPostTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testDtoCatetgoryPostNull() {
        DtoCategoryPost dtoCategoryPost = new DtoCategoryPost();
        Set<ConstraintViolation<DtoCategoryPost>> validate = validator.validate(dtoCategoryPost);
    }
}