
package com.example.video.service.serviceImpl;

import com.example.video.dao.VideoInfoDao;
import com.example.video.pojo.VideoInfo;
import com.example.video.service.VideoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VideoInfoServiceImpl implements VideoInfoService {

	@Autowired
	private VideoInfoDao videoInfoDao;

	@Override
	public List<VideoInfo> ListVideoInfoAndType() {
		return videoInfoDao.ListAllInfoAndType();
	}

	@Override
	public VideoInfo getVideoInfoById(int id) {
		return videoInfoDao.getVideoTypeById(id);
	}

	@Override
	public void saveVideoInfo(VideoInfo videoInfo) {
		videoInfoDao.saveVideoInfo(videoInfo);
	}

	@Override
	public void deleteVideoInfo(Integer id) {
		videoInfoDao.deleteVideoInfo(id);
	}

}





	/*public List<VideoInfo> getVideoInfo() {
		return videoInfoMapper.selectAll();

	}

	public int addVideoInfo(VideoInfo record) {
		return videoInfoMapper.insert(record);

	}

	public VideoInfo getVideoInfo(int id) {
		return videoInfoMapper.selectByPrimaryKey(id);

	}


	public List<VideoInfo> getVoideAll(VideoInfo videoInfo) {
		return videoInfoMapper.getVoideAll(videoInfo);
	}

	@Override
	public int addVoide(VideoInfo videoInfo) {
		return videoInfoMapper.insert(videoInfo);
	}
}*/
