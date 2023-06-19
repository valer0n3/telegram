package ru.training;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoMuscleGet {
    private Long muscleId;
    private String muscleName;
    private String muscleDescription;
    private List<DtoTrainingProgramGet> listOfTrainingPrograms;
}
