package ru.training.muscles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.training.muscles.model.MuscleModel;

public interface MuscleRepository extends JpaRepository<MuscleModel, Long> {

}
