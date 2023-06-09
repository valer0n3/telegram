package ru.training.muscles.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoMuscleGetAll {
    private Long muscleId;
    private String muscleName;
    private String muscleDescription;
}
