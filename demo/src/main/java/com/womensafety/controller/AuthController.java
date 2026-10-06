package com.womensafety.controller;

import com.womensafety.dto.LoginRequest;
import com.womensafety.dto.LoginResponse;
import com.womensafety.dto.RegisterRequest;
import com.womensafety.service.UserService;
import com.womensafety.userdto.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://smart-safety-and-emergency-response-0372.onrender.com"
})
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(
            @Valid @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(
                userService.register(request)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                userService.login(request)
        );
    }

}