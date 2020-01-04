package com.spryntas.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class Client {
	
	private Integer client_Id;
	private String name;
	private String email;
	private String functionalDomain;
	private String locality;
	private String url;
	private Date startDate;
	private Date endDate;
	private Timestamp created;
	private Timestamp deleted;
	private Timestamp updated;
	
	public Integer getClient_Id() {
		return client_Id;
	}
	public void setClient_Id(Integer client_Id) {
		this.client_Id = client_Id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFunctionalDomain() {
		return functionalDomain;
	}
	public void setFunctionalDomain(String functionalDomain) {
		this.functionalDomain = functionalDomain;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Timestamp getDeleted() {
		return deleted;
	}
	public void setDeleted(Timestamp deleted) {
		this.deleted = deleted;
	}
	public Timestamp getUpdated() {
		return updated;
	}
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}