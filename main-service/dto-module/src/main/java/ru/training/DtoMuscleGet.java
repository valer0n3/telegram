package ru.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DtoMuscleGet {
    private Long muscleId;
    private String muscleName;
    private String muscleDescription;
    private List<DtoTrainingProgramGet> listOfTrainingPrograms;
}
