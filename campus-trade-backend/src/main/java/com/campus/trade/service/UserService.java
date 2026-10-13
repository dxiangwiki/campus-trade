package com.campus.trade.service;

import com.campus.trade.entity.User;
import com.campus.trade.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /** 登录 */
    public User login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new RuntimeException("用户名或密码错误"));
        // 返回前清空密码
        user.setPassword(null);
        return user;
    }

    /** 注册 */
    public User register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("用户名已存在");
        }
        user.setCreateTime(LocalDateTime.now());
        User saved = userRepository.save(user);
        saved.setPassword(null);
        return saved;
    }

    /** 按 id 查 */
    public User getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
        user.setPassword(null);
        return user;
    }
}