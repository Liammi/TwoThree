package com.example.video.dao;

import com.example.video.pojo.VideoType;
import com.example.video.vo.VideoTypeAndCount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;

@Repository
@Mapper
public interface VideoTypeDao {

	List<VideoType> listType();

	List<VideoTypeAndCount> listTypeAndCount();

	@Insert("insert into video_type(type_name, type_description) VALUES (#{typeName},#{typeDescription})")
	void saveType(VideoType type);

}