package ru.training.categories.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.training.categories.dto.DtoCategoryGet;
import ru.training.categories.dto.DtoCategoryPatch;
import ru.training.categories.dto.DtoCategoryPost;
import ru.training.categories.mapper.CategoryMapper;
import ru.training.categories.repository.CategoryRepository;

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
        return (categoryRepository.findAll().stream()
                .map(categoryMapper::mapCategoryModeltoDTOCategoryGet).collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public DtoCategoryPost updateCategory(long category_id, DtoCategoryPatch dtoCategoryPatch) {
        return null;
    }
}
