package com.example.mysql_db.dao;

import com.example.mysql_db.entuty.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertUser(User user) {
        jdbcTemplate.update("INSERT INTO user(name, email) VALUES(?,?)",
                user.getUserName(), user.getUserMail());
    }

    @Override
    public int updateUser(User user) {
        return jdbcTemplate.update("UPDATE user SET name = ? ,email = ?  WHERE id = ?",
                user.getUserName(), user.getUserName(), user.getUserId());}

    @Override
    public List<User> getAll(){
        String sql = "SELECT id, name, email FROM user";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
        List<User> list = new ArrayList<User>();
        for(Map<String, Object> result : resultList){
            User user = new User();
            user.setUserId((int)result.get("id"));
            user.setUserName((String)result.get("name"));
            user.setUserMail((String)result.get("email"));
            list.add(user);

        }
        return list;
    }

    @Override
    public User getOne(int id) {
        String sql = "SELECT id, name, email FROM user WHERE id = ?";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
        User user = new User();
        for(Map<String, Object> result : resultList) {

            user.setUserId((int) result.get("id"));
            user.setUserName((String) result.get("name"));
            user.setUserMail((String) result.get("email"));
        }

        return user;
    }
}
