package ru.training.muscles.service;

import ru.training.muscles.dto.DtoMuscleGet;
import ru.training.muscles.dto.DtoMusclePost;

public interface MuscleService {
    DtoMuscleGet saveNewMuscle(DtoMusclePost dtoMusclePost);

    DtoMuscleGet getMuscleTrainingProgram(String muscleName);
}
