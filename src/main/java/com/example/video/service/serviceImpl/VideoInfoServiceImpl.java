
package com.example.video.service.serviceImpl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.example.video.dao.VideoInfoDao;
import com.example.video.enums.OssConstant;
import com.example.video.pojo.VideoInfo;
import com.example.video.service.VideoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class VideoInfoServiceImpl implements VideoInfoService {

	@Autowired
	private VideoInfoDao videoInfoDao;

	@Autowired
	private OSSClient ossClient;

	@Override
	public List<VideoInfo> listVideoInfoAndType() {
		return videoInfoDao.listAllInfoAndType();
	}

	@Override
	public VideoInfo getVideoInfoById(Integer id) {
		return videoInfoDao.getVideoTypeById(id);
	}

	@Override
	public void saveVideoInfo(VideoInfo videoInfo) {
		videoInfoDao.saveVideoInfo(videoInfo);
	}

	@Override
	public String saveVideo(MultipartFile multipartFile){
		String fileName = UUID.randomUUID().toString().replace("-","")+".mp4";
		try {
			ossClient.putObject(OssConstant.BUCKET,fileName,new ByteArrayInputStream(multipartFile.getBytes()));
		} catch (OSSException | IOException | ClientException e) {
			e.printStackTrace();
		}
		return "https://" + OssConstant.BUCKET + "." + OssConstant.END_POINT + "/" + fileName;
	}

	@Override
	public void deleteVideoInfo(Integer id) {
		videoInfoDao.deleteVideoInfo(id);
	}

	@Override
	public List<VideoInfo> searchVideoInfoLike(String s) {
		StringBuilder stringBuilder = new StringBuilder(s);
		stringBuilder.append("%");
		stringBuilder.insert(0,"%");
		return videoInfoDao.searchVideoInfoLike(stringBuilder.toString());
	}

	@Override
	public void updateVideoInfo(VideoInfo videoInfo) {
		videoInfoDao.updateVideoInfo(videoInfo);
	}

}