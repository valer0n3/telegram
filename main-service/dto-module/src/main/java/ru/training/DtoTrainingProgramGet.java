package ru.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DtoTrainingProgramGet {
    private Long trainingProgramId;
    private String trainingProgramName;
    private String trainingProgramDescription;
}
