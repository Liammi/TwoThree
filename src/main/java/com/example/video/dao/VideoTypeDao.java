package com.example.video.dao;

import com.example.video.pojo.VideoInfo;
import com.example.video.pojo.VideoType;
import com.example.video.vo.VideoTypeAndCount;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;

@Repository
@Mapper
public interface VideoTypeDao {

	List<VideoType> listType();

	List<VideoTypeAndCount> listTypeAndCount();

	@Select("select * from video_type where id = #{id}")
	VideoType getVideoTypeById(Integer id);

	@Insert("insert into video_type(type_name, type_description) VALUES (#{typeName},#{typeDescription})")
	void saveType(VideoType type);

	@Delete("delete from video_type where id=#{id}")
	void deleteType(Integer id);

	@Update("update video_type set type_name=#{typeName},type_description=#{typeDescription} where id=#{id}")
	void updateVideoType(VideoType videoType);


}