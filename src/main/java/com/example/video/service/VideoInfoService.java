
package com.example.video.service;

import com.example.video.pojo.VideoInfo;

import java.util.List;

public interface VideoInfoService {

	List<VideoInfo> ListVideoInfoAndType();

	VideoInfo getVideoInfoById(int id);

	void saveVideoInfo(VideoInfo videoInfo);

	void deleteVideoInfo(Integer id);


/*
	public VideoInfo getVideoInfo(int id);

	public List<VideoInfo> getVoideAll(VideoInfo videoInfo);

	public int addVideoInfo(VideoInfo record);

	public int addVoide(VideoInfo videoInfo);*/
}
