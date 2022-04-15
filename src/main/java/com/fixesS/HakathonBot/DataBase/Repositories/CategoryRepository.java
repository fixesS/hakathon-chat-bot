package com.fixesS.HakathonBot.DataBase.Repositories;

import com.fixesS.HakathonBot.DataBase.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryId(long categoryId);
}
