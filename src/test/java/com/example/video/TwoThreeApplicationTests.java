package com.example.video;

import com.example.video.dao.AdminDao;
import com.example.video.dao.SocketMessageDao;
import com.example.video.dao.VideoInfoDao;
import com.example.video.dao.VideoTypeDao;
import com.example.video.pojo.VideoType;
import com.example.video.service.SocketMessageService;
import com.example.video.service.UserService;
import com.example.video.service.VideoTypeService;
import com.example.video.websocket.pojo.SocketMessage;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

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
	@Autowired
	private SocketMessageService socketMessageService;
	@Autowired
	private SocketMessageDao socketMessageDao;
	@Test
	void contextLoads() {
		System.out.println(userService.listUser());
	}
	@Test
	void listVideo() {
		System.out.println(videoInfoDao.listAllInfoAndType());
		System.out.println(videoInfoDao.getVideoTypeById(30));
	}
	@Test
	void testSocketMessage(){
		System.out.println(socketMessageService.listMessage(15L));
	}

}
