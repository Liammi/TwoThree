package com.example.video.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.video.pojo.User;
import com.example.video.pojo.VideoInfo;
import com.example.video.service.VideoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @classDesc: 功能描述:修改添加删除视频信息
 */
@Controller
public class VideoInfoController {

    @Autowired
    private VideoInfoService videoInfoService;

    @RequestMapping("/video/{id}/{UUID}")
    public String showVideo(@PathVariable("id") Integer id, @PathVariable("UUID") String UUID, Model model, HttpSession httpSession) {
        VideoInfo videoInfo = videoInfoService.getVideoInfoById(id);
        model.addAttribute("info", videoInfo);
        model.addAttribute("UUID", UUID);
        model.addAttribute("user", (User) httpSession.getAttribute("user"));
        if (videoInfo.getFiled()){
            return "videoPlayer";
        }else {
            return "videoDetail";
        }
    }

    @RequestMapping("/uploadVideo")
    @ResponseBody
    public Object uploadVideo(@RequestParam("file") MultipartFile multipartFile){
        HashMap<String,String> hashMap = new HashMap<String,String>();
        String url = videoInfoService.saveVideo(multipartFile);
        hashMap.put("url",url);
        return JSONArray.toJSONString(hashMap);
    }

    @RequestMapping("/addVideoInfo")
    public String addVideoInfo(VideoInfo videoInfo, Model model) {
        videoInfoService.saveVideoInfo(videoInfo);
        return "redirect:/addVideo";
    }

}
