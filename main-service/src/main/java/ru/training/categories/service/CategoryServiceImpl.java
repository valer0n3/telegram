package ru.training.categories.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.training.categories.dto.DtoCategoryGet;
import ru.training.categories.dto.DtoCategoryPatch;
import ru.training.categories.dto.DtoCategoryPost;
import ru.training.categories.mapper.CategoryMapper;
import ru.training.categories.model.CategoryModel;
import ru.training.categories.repository.CategoryRepository;
import ru.training.exceptions.TrainingNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public DtoCategoryGet createNewCategory(DtoCategoryPost dtoCategoryPost) {
        return categoryMapper.mapCategoryModeltoDTOCategoryGet(categoryRepository.save(categoryMapper
                .mapDtoCategoryPostToCategoryModel(dtoCategoryPost)));
    }

    @Override
    @Transactional
    public void deleteCategory(long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<DtoCategoryGet> getAllCategories(int from, int size) {
        Pageable pageable = PageRequest.of(from / size, size, Sort.by("categoryName"));
        return (categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::mapCategoryModeltoDTOCategoryGet).collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public DtoCategoryGet updateCategory(long category_id, DtoCategoryPatch dtoCategoryPatch) {
        CategoryModel categoryModelForUpdate = checkIfCategoryExists(category_id);
        if (dtoCategoryPatch.getName() != null && !dtoCategoryPatch.getName().isBlank()) {
            categoryModelForUpdate.setCategoryName(dtoCategoryPatch.getName());
        }
        if (dtoCategoryPatch.getDescription() != null && !dtoCategoryPatch.getDescription().isBlank()) {
            categoryModelForUpdate.setCategoryDescription(dtoCategoryPatch.getDescription());
        }
        categoryRepository.save(categoryModelForUpdate);
        return categoryMapper.mapCategoryModeltoDTOCategoryGet(categoryModelForUpdate);
    }

    private CategoryModel checkIfCategoryExists(long category_id) {
        return categoryRepository.findById(category_id)
                .orElseThrow(() -> new TrainingNotFoundException(String
                        .format("Category with Id: %d is not existed", category_id)));
    }
}
