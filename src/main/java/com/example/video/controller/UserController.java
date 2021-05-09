package com.example.video.controller;

import com.example.video.pojo.User;
import com.example.video.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到用户信息更新页面
     * @param model
     * @param httpSession
     * @return
     */
    @RequestMapping("/userUpdate")
    public String updateUser(Model model , HttpSession httpSession){
        model.addAttribute("user",(User)httpSession.getAttribute("user"));
        return "userUpdate";
    }

    @RequestMapping("/update")
    public String updateUserInfo(User user,HttpSession httpSession){
        user.setUserName(((User)httpSession.getAttribute("user")).getUserName());
        httpSession.removeAttribute("user");
        httpSession.setAttribute("user",user);
        userService.updateUser(user);
        return "redirect:/home";
    }

}
