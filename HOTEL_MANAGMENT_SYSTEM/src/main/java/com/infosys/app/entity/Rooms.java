package com.infosys.app.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="Rooms")
public class Rooms {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// @GeneratedValue(generator = "custom_id")
	// @GenericGenerator(name="custom_id",strategy=
	// "com.infosys.app.CustomGenerator")
	@Column(name = "roomId")
	private Integer roomId;
	@Column(name="roomName",length = 225,nullable = false)
	private String  roomName;
	@Column(name = "price",nullable = false)
	private int price;
	@Column(name="totalRoomsAvailable",nullable = false)
	private int totalRoomsAvailable;
	@Column(name="createDate",nullable = false)
	private Date createDate;
	@Column(name="updateDate")
	private Date updateDate;
	@Column(name="createBy",length = 225,nullable = false)
	private String  createBy;
	@Column(name="updatedBy",length = 225)
	private String  updatedBy;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roomTypeId")
	private RoomType roomType;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="amenitiesId")
    private Amenities amenities;
	@OneToOne(mappedBy = "rooms",cascade= CascadeType.ALL)
    private BookingDetails bookingDetails ;
	
	
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getTotalRoomsAvailable() {
		return totalRoomsAvailable;
	}
	public void setTotalRoomsAvailable(int totalRoomsAvailable) {
		this.totalRoomsAvailable = totalRoomsAvailable;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public RoomType getRoomType() {
		return roomType;
	}
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	public Amenities getAmenities() {
		return amenities;
	}
	public void setAmenities(Amenities amenities) {
		this.amenities = amenities;
	}
	public BookingDetails getBookingDetails() {
		return bookingDetails;
	}
	public void setBookingDetails(BookingDetails bookingDetails) {
		this.bookingDetails = bookingDetails;
	}
	/*
	 * @Override public String toString() { return "Rooms [roomId=" + roomId +
	 * ", roomName=" + roomName + ", price=" + price + ", totalRoomsAvailable=" +
	 * totalRoomsAvailable + ", createDate=" + createDate + ", updateDate=" +
	 * updateDate + ", createBy=" + createBy + ", updatedBy=" + updatedBy +
	 * ", roomType=" + roomType + ", amenities=" + amenities + ", bookingDetails=" +
	 * bookingDetails + "]"; }
	 */
}
