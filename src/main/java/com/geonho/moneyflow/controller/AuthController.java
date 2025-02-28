package com.geonho.moneyflow.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geonho.moneyflow.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String email = request.get("email");
        String password = request.get("password"); 

        userService.registerUser(username, email, password);
        return ResponseEntity.ok(Map.of("message", "회원가입 성공"));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        String token = userService.loginUser(email, password);
        if (token != null) {
            return ResponseEntity.ok(Map.of("token", token)); 
        }
        return ResponseEntity.status(401).body(Map.of("error", "로그인 실패"));        
    }
}
