package com.example.video.dao;

import com.example.video.pojo.VideoType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface VideoTypeDao {

	List<VideoType> listType();

/*	int deleteByPrimaryKey(Integer id);

	int insert(VideoType record);

	int insertSelective(VideoType record);

	VideoType selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(VideoType record);

	int updateByPrimaryKey(VideoType record);

	List<VideoType> selectList(VideoType record);*/
}