package ru.training.muscles.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.training.muscles.dto.DtoMuscleGet;
import ru.training.muscles.dto.DtoMusclePost;
import ru.training.muscles.service.MuscleService;

@RestController
@RequestMapping("/muscle")
@AllArgsConstructor
@Validated
public class MuscleController {
    private final MuscleService muscleService;

    @PostMapping
    public DtoMuscleGet saveNewMuscle(@Valid @RequestBody DtoMusclePost dtoMusclePost) {
        return muscleService.saveNewMuscle(dtoMusclePost);
    }

    @GetMapping
    public DtoMuscleGet getMuscleTrainingProgram(@RequestParam(name = "muscleName") @NotBlank String muscleName) {
        return null;
    }
}
