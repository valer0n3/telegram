package ru.training.customvalidation.classvalidation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Set;

class DtoClassTestTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testBothFieldsAreNull() {
        DtoClassTest dtoClassTest = new DtoClassTest();
        dtoClassTest.setTest("  ");
        dtoClassTest.setTest2("test");
        Set<ConstraintViolation<DtoClassTest>> validate = validator.validate(dtoClassTest);
        System.out.println(validate);
    }
}