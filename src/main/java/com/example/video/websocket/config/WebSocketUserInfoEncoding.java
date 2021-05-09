package com.example.video.websocket.config;

import com.alibaba.fastjson.JSON;
import com.example.video.vo.UserInfoVO;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.List;

/**
 * 编码器：将user信息发送到前端
 */
public class WebSocketUserInfoEncoding implements Encoder.Text<List<UserInfoVO>> {

    @Override
    public String encode(List<UserInfoVO> user) throws EncodeException {
        assert user!=null;
        return JSON.toJSONString(user);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
