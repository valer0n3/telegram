package ru.training.categories.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.training.categories.dto.DtoCategoryGet;
import ru.training.categories.dto.DtoCategoryPatch;
import ru.training.categories.dto.DtoCategoryPost;
import ru.training.categories.service.CategoryService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/categories")
@Validated
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DtoCategoryGet createNewCategory(@Valid @RequestBody DtoCategoryPost dtoCategoryPost) {
        return categoryService.createNewCategory(dtoCategoryPost);
    }

    @DeleteMapping("/{category_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable("category_id") @Min(0) long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @GetMapping
    public List<DtoCategoryGet> getAllCategories(@RequestParam(name = "from", defaultValue = "0") @Min(0) int from,
                                                 @RequestParam(name = "size", defaultValue = "10") @Min(1) int size) {
        return categoryService.getAllCategories(from, size);
    }

    @PatchMapping("/category_id")
    public DtoCategoryPost updateCategory(@PathVariable("category_id") @Min(0) long category_id,
                                          @RequestBody DtoCategoryPatch dtoCategoryPatch) {
        return categoryService.updateCategory(category_id, dtoCategoryPatch);
    }
}
