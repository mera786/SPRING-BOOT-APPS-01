package com.app.service.impl;



import com.app.entitie.User;
import com.app.repository.UserRepository;
import com.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getSingleUser(int id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    public User updateUser(User user, int id) {
        User user1 = userRepository.findById(id).get();
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        User save = userRepository.save(user1);
        return save;
    }

    @Override
    public User updatePartialUserData(Map<String, Optional> map, int id)
    {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User not found with id: " + id);
        }
        User user = optionalUser.get();

        map.forEach((key, value) -> {
            if (value.isPresent()) {
                switch (key) {
                    case "name":
                        user.setName((String) value.get());
                        break;
                    case "email":
                        user.setEmail((String) value.get());
                        break;
                    // Add more cases for other fields you want to update
                }
            }
        });

        // Step 3: Save the updated user
        User updatedUser = userRepository.save(user);
        return updatedUser; // Return the updated user
    }


    @Override
    public void deleteSingleUser(int id) {
        userRepository.deleteById(id);
    }
}
