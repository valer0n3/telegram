package ru.training.muscles.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.training.DtoMuscleGet;
import ru.training.DtoMuscleGetAll;
import ru.training.muscles.dto.DtoMusclePost;
import ru.training.muscles.service.MuscleService;

import java.util.List;

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
    public List<DtoMuscleGet> getMuscleTrainingProgram(@RequestParam(name = "muscleId") @NotNull Long muscleId) {
        return muscleService.getMuscleTrainingProgram(muscleId);
    }

    @GetMapping("/all")
    public List<DtoMuscleGetAll> getAllMuscles() {
        return muscleService.getAllMuscles();
    }
}
