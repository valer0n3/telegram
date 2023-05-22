package ru.training.muscles.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.training.categories.model.CategoryModel;
import ru.training.categories.service.CategoryServiceImpl;
import ru.training.muscles.dto.DtoMuscleGet;
import ru.training.muscles.dto.DtoMusclePost;
import ru.training.muscles.mapper.MuscleMapper;
import ru.training.muscles.model.MuscleModel;
import ru.training.muscles.repository.MuscleRepository;
import ru.training.trainingprogram.model.TrainingProgramModel;
import ru.training.trainingprogram.repository.TrainingProgramRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class MuscleServiceImpl implements MuscleService {
    private final CategoryServiceImpl categoryService;
    private final TrainingProgramRepository trainingProgramRepository;
    private final MuscleRepository muscleRepository;
    private final MuscleMapper muscleMapper;

    @Override
    @Transactional
    public DtoMuscleGet saveNewMuscle(DtoMusclePost dtoMusclePost) {
        CategoryModel categoryModel = categoryService.checkIfCategoryExists(dtoMusclePost.getCategoryModelId());
        List<TrainingProgramModel> trainingProgramModels = new ArrayList<>();
        if (dtoMusclePost.getTrainingProgramModelIds() != null && !dtoMusclePost.getTrainingProgramModelIds().isEmpty()) {
            trainingProgramModels = trainingProgramRepository
                    .findAllByTrainingProgramIdIn(dtoMusclePost.getTrainingProgramModelIds());
        }
        MuscleModel muscleModel = muscleRepository.save(muscleMapper
                .mapDtoMusclePostToMuscleModel(dtoMusclePost, trainingProgramModels, categoryModel));
        return muscleMapper.mapMuscleModelToDtoMuscleGet(muscleModel);
    }

    @Override
    public List<DtoMuscleGet> getMuscleTrainingProgram(String muscleName) {
        return muscleRepository.findAllByMuscleNameContainingIgnoreCase(muscleName).stream()
                .map(muscleMapper::mapMuscleModelToDtoMuscleGet).collect(Collectors.toList());
    }
}
