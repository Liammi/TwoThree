package com.example.video.websocket.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.video.websocket.config.WebSocketUserInfoEncoding;
import com.example.video.websocket.pojo.SocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Controller
@ServerEndpoint(value = "/twoChat/{homeId}", encoders = {WebSocketUserInfoEncoding.class})
public class TwoChatSocketController {

    private static final ConcurrentHashMap<String, List<Session>> groupMemberInfoMap = new ConcurrentHashMap<>();

    // 收到消息调用的方法。
    @OnMessage
    public void onMessage(Session session, String message, @PathParam("homeId") String homeId) {
        List<Session> sessionList = groupMemberInfoMap.get(homeId);
        log.info(message);
        JSONObject jsonObject = JSONObject.parseObject(message);
        SocketMessage socketMessage = JSONObject.toJavaObject(jsonObject, SocketMessage.class);
        System.out.println(socketMessage);
        /*if (sessionList.size() >= 2) {//判断人数大于等于2时
            Long receiveId;
            for (Session session : sessionList
            ) {
                session
            }
            SocketMessage socketMessage = new SocketMessage();
            socketMessage.setContent(message);
            socketMessage.setCreatTime(new Date());
            socketMessage.setLaunchId(Long.valueOf(userId));
            socketMessage.setReceiveId();
        }
        User user = new User();
        user = userService.getUserById(Long.valueOf(userId));
        // 向一个组中的成员发送消息
        User finalUser = user;*/
        sessionList.forEach(item -> {
            try {
                item.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    // 建立连接调用的方法
    @OnOpen
    public void onOpen(Session session, @PathParam("homeId") String homeId) {
        List<Session> sessionList = groupMemberInfoMap.get(homeId);//判断有没有这个房间号
        if (sessionList == null) {
            sessionList = new ArrayList<>();
            groupMemberInfoMap.put(homeId, sessionList);
        }
        sessionList.add(session);
        System.out.println("Client connected");
    }

    // 关闭连接调用的方法
    @OnClose
    public void onClose(Session session, @PathParam("homeId") String homeId) {
        List<Session> sessionList = groupMemberInfoMap.get(homeId);
        sessionList.remove(session);
        System.out.println("Connection closed");
    }

    // 传输消息错误调用的方法
    @OnError
    public void OnError(Throwable error) {
        System.out.println("Connection error");
    }
}

