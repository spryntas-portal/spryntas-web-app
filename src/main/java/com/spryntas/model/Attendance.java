package com.spryntas.model;

import java.sql.Timestamp;

public class Attendance {

	private Integer attendance_id;
	private String empId;
	private Integer count;
	private String month;
	private Integer year;
	private Timestamp created;
	private Timestamp updated;
	
	public Integer getAttendance_id() {
		return attendance_id;
	}
	public void setAttendance_id(Integer attendance_id) {
		this.attendance_id = attendance_id;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Timestamp getUpdated() {
		return updated;
	}
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}
	
	
	
}
