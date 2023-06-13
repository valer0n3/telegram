package ru.training.trainingprogram.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.training.trainingprogram.dto.DtoTrainingProgramGet;
import ru.training.trainingprogram.dto.DtoTrainingProgramPost;
import ru.training.trainingprogram.service.TrainingProgramService;

import java.util.List;

@RestController
@RequestMapping("/training/program")
@AllArgsConstructor
@Validated
public class TrainingProgramController {
    private final TrainingProgramService trainingProgramService;

    @PostMapping
    public DtoTrainingProgramGet createNewProgram(@Valid @RequestBody DtoTrainingProgramPost dtoTrainingProgramPost) {
        return trainingProgramService.createNewProgram(dtoTrainingProgramPost);
    }

    @GetMapping
    public List<DtoTrainingProgramGet> getAllTrainingPrograms(@RequestParam(name = "from", defaultValue = "0") @Min(0) int from,
                                                              @RequestParam(name = "size", defaultValue = "10") @Min(1) int size) {
        return trainingProgramService.getAllTrainingPrograms(from, size);
    }
}
