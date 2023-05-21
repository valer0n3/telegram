package ru.training.trainingprogram.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoTrainingProgramGet {
    private Long trainingProgramId;
    private String trainingProgramName;
    private String trainingProgramDescription;
}
