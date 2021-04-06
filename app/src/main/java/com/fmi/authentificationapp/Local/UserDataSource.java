package com.fmi.authentificationapp.Local;

import com.fmi.authentificationapp.Database.IUserDataSource;
import com.fmi.authentificationapp.Model.User;

import java.util.List;

import io.reactivex.Flowable;

public class UserDataSource implements IUserDataSource {

    private static UserDataSource mInstance;
    private UserDao userDao;

    public UserDataSource(UserDao userDao) {
        this.userDao = userDao;
    }

    public static UserDataSource getInstance(UserDao userDao) {
        if (mInstance == null) {
            mInstance = new UserDataSource(userDao);
        }
        return mInstance;
    }

    @Override
    public Flowable<User> getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Flowable<List<User>> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void insertUser(User... users) {
        userDao.insertUser(users);
    }

    @Override
    public void updateUser(User... users) {
        userDao.updateUser(users);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    public void deleteAllUsers() {
        userDao.deleteAllUsers();
    }
}
