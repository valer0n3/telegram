package ru.training.trainingprogram.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoTrainingProgramPost {
    @NotBlank
    private String trainingProgramName;
    private String trainingProgramDescription;
}
