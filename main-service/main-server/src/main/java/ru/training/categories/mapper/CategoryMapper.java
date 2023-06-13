package ru.training.categories.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.training.categories.dto.DtoCategoryGet;
import ru.training.categories.dto.DtoCategoryPost;
import ru.training.categories.model.CategoryModel;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "categoryName", source = "name")
    @Mapping(target = "categoryDescription", source = "description")
    CategoryModel mapDtoCategoryPostToCategoryModel(DtoCategoryPost dtoCategoryPost);

    @Mapping(target = "id", source = "categoryId")
    @Mapping(target = "name", source = "categoryName")
    @Mapping(target = "description", source = "categoryDescription")
    DtoCategoryGet mapCategoryModeltoDTOCategoryGet(CategoryModel categoryModel);
}
