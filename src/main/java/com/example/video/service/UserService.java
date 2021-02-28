package com.example.video.service;

import com.example.video.pojo.User;
import com.example.video.vo.UserInfoVO;

public interface UserService {

    UserInfoVO getUserById(Long id);

    User getByUserNameAndPassword(String username, String password);

    User getUserByUserName(String name);

    User getUserByNickName(String nickName);

    void saveUser(User user);

}


