package com.dmh.controller;

import com.dmh.model.SysUser;
import com.dmh.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @Autowired
    private SysUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/home")//value和WebSecrityConfig中的defaultSuccessUrl的value对应
    public String  login(HttpServletRequest request) {
        System.out.println("登录成功");
        return "index";
    }

    @PostMapping("/register")
    public String  registry(@RequestParam(value = "username") String username,
                            @RequestParam(value = "pwd1") String password) {
        SysUser user=userRepository.findByUsername(username);
        if (user!=null){
            System.out.println("用户名存在");
            return "registry";
        }
        userRepository.save(new SysUser(username, passwordEncoder.encode(password)));
        System.out.println("注册成功");
        return "index";
    }
}
