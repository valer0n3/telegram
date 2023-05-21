package ru.training.trainingprogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.training.trainingprogram.model.TrainingProgramModel;

public interface TrainingProgramRepository extends JpaRepository<TrainingProgramModel, Long> {
}
