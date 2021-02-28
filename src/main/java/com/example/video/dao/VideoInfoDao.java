package com.example.video.dao;

import com.example.video.pojo.VideoInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface VideoInfoDao {

	List<VideoInfo> ListAllInfoAndType();

	VideoInfo getVideoTypeById(Integer id);

	void saveVideoInfo(VideoInfo videoInfo);

	void deleteVideoInfo(Integer id);

}