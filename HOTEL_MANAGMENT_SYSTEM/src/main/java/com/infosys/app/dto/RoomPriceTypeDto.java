package com.infosys.app.dto;
import com.infosys.app.entity.RoomType;

import jakarta.persistence.Column;

public class RoomPriceTypeDto {
	private String price;
	private String id;
	private  RoomType roomtype;
	private  String  roomtypedata;
	private boolean isACAvailable;
	private boolean isSmokingAvailable;
	private String fromDate;
	private String toDate;
	private int totalRoomAvaliable;
	private int totalRoomBooked;
	private String roomName;
	private boolean isWifiAvailable;
	private boolean isTVAvailable;
	private boolean isGeyserAvailable;
	public boolean isWifiAvailable() {
		return isWifiAvailable;
	}
	public void setWifiAvailable(boolean isWifiAvailable) {
		this.isWifiAvailable = isWifiAvailable;
	}
	public boolean isTVAvailable() {
		return isTVAvailable;
	}
	public void setTVAvailable(boolean isTVAvailable) {
		this.isTVAvailable = isTVAvailable;
	}
	public boolean isGeyserAvailable() {
		return isGeyserAvailable;
	}
	public void setGeyserAvailable(boolean isGeyserAvailable) {
		this.isGeyserAvailable = isGeyserAvailable;
	}
	public void setACAvailable(boolean isACAvailable) {
		this.isACAvailable = isACAvailable;
	}
	public void setSmokingAvailable(boolean isSmokingAvailable) {
		this.isSmokingAvailable = isSmokingAvailable;
	}

	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public int getTotalRoomAvaliable() {
		return totalRoomAvaliable;
	}
	public void setTotalRoomAvaliable(int totalRoomAvaliable) {
		this.totalRoomAvaliable = totalRoomAvaliable;
	}
	public int getTotalRoomBooked() {
		return totalRoomBooked;
	}
	public void setTotalRoomBooked(int totalRoomBooked) {
		this.totalRoomBooked = totalRoomBooked;
	}
	
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public boolean getIsACAvailable() {
		return isACAvailable;
	}
	public void setIsACAvailable(boolean isACAvailable) {
		this.isACAvailable = isACAvailable;
	}
	public boolean getIsSmokingAvailable() {
		return isSmokingAvailable;
	}
	public void setIsSmokingAvailable(boolean isSmokingAvailable) {
		this.isSmokingAvailable = isSmokingAvailable;
	}
	public String getRoomtypedata() {
		return roomtypedata;
	}
	public void setRoomtypedata(String roomtypedata) {
		this.roomtypedata = roomtypedata;
	}
	@Override
	public String toString() {
		return "RoomPriceTypeDto [price=" + price + ", id=" + id + ", roomtype=" + roomtype + ", roomtypedata="
				+ roomtypedata + ", isACAvailable=" + isACAvailable + ", isSmokingAvailable=" + isSmokingAvailable
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + ", totalRoomAvaliable=" + totalRoomAvaliable
				+ ", totalRoomBooked=" + totalRoomBooked + ", roomName=" + roomName + ", isWifiAvailable="
				+ isWifiAvailable + ", isTVAvailable=" + isTVAvailable + ", isGeyserAvailable=" + isGeyserAvailable
				+ "]";
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public RoomType getRoomtype() {
		return roomtype;
	}
	public void setRoomtype(RoomType roomtype) {
		this.roomtype = roomtype;
	}
	
}
