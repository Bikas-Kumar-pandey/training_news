package com.news.controller;


import com.news.dto.CategoryRequest;
import com.news.dto.CategoryResponse;
import com.news.model.Category;
import com.news.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {this.categoryService = categoryService;}


    @PostMapping("/category")
    public CategoryResponse createCategory(@RequestBody List<CategoryRequest> request){
    return categoryService.createCategory(request);

}
@GetMapping("/category/{id}")
public CategoryResponse getCategories(@PathVariable int id) throws Exception {
        return categoryService.getcategoryById(id);
}

@GetMapping("all-category")
    public List<CategoryResponse> gatAllCategories() throws Exception {
        return categoryService.getAllCategories();
}
@PutMapping("category/{id}")
    public String updateCategory(@PathVariable int id,@RequestBody CategoryRequest request) {
    return categoryService.updatecategory(id, request);
}

@DeleteMapping("/category/{id}")
    public String deleteCategory(@PathVariable int id){
      return   categoryService.deleteCategory(id);
}

}
