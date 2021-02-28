package com.example.video.service.serviceImpl;

import com.example.video.dao.UserDao;
import com.example.video.pojo.User;
import com.example.video.service.UserService;
import com.example.video.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserInfoVO getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getByUserNameAndPassword(String username, String password) {
        return userDao.getByUserNameAndPassword(username,password);
    }

    @Override
    public User getUserByUserName(String name) {
        return userDao.getUserByUserName(name);
    }

    @Override
    public User getUserByNickName(String nickName) {
        return userDao.getUserByNickName(nickName);
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }
}
