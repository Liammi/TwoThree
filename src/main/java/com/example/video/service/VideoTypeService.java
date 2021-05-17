

package com.example.video.service;

import com.example.video.pojo.VideoType;
import com.example.video.vo.VideoTypeAndCount;

import java.lang.reflect.Type;
import java.util.List;


public interface VideoTypeService {

    List<VideoType> listType();

    List<VideoTypeAndCount> listTypeAndCount();

    VideoType getVideoTypeById(Integer id);

    void saveType(VideoType type);

    void deleteType(Integer id);

    void updateVideoType(VideoType videoType);

}
