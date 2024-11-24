package com.example.demo.controller;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    public UserService() {
        // Initialize with sample data
        users.add(new User(1L, "alice", "alice@example.com"));
        users.add(new User(2L, "bob", "bob@example.com"));
        users.add(new User(3L, "charlie", "charlie@example.com"));
    }

    // Get all users
    public List<User> getAllUsers() {
        return users;
    }

    // Search users by username
    public List<User> findByUsername(String username) {
        List<User> result = new ArrayList<>();
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                result.add(user);
            }
        }
        return result;
    }

    // Add a new user
    public User addUser(User user) {
        user.setId((long) (users.size() + 1)); // Generate a new ID
        users.add(user);
        return user;
    }
}
