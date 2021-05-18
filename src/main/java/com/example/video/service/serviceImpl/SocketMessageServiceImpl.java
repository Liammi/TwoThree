package com.example.video.service.serviceImpl;

import com.example.video.dao.SocketMessageDao;
import com.example.video.dao.UserDao;
import com.example.video.pojo.User;
import com.example.video.service.SocketMessageService;
import com.example.video.vo.UserInfoVO;
import com.example.video.websocket.pojo.SocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SocketMessageServiceImpl implements SocketMessageService {

    @Autowired
    SocketMessageDao socketMessageDao;

    @Autowired
    UserDao userDao;

    @Override
    public void saveSocketMessage(SocketMessage socketMessage) {
        socketMessageDao.saveSocketMessage(socketMessage);
    }

    @Override
    public HashMap<UserInfoVO,List<SocketMessage>> listMessage(Long id) {
        List<Long> friendIdList = socketMessageDao.listFriendId(id);
        HashMap<UserInfoVO,List<SocketMessage>> longListHashMap = new HashMap<>();
        for(Long uid : friendIdList){
            longListHashMap.put(userDao.getUserById(uid), socketMessageDao.listSocketMessageById(id,uid));
        }
        return longListHashMap;
    }
}
