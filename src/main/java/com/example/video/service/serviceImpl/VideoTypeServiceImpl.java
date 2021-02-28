
package com.example.video.service.serviceImpl;

import com.example.video.dao.VideoTypeDao;
import com.example.video.pojo.VideoType;
import com.example.video.service.VideoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VideoTypeServiceImpl implements VideoTypeService {

	@Autowired
	private VideoTypeDao videoTypeDao;

	@Override
	public List<VideoType> listType() {
		return videoTypeDao.listType();
	}

//	public List<VideoType> showVideoType(VideoType record) {
//		return videoTypeMapper.selectList(record);
//	}


}
