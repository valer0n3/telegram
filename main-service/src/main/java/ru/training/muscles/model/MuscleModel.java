package ru.training.muscles.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.training.categories.model.CategoryModel;
import ru.training.trainingprogram.model.TrainingProgramModel;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "muscle")
public class MuscleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "muscle_id")
    private Long muscle_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private CategoryModel categoryModel;
    @Column(name = "name_")
    private String muscleName;
    @Column(name = "description")
    private String muscleDescription;
    @ManyToMany
    @JoinTable(name = "muscle_training_program",
            joinColumns = @JoinColumn(name = "muscle_id"),
            inverseJoinColumns = @JoinColumn(name = "training_program_id"))
    private List<TrainingProgramModel> trainingProgramModel;
}
