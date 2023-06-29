package com.infosys.app.beans;

public class RequestData {
	private String isSmokingAvailable;
	private String isACAvailable;
	private String RoomType;
	public String getRoomType() {
		return RoomType;
	}
	public void setRoomType(String roomType) {
		RoomType = roomType;
	}
	public String getIsSmokingAvailable() {
		return isSmokingAvailable;
	}
	public void setIsSmokingAvailable(String isSmokingAvailable) {
		this.isSmokingAvailable = isSmokingAvailable;
	}
	public String getIsACAvailable() {
		return isACAvailable;
	}
	public void setIsACAvailable(String isACAvailable) {
		this.isACAvailable = isACAvailable;
	}



}
