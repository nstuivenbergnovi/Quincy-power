package com.quincy.database_test.service;

import com.quincy.database_test.model.Authority;
import com.quincy.database_test.model.User;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    Collection<User> getUsers();
    Optional<User> getUser(String username);
    String createUser(User user);
    void updateUser(String username, User user);
    void deleteUser(String username);
    Optional<User> getUserByUsername(String username);
    boolean userExists(String username);
    Set<Authority> getAuthorities(String username);
    void addAuthority(String username, String authority);
    void removeAuthority(String username, String authority);

}
