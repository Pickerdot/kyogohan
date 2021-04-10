package com.example.mysql_db.service;

import com.example.mysql_db.dao.UserDao;
import com.example.mysql_db.entuty.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao dao;

    @Autowired
    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public User getOne(int id) {
        return dao.getOne(id);
    }

    @Override
    public void save(User user){
        dao.insertUser(user);
    }

    @Override
    public void update(User user) {
        if(dao.updateUser(user)== 0){
            throw new UserNotFoundException("can't find same ID");
        }
    }


}
