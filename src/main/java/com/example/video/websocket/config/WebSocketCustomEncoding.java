package com.example.video.websocket.config;

import com.alibaba.fastjson.JSON;
import com.example.video.websocket.pojo.SocketMessage;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * SocketMessage编码器，将SocketMessage转为json对象给前端发送
 */
public class WebSocketCustomEncoding  implements Encoder.Text<SocketMessage> {

    @Override
    public String encode(SocketMessage object) {
        assert object!=null;
        return JSON.toJSONString(object);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

}
