package ru.training.customvalidation.classvalidation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import ru.training.categories.dto.DtoCategoryPatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DtoClassTestTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testIfBothFieldsAreNull() {
        DtoCategoryPatch dtoClassTest = new DtoCategoryPatch();
        Set<ConstraintViolation<DtoCategoryPatch>> validate = validator.validate(dtoClassTest);
        List<ConstraintViolation<DtoCategoryPatch>> listOfValidation = new ArrayList<>(validate);
        assertEquals(1, validate.size(), "A validation does not work!");
        assertEquals("Fields name and desription are null. There is nothing to change!",
                listOfValidation.get(0).getMessage(), "A validation does not work!");
    }
}