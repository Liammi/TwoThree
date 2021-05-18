package com.example.video.websocket.Controller;

import com.example.video.websocket.config.VideoMessageDecoder;
import com.example.video.websocket.config.VideoMessageEncoder;
import com.example.video.websocket.pojo.VideoMessage;
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
@ServerEndpoint(value = "/syncVideo/{homeId}", decoders = {VideoMessageDecoder.class},encoders = {VideoMessageEncoder.class})
public class VideoSyncController {

    //保存组id->组成员的映射关系
    private static final ConcurrentHashMap<String, List<Session>> groupMemberInfoMap = new ConcurrentHashMap<>();

    @OnMessage
    public void onMessage(@PathParam("homeId") String homeId,Session session,VideoMessage videoMessage){
        List<Session> sessionList = groupMemberInfoMap.get(homeId);
        log.info(videoMessage.toString());
        for (Session s:sessionList){
            try {
                if (!s.equals(session)){
                    s.getBasicRemote().sendObject(videoMessage);
                }
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
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
}
