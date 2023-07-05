package com.infosys.app.entity;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
//@Table(name="BookingDetails")

public class BookingDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="bookingDetailsId")
	private Long bookingDetailsId;
/*	@Column(name="roomId",nullable = false)
	private int roomId;*/
	@Column(name="customerId")
	private int customerId;
	@Column(name="createDate",nullable = false)
	private Date createDate;
	@Column(name="updateDate")
	private Date updateDate;
	@Column(name="createBy",length = 225,nullable = false)
	private String createBy;
	@Column(name="updatedBy",length = 225)
	private String updatedBy;
	@Column(name="totalRoomsBooked",nullable = false)
	private int totalRoomsBooked;
	@Column(name="bookingStartDate")
	private Date bookingStartDate;
	@Column(name="bookingEndDate")
	private Date bookingEndDate;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="roomId")
	private Rooms rooms;
	
	public Long getBookingDetailsId() {
		return bookingDetailsId;
	}
	public void setBookingDetailsId(Long bookingDetailsId) {
		this.bookingDetailsId = bookingDetailsId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
	public int getTotalRoomsBooked() {
		return totalRoomsBooked;
	}
	public void setTotalRoomsBooked(int totalRoomsBooked) {
		this.totalRoomsBooked = totalRoomsBooked;
	}
	public Date getBookingStartDate() {
		return bookingStartDate;
	}
	public void setBookingStartDate(Date bookingStartDate) {
		this.bookingStartDate = bookingStartDate;
	}
	public Date getBookingEndDate() {
		return bookingEndDate;
	}
	public void setBookingEndDate(Date bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
	}
	public Rooms getRooms() {
		return rooms;
	}
	public void setRooms(Rooms rooms) {
		this.rooms = rooms;
	}

}