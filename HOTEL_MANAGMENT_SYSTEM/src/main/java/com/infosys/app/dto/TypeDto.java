package com.infosys.app.dto;

import com.infosys.app.entity.RoomType;

public class TypeDto {
	private String price;
	private String id;
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
	public String getRoomtypedata() {
		return roomtypedata;
	}
	@Override
	public String toString() {
		return "TypeDto [price=" + price + ", id=" + id + ", roomtypedata=" + roomtypedata + "]";
	}
	public void setRoomtypedata(String roomtypedata) {
		this.roomtypedata = roomtypedata;
	}
	private  String  roomtypedata;
}
