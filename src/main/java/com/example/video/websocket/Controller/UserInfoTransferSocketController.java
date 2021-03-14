package com.example.video.websocket.Controller;

import com.example.video.service.UserService;
import com.example.video.vo.UserInfoVO;
import com.example.video.websocket.config.WebSocketUserInfoEncoding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Controller
@ServerEndpoint(value = "/userInfoTransfer/{homeId}/{userId}", encoders = {WebSocketUserInfoEncoding.class})
public class UserInfoTransferSocketController {

    private static UserService userService;

    // 保存组id->组成员的映射关系
    private static final ConcurrentHashMap<String, List<Session>> groupMemberInfoMap = new ConcurrentHashMap<>();

    // 收到消息调用的方法，群成员发送消息
    @OnMessage
    public void onMessage(@PathParam("homeId") String homeId,
                          @PathParam("userId") String userId, String message) {

    }

    // 建立连接调用的方法，群成员加入，根据房间号设置session，一个session就代表有一个连接
    @OnOpen
    public void onOpen(Session session, @PathParam("homeId") String homeId, @PathParam("userId") String userId) {
        UserInfoVO us;
        List<UserInfoVO> userInfoVOList = new ArrayList<>();
        List<Session> sessionList = groupMemberInfoMap.get(homeId);//判断有没有这个房间号
        if (sessionList == null) {
            sessionList = new ArrayList<>();
            groupMemberInfoMap.put(homeId, sessionList);
        }
        sessionList.add(session);
        log.info("UserInfo Connection connected");
        log.info(session.getId());
        log.info("homeId: {}, sessionList size: {}", homeId, sessionList.size());

        for (Session s : sessionList) {
            Map<String, String> stringMap = s.getPathParameters();
            us = userService.getUserById(Long.valueOf(stringMap.get("userId")));
            userInfoVOList.add(us);
            log.info(us.toString());
        }

        for (Session s : sessionList) {
            try {
                s.getBasicRemote().sendObject(userInfoVOList);
                log.info(userInfoVOList.toString());
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
    }

    // 关闭连接调用的方法，群成员退出
    @OnClose
    public void onClose(Session session, @PathParam("homeId") String homeId) {
        UserInfoVO us;
        List<UserInfoVO> userInfoVOList = new ArrayList<>();
        List<Session> sessionList = groupMemberInfoMap.get(homeId);
        sessionList.remove(session);

        for (Session s : sessionList) {//得到user的list集合
            Map<String, String> stringMap = s.getPathParameters();
            us = userService.getUserById(Long.valueOf(stringMap.get("userId")));
            userInfoVOList.add(us);
            log.info(us.toString());
        }

        for (Session s : sessionList) {//向前端发送当前用户集合
            try {
                s.getBasicRemote().sendObject(userInfoVOList);
                log.info(userInfoVOList.toString());
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
        log.info("UserInfo Connection closed");
        log.info("homeId: {}, sessionList size: {}", homeId, sessionList.size());
        if(sessionList.size()==0){
            groupMemberInfoMap.remove(homeId);
        }
    }

    // 传输消息错误调用的方法
    @OnError
    public void OnError(Throwable error) {
        log.info("UserInfo Connection error");
    }

    //TODO:Service自动注入
    @Autowired
    public void setUserService(UserService userService) {
        UserInfoTransferSocketController.userService = userService;

    }

}
