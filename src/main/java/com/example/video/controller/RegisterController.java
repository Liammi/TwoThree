package com.example.video.controller;

import com.example.video.pojo.User;
import com.example.video.service.UserService;
import com.example.video.util.Toolbox;
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

    /**
     * 跳转到登陆界面
     * @return
     */
    @GetMapping()
    public String register() {
        return "register";
    }

    /**
     * 保存用户，重定向到登陆界面
     * @param user
     * @return
     */
    @PostMapping("/save")
    public String saveRegister(User user) {
        user.setAvatar("https://gitee.com/inndownn/picture/raw/image/img/20210509110012.png");
        String pwdMd5 = Toolbox.md5(user.getPassword());
        user.setPassword(pwdMd5);
        userService.saveUser(user);
        return "redirect:/";
    }

    /**
     * 检查username是否重复
     * @param name
     * @return
     */
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

    /**
     * 检查nickname是否重复
     * @param name
     * @return
     */
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
