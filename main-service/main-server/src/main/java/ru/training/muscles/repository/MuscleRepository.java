package ru.training.muscles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.training.muscles.model.MuscleModel;

import java.util.List;

public interface MuscleRepository extends JpaRepository<MuscleModel, Long> {
    List<MuscleModel> findAllByMuscleId(long muscleId);
}
