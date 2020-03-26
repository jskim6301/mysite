package com.douzone.mysite.vo;

import org.springframework.web.multipart.MultipartFile;

public class AdminVO {
	private Long no;
	private String title;
	private String welcomeMessage;
	private String profile;
	private String description;
	private MultipartFile multipartFile;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWelcomeMessage() {
		return welcomeMessage;
	}
	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	@Override
	public String toString() {
		return "AdminVO [no=" + no + ", title=" + title + ", welcomeMessage=" + welcomeMessage + ", profile=" + profile
				+ ", description=" + description + ", multipartFile=" + multipartFile + "]";
	} 
	
	
	
}
