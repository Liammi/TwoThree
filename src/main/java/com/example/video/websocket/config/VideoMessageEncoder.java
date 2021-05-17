package com.example.video.websocket.config;

import com.alibaba.fastjson.JSON;
import com.example.video.websocket.pojo.VideoMessage;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class VideoMessageEncoder implements Encoder.Text<VideoMessage> {
    @Override
    public String encode(VideoMessage object) throws EncodeException {
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
