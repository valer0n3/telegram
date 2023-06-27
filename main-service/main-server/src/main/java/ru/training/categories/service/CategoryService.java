package ru.training.categories.service;

import ru.training.categories.dto.DtoCategoryGet;
import ru.training.categories.dto.DtoCategoryPatch;
import ru.training.categories.dto.DtoCategoryPost;

import java.util.List;

public interface CategoryService {
    DtoCategoryGet createNewCategory(DtoCategoryPost dtoCategoryPost);

    void deleteCategory(long categoryId);

    List<DtoCategoryGet> getAllCategories(int from, int size);

    DtoCategoryGet updateCategory(long category_id, DtoCategoryPatch dtoCategoryPatch);
}
