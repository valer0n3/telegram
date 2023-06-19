package ru.training.muscles.service;

import ru.training.DtoMuscleGet;
import ru.training.DtoMuscleGetAll;
import ru.training.muscles.dto.DtoMusclePost;

import java.util.List;

public interface MuscleService {
    DtoMuscleGet saveNewMuscle(DtoMusclePost dtoMusclePost);

    List<DtoMuscleGet> getMuscleTrainingProgram(Long muscleId);

    List<DtoMuscleGetAll> getAllMuscles();
}
