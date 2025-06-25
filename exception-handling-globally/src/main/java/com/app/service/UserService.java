package com.app.service;

import com.app.entity.User;

public interface UserService {
    User createUser(User user);
    User getSingleUser(long userId);
    User updateSingleUser(User user,long userId);
    void deleteSingleUser(long userId);
}
