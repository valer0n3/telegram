package ru.training.customvalidation.classvalidation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ValidationNameAndDescrFieldsAreNull(
        field = "test",
        fieldMatch = "test2",
        message = "Fields name and desription are null. There is nothing to change!")
public class DtoClassTest {
    private String test;
    private String test2;
}
