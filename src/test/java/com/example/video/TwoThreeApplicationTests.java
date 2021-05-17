package com.example.video;

import com.example.video.dao.AdminDao;
import com.example.video.dao.VideoInfoDao;
import com.example.video.dao.VideoTypeDao;
import com.example.video.pojo.VideoType;
import com.example.video.service.UserService;
import com.example.video.service.VideoTypeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TwoThreeApplicationTests {

	@Autowired
	private UserService userService;
	@Autowired
	private VideoTypeService videoType;
	@Autowired
	private VideoInfoDao videoInfoDao;
	@Autowired
	private AdminDao adminDao;
	@Test
	void contextLoads() {
		System.out.println(userService.listUser());
	}
	@Test
	void listVideo() {
		System.out.println(videoInfoDao.listAllInfoAndType());
		System.out.println(videoInfoDao.getVideoTypeById(30));
	}

}
