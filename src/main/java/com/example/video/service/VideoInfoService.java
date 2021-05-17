
package com.example.video.service;

import com.example.video.pojo.VideoInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoInfoService {

	List<VideoInfo> listVideoInfoAndType();

	VideoInfo getVideoInfoById(Integer id);

	void saveVideoInfo(VideoInfo videoInfo);

	String saveVideo(MultipartFile multipartFile);

	void deleteVideoInfo(Integer id);

	List<VideoInfo> searchVideoInfoLike(String s);

	void updateVideoInfo(VideoInfo videoInfo);

}
