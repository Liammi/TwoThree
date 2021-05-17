package com.example.video.websocket.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoMessage {

    /*
    0代表暂停
    1代表开始
    2代表调整进度
     */
    private Integer state;
    private Double time;

}
