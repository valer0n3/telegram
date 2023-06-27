package ru.training;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DtoMuscleGetAll {
    private Long muscleId;
    private String muscleName;
    private String muscleDescription;
}
