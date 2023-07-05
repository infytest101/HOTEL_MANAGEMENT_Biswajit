package com.infosys.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="RoomType")
public class RoomType {
@Id
//@GeneratedValue(strategy = GenerationType.AUTO)
@Column(columnDefinition ="roomTypeId")
private Integer roomTypeId;
@Column(columnDefinition="RoomType",nullable =false)
private String roomType;
public Integer getRoomTypeId() {
	return roomTypeId;
}
public void setRoomTypeId(Integer roomTypeId) {
	this.roomTypeId = roomTypeId;
}
public String getRoomType() {
	return roomType;
}
public void setRoomType(String roomType) {
	this.roomType = roomType;
}
@Override
public String toString() {
	return "RoomType [roomTypeId=" + roomTypeId + ", roomType=" + roomType + "]";
}
}
