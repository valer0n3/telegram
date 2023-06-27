package ru.training.categories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.training.categories.model.CategoryModel;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
}
