package ru.training.muscles.service;

import ru.training.muscles.dto.DtoMuscleGet;
import ru.training.muscles.dto.DtoMuscleGetAll;
import ru.training.muscles.dto.DtoMusclePost;

import java.util.List;

public interface MuscleService {
    DtoMuscleGet saveNewMuscle(DtoMusclePost dtoMusclePost);

    List<DtoMuscleGet> getMuscleTrainingProgram(String muscleName);

    List<DtoMuscleGetAll> getAllMuscles();
}
