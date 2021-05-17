package com.example.video.websocket.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SocketMessage {

    private Long id;
    private Long launchId;
    private Long receiveId;
    private String content;
    private Date creatTime;

}
