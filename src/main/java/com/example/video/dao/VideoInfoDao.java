package com.example.video.dao;

import com.example.video.pojo.VideoInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface VideoInfoDao {

	List<VideoInfo> listAllInfoAndType();

	VideoInfo getVideoTypeById(Integer id);

	List<VideoInfo> searchVideoInfoLike(String s);

	void saveVideoInfo(VideoInfo videoInfo);

	void deleteVideoInfo(Integer id);

	@Update("update video_info set video_name=#{videoName},video_url=#{videoUrl} where id=#{id}")
	void updateVideoInfo(VideoInfo videoInfo);
}