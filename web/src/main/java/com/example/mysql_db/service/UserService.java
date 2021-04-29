package com.example.mysql_db.service;

import com.example.mysql_db.entuty.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getOne(int id);

    public void save(User user);

    void update(User user);
}


