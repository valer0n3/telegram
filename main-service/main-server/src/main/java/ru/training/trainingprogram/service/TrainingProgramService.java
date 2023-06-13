package ru.training.trainingprogram.service;

import org.springframework.stereotype.Service;
import ru.training.trainingprogram.dto.DtoTrainingProgramGet;
import ru.training.trainingprogram.dto.DtoTrainingProgramPost;

import java.util.List;

@Service
public interface TrainingProgramService {
    DtoTrainingProgramGet createNewProgram(DtoTrainingProgramPost dtoTrainingProgramPost);

    List<DtoTrainingProgramGet> getAllTrainingPrograms(int from, int size);
}
