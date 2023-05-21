package ru.training.trainingprogram.mapper;

import org.mapstruct.Mapper;
import ru.training.trainingprogram.dto.DtoTrainingProgramGet;
import ru.training.trainingprogram.dto.DtoTrainingProgramPost;
import ru.training.trainingprogram.model.TrainingProgramModel;

@Mapper(componentModel = "spring")
public interface TrainingProgramMapper {
    TrainingProgramModel DtoTrainingProgramPostToTrainingProgramModel(DtoTrainingProgramPost dtoTrainingProgramPost);

    DtoTrainingProgramGet TrainingProgramModelToTrainingProgramGet(TrainingProgramModel trainingProgramModel);
}
