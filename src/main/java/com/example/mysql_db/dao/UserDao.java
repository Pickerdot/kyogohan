package com.example.mysql_db.dao;

import com.example.mysql_db.entuty.User;

import java.util.List;

public interface UserDao {

    void insertUser(User user);

    int updateUser(User user);

    List<User> getAll();

    User getOne(int id);

}
