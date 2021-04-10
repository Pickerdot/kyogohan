package com.example.mysql_db.entuty;

public class Dish {
    private int dishId;
    private String dishName;

    public Dish(){

    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

}
