
package com.example.video.controller;

import java.lang.reflect.Type;
import java.util.List;

import com.example.video.dao.VideoTypeDao;
import com.example.video.pojo.VideoType;
import com.example.video.service.VideoTypeService;
import com.example.video.vo.VideoTypeAndCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VideoTypeController {
	@Autowired
	private VideoTypeService videoTypeService;

	@RequestMapping("/addVideoTypeInfo")
	public String saveVideo(VideoType type) {
		videoTypeService.saveType(type);
		return "redirect:/addVideoType";
	}


}
