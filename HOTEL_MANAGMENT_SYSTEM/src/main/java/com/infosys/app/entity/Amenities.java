package com.infosys.app.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Amenities")
public class Amenities {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	@Column(name="amenitiesId")
	private Integer amenitiesId;
	@Column(name="isACAvailable",nullable = false)
	private boolean isACAvailable;
	@Column(name="isSmokingAvailable",nullable = false)
	private boolean isSmokingAvailable;
	@Column(name="isWifiAvailable",nullable = false)
	private boolean isWifiAvailable;
	@Column(name="isTVAvailable",nullable = false)
	private boolean isTVAvailable;
	@Column(name="isGeyserAvailable")
	private boolean isGeyserAvailable;
	public Integer getAmenitiesId() {
		return amenitiesId;
	}
	public void setAmenitiesId(Integer amenitiesId) {
		this.amenitiesId = amenitiesId;
	}
	public boolean isACAvailable() {
		return isACAvailable;
	}
	public void setACAvailable(boolean isACAvailable) {
		this.isACAvailable = isACAvailable;
	}
	public boolean isSmokingAvailable() {
		return isSmokingAvailable;
	}
	public void setSmokingAvailable(boolean isSmokingAvailable) {
		this.isSmokingAvailable = isSmokingAvailable;
	}
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
	@Override
	public String toString() {
		return "Amenities [amenitiesId=" + amenitiesId + ", isACAvailable=" + isACAvailable + ", isSmokingAvailable="
				+ isSmokingAvailable + ", isWifiAvailable=" + isWifiAvailable + ", isTVAvailable=" + isTVAvailable
				+ ", isGeyserAvailable=" + isGeyserAvailable + "]";
	}
	
}
