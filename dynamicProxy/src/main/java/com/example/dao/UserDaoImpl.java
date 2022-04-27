package com.example.dao;

/**
 * created by dfk
 * 2022/4/27
 */
public class UserDaoImpl implements UserDao {
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public String update(String id) {
        return id;
    }
}
