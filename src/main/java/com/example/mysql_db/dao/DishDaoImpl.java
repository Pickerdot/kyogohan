package com.example.mysql_db.dao;


import com.example.mysql_db.entuty.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DishDaoImpl implements DishDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DishDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate =jdbcTemplate;
    }

    @Override
    public void insertDish(Dish dish) {
        jdbcTemplate.update("INSERT INTO dish(dish_name) VALUES(?)",
                dish.getDishName());
    }

    @Override
    public int updateDish(Dish dish) {
        return jdbcTemplate.update("UPDATE dish SET dish_name = ? , WHERE dish_id = ?",
                dish.getDishName(),  dish.getDishId());}

    @Override
    public List<Dish> getAll(){
        String sql = "SELECT dish_id, dish_name FROM dish";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
        List<Dish> list = new ArrayList<Dish>();
        for(Map<String, Object> result : resultList){
            Dish dish = new Dish();
            dish.setDishId((int)result.get("dish_id"));
            dish.setDishName((String)result.get("dish_name"));
            list.add(dish);

        }
        return list;
    }

    @Override
    public Dish getOne(int id) {
        String sql = "SELECT dish_id, dish_name FROM dish WHERE dish_id = ?";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, id);
        Dish dish = new Dish();
        for(Map<String, Object> result : resultList) {
            dish.setDishId((int) result.get("dish_id"));
            dish.setDishName((String) result.get("dish_name"));
        }

        return dish;
    }

}
