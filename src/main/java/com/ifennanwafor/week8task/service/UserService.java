package com.ifennanwafor.week8task.service;

import com.ifennanwafor.week8task.dto.LoginDTO;
import com.ifennanwafor.week8task.dto.UserDTO;
import com.ifennanwafor.week8task.entity.User;

import java.util.List;

public interface UserService {
    boolean registerUser(UserDTO userDTO);

    void signUpUser(UserDTO userDTO);
    UserDTO loginUser(LoginDTO loginDTO);
    User getUserByEmail(String email);

    User getUserByUsername(String userName);
     User getUserByPassword(String password);

    UserDTO getUserById(Long userId);

    List<User> getAllUsers();
}
