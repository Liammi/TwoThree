package com.example.video.pojo;

import lombok.Data;

@Data
public class VideoInfo {

	private Integer id;

	private String videoName;

	private String videoUrl;

	private Integer videoTypeId;

	private VideoType videoType;

}