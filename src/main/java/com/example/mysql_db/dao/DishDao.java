package com.example.mysql_db.dao;

import com.example.mysql_db.entuty.Dish;

import java.util.List;

public interface DishDao {

    void insertDish(Dish dish);

    int updateDish(Dish dish);

    List<Dish> getAll();

    Dish getOne(int id);

}
