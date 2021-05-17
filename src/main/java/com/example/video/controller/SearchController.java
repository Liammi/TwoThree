package com.example.video.controller;

import cn.hutool.core.util.IdUtil;
import com.example.video.pojo.User;
import com.example.video.service.VideoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SearchController {

    @Autowired
    private VideoInfoService videoInfoService;

    @RequestMapping("/searchVideo")
    public String search(Model model , HttpSession httpSession,String videoName){
        model.addAttribute("infos",videoInfoService.searchVideoInfoLike(videoName));
        model.addAttribute("user",(User)httpSession.getAttribute("user"));
        model.addAttribute("UUID", IdUtil.simpleUUID());
        return "search";
    }

}
