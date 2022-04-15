package com.fixesS.HakathonBot.DataBase.Services;

import com.fixesS.HakathonBot.DataBase.Entities.Category;
import com.fixesS.HakathonBot.DataBase.Repositories.CategoryRepository;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryService(){

    }

    @Transactional
    public Category findByCategoryId(long categoryId){
        return categoryRepository.findByCategoryId(categoryId);
    }

    @Transactional
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
}
