package com.spryntas.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class Holiday {
	private Integer holidayId;

	public Integer getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(Integer holidayId) {
		this.holidayId = holidayId;
	}

	private Date holidayDate;
	private String eventName;

	private String locality;
	private Timestamp created;
	private Timestamp updated;
	private Timestamp deleted;

	public Date getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
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

	public Timestamp getDeleted() {
		return deleted;
	}

	public void setDeleted(Timestamp deleted) {
		this.deleted = deleted;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
}
