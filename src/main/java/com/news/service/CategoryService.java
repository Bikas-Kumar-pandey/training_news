package com.news.service;

import com.news.dto.CategoryRequest;
import com.news.dto.CategoryResponse;
import com.news.model.Category;
import com.news.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryService {


    private final CategoryRepository categoryRepo;

    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }


    public CategoryResponse createCategory(List<CategoryRequest> request) {
        Category category = new Category();
        for (CategoryRequest requests : request) {
            category.setCategories(requests.getCategory());
        }
        Category save = categoryRepo.save(category);

        CategoryResponse response = new CategoryResponse();
        response.setCategory(save.getCategories());
        response.setDate(save.getDate());
        return response;

    }

    public CategoryResponse getcategoryById(int id) throws Exception {
        Optional<Category> byId = categoryRepo.findById(id);
        if (byId.isEmpty()) {
            throw new Exception("Category from given id " + id + " doesn't Exists !");
        }
        Category category = byId.get();
        CategoryResponse response = new CategoryResponse();
        response.setCategory(category.getCategories());
        ;
        return response;
    }

    public List<CategoryResponse> getAllCategories() throws Exception {
        List<Category> all = categoryRepo.findAll();
        if (all == null) {
            throw new Exception("No Category present");
        }
        List<CategoryResponse> responseList = new ArrayList<>();
        for (Category category : all) {
            CategoryResponse response = new CategoryResponse();
            response.setCategory(category.getCategories());
            responseList.add(response);
        }
        return responseList;
    }

    public String updatecategory(int id, CategoryRequest request) {
        Optional<Category> byId = categoryRepo.findById(id);
        if (byId.isEmpty()) {
            throw new RuntimeException("No category present by given id " + id);
        }
        Category category = byId.get();
        String beforeUpdate = category.getCategories();
        category.setCategories(request.getCategory());
        categoryRepo.save(category);
        return "Catagory updated from " + beforeUpdate + " to " + request.getCategory();
    }

    public String deleteCategory(int id) {
        categoryRepo.deleteById(id);
        return "Category deleted";
    }
}
