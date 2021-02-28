package com.example.video.controller;

import com.example.video.pojo.User;
import com.example.video.pojo.VideoInfo;
import com.example.video.service.VideoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @classDesc: 功能描述:修改添加删除视频信息
 */
@Controller
public class VideoInfoController {

    @Autowired
    private VideoInfoService videoInfoService;

    @RequestMapping("/video/{id}/{UUID}")
    public String showVideo(@PathVariable("id") Integer id, @PathVariable("UUID") String UUID, Model model, HttpSession httpSession) {
        model.addAttribute("info", videoInfoService.getVideoInfoById(id));
        model.addAttribute("UUID", UUID);
        model.addAttribute("user", (User) httpSession.getAttribute("user"));
        return "videoDetail";
    }

    @RequestMapping("/addVideoInfo")
    public String addVideoInfo(VideoInfo videoInfo, Model model) {
        videoInfoService.saveVideoInfo(videoInfo);
        return "redirect:/home";
    }

    @RequestMapping("/deleteVideoInfo/{id}")
    public String deleteVideoInfo(@PathVariable("id") Integer id, Model model) {
        videoInfoService.deleteVideoInfo(id);
        return "redirect:/admin/manage";
    }

}
