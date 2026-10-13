package com.campus.trade.controller;

import com.campus.trade.common.Result;
import com.campus.trade.dto.LoginDTO;
import com.campus.trade.entity.User;
import com.campus.trade.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /** 登录 */
    @PostMapping("/login")
    public Result<User> login(@RequestBody LoginDTO dto) {
        return Result.success(userService.login(dto.getUsername(), dto.getPassword()));
    }

    /** 注册 */
    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        return Result.success(userService.register(user));
    }

    /** 按 id 查用户 */
    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Long id) {
        return Result.success(userService.getById(id));
    }
}