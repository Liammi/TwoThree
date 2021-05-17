package com.example.video.service.serviceImpl;

import com.example.video.dao.UserDao;
import com.example.video.pojo.User;
import com.example.video.service.UserService;
import com.example.video.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public UserInfoVO getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public UserInfoVO getUserFromCache(Long id) {
        if (id <= 0) {
            return null;
        }

        UserInfoVO user = null;
        String key = "user:" + id;

        // redis
        user = (UserInfoVO) redisTemplate.opsForValue().get(key);
        if (user != null) {
            //缓存命中
            return user;
        }

        // mysql
        user = this.getUserById(id);
        if (user != null) {
            redisTemplate.opsForValue().set(key, user, 30, TimeUnit.MINUTES);
            return user;
        }

        return null;
    }

    @Override
    public List<User> listUser() {
        return userDao.listUser();
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

    /**
     * 先更新数据库再删缓存，延迟双删
     * @param user
     */
    @Override
    public void updateUser(User user){
        userDao.updateUser(user);
        String key = "user:" + user.getId();
        redisTemplate.delete(key);
        try {
            Thread.sleep( 500 ); //睡眠延迟
        } catch (Exception e){
            e.printStackTrace();
        }
        redisTemplate.delete(key);
    }

    @Override
    public void updateUserPassword(User user) {
        userDao.updateUserPassword(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteUser(id);
    }
}
