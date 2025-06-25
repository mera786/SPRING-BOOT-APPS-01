package com.app.service;


import com.app.entitie.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    List<User> getUsers();

    User getSingleUser(int id);

    User updateUser(User user, int id);
    User updatePartialUserData(Map<String, Optional> map, int id);

    void deleteSingleUser(int id);
}
