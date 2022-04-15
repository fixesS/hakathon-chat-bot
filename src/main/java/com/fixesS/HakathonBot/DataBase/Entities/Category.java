package com.fixesS.HakathonBot.DataBase.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories", schema = "public")
public class Category {

    @Id
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "name")
    private String name;

    public Long getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Category\n{" +
                "\nid=" + categoryId +
                ",\nname='" + name + '\'' +
                "\n}";
    }
}
