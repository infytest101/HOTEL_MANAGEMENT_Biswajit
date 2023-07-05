package com.infosys.app.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

@Table(name="Customer")
public class Customer {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name="customerId")
private int customerId;
@Column(name="firstName",length = 100,nullable = false)
private String firstName;
@Column(name="middleName",length = 100)
private String  middleName;
@Column(name="lastName",length = 100,nullable = false)
private String lastName;
@Column(name="email",length = 225,nullable = false)
private String  email;
@Column(name="mobile",length = 10,nullable = false)
private int mobile;
@Column(name="age",nullable = false)
private int  age;
@Column(name="description",length = 1000,nullable = false)
private String desc;
public int getCustomerId() {
	return customerId;
}
public void setCustomerId(int customerId) {
	this.customerId = customerId;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getMiddleName() {
	return middleName;
}
public void setMiddleName(String middleName) {
	this.middleName = middleName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getMobile() {
	return mobile;
}
public void setMobile(int mobile) {
	this.mobile = mobile;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}
@Override
public String toString() {
	return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", middleName=" + middleName
			+ ", lastName=" + lastName + ", email=" + email + ", mobile=" + mobile + ", age=" + age + ", desc=" + desc
			+ "]";
}
}
