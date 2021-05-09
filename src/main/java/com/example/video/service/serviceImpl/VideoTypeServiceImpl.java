
package com.example.video.service.serviceImpl;

import com.example.video.dao.VideoTypeDao;
import com.example.video.pojo.VideoType;
import com.example.video.service.VideoTypeService;
import com.example.video.vo.VideoTypeAndCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;


@Service
public class VideoTypeServiceImpl implements VideoTypeService {

	@Autowired
	private VideoTypeDao videoTypeDao;

	@Override
	public List<VideoType> listType() {
		return videoTypeDao.listType();
	}

	@Override
	public List<VideoTypeAndCount> listTypeAndCount() {
		List<VideoTypeAndCount> list = videoTypeDao.listTypeAndCount();
		for(int i = 0;i<list.size();i++){
			VideoTypeAndCount videoTypeAndCount = list.get(i);
			videoTypeAndCount.setCount(videoTypeAndCount.getListVideoInfo().size());
		}
		return list;
	}

	@Override
	public void saveType(VideoType type) {
		videoTypeDao.saveType(type);
	}

}
