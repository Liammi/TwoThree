package com.example.video.vo;

import com.example.video.pojo.VideoInfo;
import lombok.Data;

import java.util.List;

@Data
public class VideoTypeAndCount {

    private Integer id;

    private String typeName;

    private String typeDescription;

    private List<VideoInfo> listVideoInfo;

    private Integer count;

}
