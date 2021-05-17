package com.example.video.controller.admin;

import com.example.video.dao.UserDao;
import com.example.video.pojo.User;
import com.example.video.pojo.VideoInfo;
import com.example.video.pojo.VideoType;
import com.example.video.service.UserService;
import com.example.video.service.VideoInfoService;
import com.example.video.service.VideoTypeService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminIndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private VideoInfoService videoInfoService;

    @Autowired
    private VideoTypeService videoTypeService;

    @RequestMapping("/userManage")
    public String adminManageUser(Model model){
        List<User> list = userService.listUser();
        model.addAttribute("infos",list);
        return "admin/userManage";
    }

    @RequestMapping("/videoManage")
    public String adminManageVideo(Model model){
        List<VideoInfo> list = videoInfoService.listVideoInfoAndType();
        model.addAttribute("infos",list);
        return "admin/videoManage";
    }

    @RequestMapping("/typeManage")
    public String adminManageType(Model model){
        List<VideoType> list = videoTypeService.listType();
        model.addAttribute("infos",list);
        return "admin/typeManage";
    }

    /**
     * 删除用户信息
     * @return
     */
    @RequestMapping("/deleteUser/{id}")
    public String adminDeleteUser(@PathVariable("id") Integer id){
        userService.deleteUser(id);
        return "redirect:/admin/userManage";
    }

    /**
     *  删除视频
     * @param id
     * @return
     */
    @RequestMapping("/deleteVideo/{id}")
    public String deleteVideo(@PathVariable("id") Integer id) {
        videoInfoService.deleteVideoInfo(id);
        return "redirect:/admin/videoManage";
    }

    /**
     *  删除视频类型
     * @param id
     * @return
     */
    @RequestMapping("/deleteType/{id}")
    public String deleteType(@PathVariable("id") Integer id) {
        videoTypeService.deleteType(id);
        return "redirect:/admin/typeManage";
    }
    /*************************************************修改**************************************************************/

    @GetMapping(value = "modifyVideo/{id}")
    public String modifyVideo(@PathVariable Integer id,Model model){
        model.addAttribute("video",videoInfoService.getVideoInfoById(id));
        return "admin/videoModify";
    }

    @GetMapping("modifyType/{id}")
    public String modifyType(@PathVariable Integer id,Model model){
        model.addAttribute("type",videoTypeService.getVideoTypeById(id));
        return "admin/typeModify";
    }

    @PostMapping("modifyVideo/save")
    public String modifyVideoSave(VideoInfo videoInfo){
        videoInfoService.updateVideoInfo(videoInfo);
        return "redirect:/admin/videoManage";
    }

    @PostMapping("modifyType/save")
    public String modifyTypeSave(VideoType videoType){
        videoTypeService.updateVideoType(videoType);
        return "redirect:/admin/typeManage";
    }
    /*************************************************修改**************************************************************/

    /**************************************water*****************************************************/
    @RequestMapping("/addVideo")
    public String add(Model model, HttpSession httpSession) {
        return "admin/videoAdd";
    }

    @RequestMapping("/addVideoType")
    public String addT(Model model,HttpSession httpSession) {
        return "admin/typeAdd";
    }

    @RequestMapping("/addVideoInfo")
    public String addVideoInfo(VideoInfo videoInfo, Model model) {
        videoInfoService.saveVideoInfo(videoInfo);
        return "redirect:/admin/addVideo";
    }

    @RequestMapping("/addVideoTypeInfo")
    public String saveVideo(VideoType type) {
        videoTypeService.saveType(type);
        return "redirect:/admin/addVideoType";
    }
    /**************************************water*****************************************************/


}
