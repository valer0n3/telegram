package ru.training.muscles.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoMusclePost {
    @NotNull
    private Long categoryModelId;
    @NotBlank
    private String muscleName;
    private String muscleDescription;
    private List<Long> trainingProgramModelIds;
}
