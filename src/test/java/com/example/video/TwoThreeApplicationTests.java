package com.example.video;

import com.example.video.service.UserService;
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
	@Test
	void contextLoads() {
		System.out.println(userService.getUserById(2L));

	}

}
