package com.geonho.moneyflow.service;

import com.geonho.moneyflow.dto.User;
import com.geonho.moneyflow.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(String username, String email, String password) {
        String hashedPassword = passwordEncoder.encode(password); // 비밀번호 암호화
        User user = new User(null, username, email, hashedPassword);
        userMapper.save(user);
    }

    public User loginUser(String email, String rawPassword) {
        User user = userMapper.findByEmail(email);
        if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
            return user;
        }
        return null; // 로그인 실패
    }
}
