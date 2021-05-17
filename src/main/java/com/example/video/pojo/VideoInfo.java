package com.example.video.pojo;

import lombok.Data;

public class VideoInfo {

	private Integer id;

	private String videoName;

	private String videoUrl;

	private Integer videoTypeId;

	private Boolean filed;
	//如果是则代表为iframe，否则为上传的video
	private VideoType videoType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Integer getVideoTypeId() {
		return videoTypeId;
	}

	public void setVideoTypeId(Integer videoTypeId) {
		this.videoTypeId = videoTypeId;
	}

	public Boolean getFiled() {
		return filed;
	}

	public void setFiled(Boolean filed) {
		this.filed = filed;
	}

	public VideoType getVideoType() {
		return videoType;
	}

	public void setVideoType(VideoType videoType) {
		this.videoType = videoType;
	}

	@Override
	public String toString() {
		return "VideoInfo{" +
				"id=" + id +
				", videoName='" + videoName + '\'' +
				", videoUrl='" + videoUrl + '\'' +
				", videoTypeId=" + videoTypeId +
				", filed=" + filed +
				", videoType=" + videoType +
				'}';
	}


}