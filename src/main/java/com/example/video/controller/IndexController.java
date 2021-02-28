
package com.example.video.controller;

import cn.hutool.core.util.IdUtil;
import com.example.video.pojo.User;
import com.example.video.service.UserService;
import com.example.video.service.VideoInfoService;
import com.example.video.service.VideoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 用于页面跳转,展示数据
 */
@Controller
public class IndexController {

	@Autowired
	private VideoTypeService videoTypeService;
	@Autowired
	private VideoInfoService videoInfoService;
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index(Model model){
		return "index";
	}

	@RequestMapping("/admin/manage")
	public String manage(Model model,HttpSession httpSession) {
		model.addAttribute("infos",videoInfoService.ListVideoInfoAndType());
		model.addAttribute("name",((User)httpSession.getAttribute("user")).getNickName());
		return "videoManage";
	}

	@RequestMapping("/admin/add")
	public String add(Model model,HttpSession httpSession) {
		model.addAttribute("types",videoTypeService.listType());
		model.addAttribute("name",((User)httpSession.getAttribute("user")).getNickName());
		return "videoAdd";
	}

	@GetMapping("/home")
	public String home(Model model ,HttpSession httpSession) {
		model.addAttribute("infos", videoInfoService.ListVideoInfoAndType());
		model.addAttribute("UUID", IdUtil.simpleUUID());
		model.addAttribute("name",((User)httpSession.getAttribute("user")).getNickName());
		return "home";
	}

	@PostMapping("/jump")
	public String jump(@RequestParam("userName")String userName, @RequestParam("password")String password,boolean remember, Model model, HttpSession session) {
		User user = userService.getByUserNameAndPassword(userName,password);
		if(user!=null){
			session.setAttribute("user",user);
			return "redirect:/home";
		}else {
			return "redirect:/";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession httpSession){
		httpSession.removeAttribute("user");
		return "redirect:/";
	}
}
