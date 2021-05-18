package com.example.video.service;

import com.example.video.vo.UserInfoVO;
import com.example.video.websocket.pojo.SocketMessage;

import java.util.HashMap;
import java.util.List;

public interface SocketMessageService {

    void saveSocketMessage(SocketMessage socketMessage);

    HashMap<UserInfoVO,List<SocketMessage>> listMessage(Long id);

}
