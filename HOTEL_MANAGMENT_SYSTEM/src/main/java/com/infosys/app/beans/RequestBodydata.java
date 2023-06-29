package com.infosys.app.beans;


public class RequestBodydata {
public String formData;
public String AmenitiesjsonData;

@Override
public String toString() {
	return "RequestBodydata [formData=" + formData + ", AmenitiesjsonData=" + AmenitiesjsonData + "]";
}
public String getFormData() {
	return formData;
}
public void setFormData(String formData) {
	this.formData = formData;
}
public String getAmenitiesjsonData() {
	return AmenitiesjsonData;
}
public void setAmenitiesjsonData(String amenitiesjsonData) {
	AmenitiesjsonData = amenitiesjsonData;
}
}
