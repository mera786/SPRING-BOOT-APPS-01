package com.app.service.impl;


import com.app.entity.User;
import com.app.exception.ResourceNotFoundException;
import com.app.repository.UserRepository;
import com.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getSingleUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user", "id", userId,
                        "sorry user is not associated with id:"+userId, HttpStatus.NOT_FOUND)
        );
        return user;
    }

    @Override
    public User updateSingleUser(User user, long userId) {
        User updatedUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user", "id", userId,
                        "sorry only user is not associated with id:" + userId, HttpStatus.NOT_FOUND)
        );
        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setMobile(user.getMobile());
        User updatedUserSave = userRepository.save(updatedUser);
        return updatedUserSave;
    }

    @Override
    public void deleteSingleUser(long userId) {
        User findUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user", "id", userId,
                        "sorry only this user is not associated with id:" + userId,
                        HttpStatus.NOT_FOUND)
        );
        long id = findUser.getId();
        userRepository.deleteById(id);
    }
}
