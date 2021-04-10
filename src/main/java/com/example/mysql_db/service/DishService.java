package com.example.mysql_db.service;

import com.example.mysql_db.entuty.Dish;
import com.example.mysql_db.entuty.User;

import java.util.List;

public interface DishService {
    List<Dish> getAll();

    Dish getOne(int id);

    public void save(Dish dish);

    void update(Dish dish);
}
