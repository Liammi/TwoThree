package com.example.video.controller;

import com.example.video.pojo.User;
import com.example.video.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/register")
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String register() {
        return "register";
    }

    @PostMapping("/save")
    public String saveRegister(User user) {
        System.out.println(user);
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/username/{name}")
    @ResponseBody
    public Map<String, String> userNameProving(@PathVariable String name) {
        Map<String, String> resultMap = new HashMap<>();
        if (StringUtils.isEmpty(name)) {
            resultMap.put("name", "empty");
        } else {
            User user = userService.getUserByUserName(name);
            if (user != null) {
                resultMap.put("name", "exist");
            } else {
                resultMap.put("name", "notExist");
            }
        }
        return resultMap;
    }

    @GetMapping("/nickname/{name}")
    @ResponseBody
    public Map<String, String> nickNameProving(@PathVariable String name) {
        Map<String, String> resultMap = new HashMap<>();
        if (StringUtils.isEmpty(name)) {
            resultMap.put("name", "empty");
        } else {
            User user = userService.getUserByNickName(name);
            if (user != null) {
                resultMap.put("name", "exist");
            } else {
                resultMap.put("name", "notExist");
            }
        }
        return resultMap;
    }

}
