package com.example.mysql_db.service;

import com.example.mysql_db.dao.DishDao;
import com.example.mysql_db.entuty.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishDao dao;

    @Autowired
    public DishServiceImpl(DishDao dao){
        this.dao = dao;
    }

    @Override
    public List<Dish> getAll(){
        return dao.getAll();
    }

    @Override
    public Dish getOne(int id){
        return dao.getOne(id);
    }

    @Override
    public  void save(Dish dish){
        dao.insertDish(dish);
    }

    @Override
    public void update(Dish dish){
        if(dao.updateDish(dish) == 0){
            throw new UserNotFoundException("can't find same ID");
        }
    }


}
