package com.infosys.app.dto;
import com.infosys.app.entity.RoomType;

import jakarta.persistence.Column;

public class RoomPriceTypeDto {
	private String price;
	private String id;
	private  RoomType roomtype;
	private  String  roomtypedata;
	private String isACAvailable;
	private String isSmokingAvailable;
	private String fromDate;
	private String toDate;
	private int totalRoomAvaliable;
	private int totalRoomBooked;
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
	public String getIsACAvailable() {
		return isACAvailable;
	}
	public void setIsACAvailable(String isACAvailable) {
		this.isACAvailable = isACAvailable;
	}
	public String getIsSmokingAvailable() {
		return isSmokingAvailable;
	}
	public void setIsSmokingAvailable(String isSmokingAvailable) {
		this.isSmokingAvailable = isSmokingAvailable;
	}
	public String getAmenties() {
		return amenties;
	}
	public void setAmenties(String amenties) {
		this.amenties = amenties;
	}
	private String amenties;
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
				+ ", totalRoomBooked=" + totalRoomBooked + ", amenties=" + amenties + "]";
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
