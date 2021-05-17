package com.example.video.service;

import com.example.video.pojo.User;
import com.example.video.vo.UserInfoVO;

import java.util.List;

public interface UserService {

    UserInfoVO getUserById(Long id);

    UserInfoVO getUserFromCache(Long id);

    List<User> listUser();

    User getByUserNameAndPassword(String username, String password);

    User getUserByUserName(String name);

    User getUserByNickName(String nickName);

    void saveUser(User user);

    void updateUser(User user);

    void updateUserPassword(User user);

    void deleteUser(Integer id);
}


