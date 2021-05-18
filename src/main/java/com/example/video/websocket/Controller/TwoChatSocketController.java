package com.example.video.websocket.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.video.service.SocketMessageService;
import com.example.video.util.SensitiveFilter;

import com.example.video.websocket.pojo.SocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Controller
@ServerEndpoint(value = "/twoChat/{homeId}")
public class TwoChatSocketController {

    private static final ConcurrentHashMap<String, List<Session>> groupMemberInfoMap = new ConcurrentHashMap<>();
    private static SensitiveFilter sensitiveFilter;
    private static SocketMessageService socketMessageService;

    /**
     *
     * @param session
     * @param message  var message = {launchId: userId,receiveId: anotherUserId,content:value}
     * @param homeId
     */
    @OnMessage
    public void onMessage(Session session, String message, @PathParam("homeId") String homeId) {
        List<Session> sessionList = groupMemberInfoMap.get(homeId);
        JSONObject jsonObject = JSONObject.parseObject(message);
        SocketMessage socketMessage = JSONObject.toJavaObject(jsonObject, SocketMessage.class);
        socketMessage.setContent(sensitiveFilter.filter(socketMessage.getContent()));
        if (!socketMessage.getReceiveId().equals(0L)){
            socketMessage.setCreateTime(new Date());
            socketMessageService.saveSocketMessage(socketMessage);
        }
        log.info(String.valueOf(socketMessage));
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
                item.getBasicRemote().sendText(JSON.toJSONString(socketMessage));
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
        log.info("Message Client connected");
    }

    // 关闭连接调用的方法
    @OnClose
    public void onClose(Session session, @PathParam("homeId") String homeId) {
        List<Session> sessionList = groupMemberInfoMap.get(homeId);
        sessionList.remove(session);
        log.info("Message Connection closed");
        log.info("homeId: {}, sessionList size: {}", homeId, sessionList.size());
        if (sessionList.size()==0){
            groupMemberInfoMap.remove(homeId);
        }
    }

    // 传输消息错误调用的方法
    @OnError
    public void OnError(Throwable error) {
       error.printStackTrace();
        log.info("Message Connection error");
    }

    @Autowired
    public void setSensentiveFilter(SensitiveFilter sensentiveFilter){
        TwoChatSocketController.sensitiveFilter = sensentiveFilter;
    }

    @Autowired
    public void setSocketMessageService(SocketMessageService socketMessageService){
        TwoChatSocketController.socketMessageService = socketMessageService;
    }


}

