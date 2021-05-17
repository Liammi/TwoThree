package com.example.video.controller.admin;

import com.example.video.pojo.Admin;
import com.example.video.service.AdminService;
import com.example.video.service.serviceImpl.AdminServiceImpl;
import com.example.video.util.Toolbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminLoginAndRegisterController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public String adminLogin(){
        return "admin/login";
    }

    @RequestMapping("/register")
    public String adminRegister(){
        return "admin/register";
    }

    /**
     * 验证登陆的用户名以及密码
     * @param admin
     * @param httpSession
     * @return
     */
    @RequestMapping("/jump")
    public String adminJump(Admin admin, HttpSession httpSession){
        String pwdMd5 = Toolbox.md5(admin.getPassword());
        Admin admin1 = adminService.getAdminByNameAndPassword(admin.getAdminName(),pwdMd5);
        if (admin1 != null){
            httpSession.setAttribute("admin",admin1);
            return "redirect:/admin/userManage";
        }else {
            return "redirect:/admin/login";
        }
    }
    @RequestMapping("/register/save")
    public String adminRegisterInfo(Admin admin){
        String pwdMd5 = Toolbox.md5(admin.getPassword());
        admin.setPassword(pwdMd5);
        adminService.saveAdmin(admin);
        return "redirect:/admin/login";
    }

    @RequestMapping("/logout")
    public String adminLogout(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:/admin/login";
    }

}
