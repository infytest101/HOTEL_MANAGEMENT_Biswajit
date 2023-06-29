package com.infosys.app.entity;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="bookingDetails")
public class BookingDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="bookingDetailsId")
	private int bookingDetailsId;
	@Column(name="customerName")
	private String customerName;
	@Column(name="customerEmail")
	private String customerEmail;
	@Column(name="customerMobile")
	private String customerMobile;
	private Date fromBookingDate;
	private Date toBookingDate;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="room_id")
	private Rooms rooms;
	
	public Date getFromBookingDate() {
		return fromBookingDate;
	}
	public void setFromBookingDate(Date fromDate) {
		this.fromBookingDate = fromDate;
	}
	public Date getToBookingDate() {
		return toBookingDate;
	}
	public void setToBookingDate(Date toBookingDate) {
		this.toBookingDate = toBookingDate;
	}
	
	public Rooms getRooms() {
		return rooms;
	}
	public int getBookingDetailsId() {
		return bookingDetailsId;
	}
	public void setBookingDetailsId(int bookingDetailsId) {
		this.bookingDetailsId = bookingDetailsId;
	}
	public void setRooms(Rooms rooms) {
		this.rooms = rooms;
	}
	@Override
	public String toString() {
		return "BookingDetails [bookingDetailsId=" + bookingDetailsId + ", customerName=" + customerName
				+ ", customerEmail=" + customerEmail + ", customerMobile=" + customerMobile + ", fromBookingDate="
				+ fromBookingDate + ", toBookingDate=" + toBookingDate + ", rooms=" + rooms + ", description="
				+ description + "]";
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="description")
	private String description;
	
}
