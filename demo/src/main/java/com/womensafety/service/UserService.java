package com.womensafety.service;

import com.womensafety.dto.LoginRequest;
import com.womensafety.dto.LoginResponse;
import com.womensafety.dto.RegisterRequest;
import com.womensafety.model.User;
import com.womensafety.userdto.UserDTO;

public interface UserService {

    UserDTO register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    User findByEmail(String email);
}