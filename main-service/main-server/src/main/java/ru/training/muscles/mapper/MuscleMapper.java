package ru.training.muscles.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.training.DtoMuscleGet;
import ru.training.DtoMuscleGetAll;
import ru.training.categories.model.CategoryModel;
import ru.training.muscles.dto.DtoMusclePost;
import ru.training.muscles.model.MuscleModel;
import ru.training.trainingprogram.model.TrainingProgramModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MuscleMapper {
    @Mapping(target = "categoryModel", source = "categoryModel")
    @Mapping(target = "muscleName", source = "dtoMusclePost.muscleName")
    @Mapping(target = "muscleDescription", source = "dtoMusclePost.muscleDescription")
    @Mapping(target = "trainingProgramModel", source = "trainingProgramModel")
    MuscleModel mapDtoMusclePostToMuscleModel(DtoMusclePost dtoMusclePost,
                                              List<TrainingProgramModel> trainingProgramModel,
                                              CategoryModel categoryModel);

    @Mapping(target = "muscleId", source = "muscleId")
    @Mapping(target = "muscleName", source = "muscleName")
    @Mapping(target = "muscleDescription", source = "muscleDescription")
    @Mapping(target = "listOfTrainingPrograms", source = "trainingProgramModel")
    DtoMuscleGet mapMuscleModelToDtoMuscleGet(MuscleModel muscleModel);

    @Mapping(target = "muscleId", source = "muscleId")
    @Mapping(target = "muscleName", source = "muscleName")
    @Mapping(target = "muscleDescription", source = "muscleDescription")
    DtoMuscleGetAll mapMuscleModelToDtoAllMuscleGet(MuscleModel muscleModel);
}
