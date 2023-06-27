package ru.training.trainingprogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.training.trainingprogram.model.TrainingProgramModel;

import java.util.List;

public interface TrainingProgramRepository extends JpaRepository<TrainingProgramModel, Long> {
    List<TrainingProgramModel> findAllByTrainingProgramIdIn(List<Long> programIds);
}
