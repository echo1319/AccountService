package com.eshop.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import shopeeng.account.User;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class UserRepository {
    private JdbcTemplate jdbctemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbctemplate) {
        this.jdbctemplate = jdbctemplate;
    }

    @PostConstruct
    public void initData() {
    }

    public User loginUser(String username, String password) {
        String sql = "SELECT U.USERNAME, U.PASSWORD, U.NAME, U.LASTNAME" +
                " FROM USERS U WHERE U.USERNAME=? " +
                " AND U.PASSWORD=?";
        return (User) jdbctemplate.query(sql, new Object[]{username, password}, new BeanPropertyRowMapper(User.class)).stream().findFirst().get();
    }

    public User registerUser(User user) {
        String sql = "INSERT INTO USERS (USERNAME,PASSWORD,NAME,LASTNAME) " +
                "VALUES (?,?,?,?)";
        if (jdbctemplate.update(sql, new Object[]{user.getUsername(), user.getPassword(), user.getName(), user.getLastname()}) > 0) {
            return user;
        }
        return null;
    }
}


