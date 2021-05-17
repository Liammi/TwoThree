package com.example.video.websocket.config;

import com.alibaba.fastjson.JSON;
import com.example.video.websocket.pojo.VideoMessage;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class VideoMessageDecoder implements Decoder.Text<VideoMessage> {
    @Override
    public VideoMessage decode(String s) throws DecodeException {
        VideoMessage videoMessage = JSON.parseObject(s,VideoMessage.class);
        return videoMessage;
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
