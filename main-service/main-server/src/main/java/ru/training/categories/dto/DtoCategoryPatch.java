package ru.training.categories.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.training.customvalidation.classvalidation.ValidationNameAndDescrFieldsAreNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ValidationNameAndDescrFieldsAreNull(
        field = "name",
        fieldMatch = "description",
        message = "Fields name and desription are null. There is nothing to change!")
public class DtoCategoryPatch {
    private String name;
    private String description;
}
