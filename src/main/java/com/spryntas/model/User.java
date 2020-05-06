package com.spryntas.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.spryntas.util.enums.UserRolesEnum;
import com.spryntas.util.enums.UserTypesEnum;

@JsonInclude(Include.NON_EMPTY)
public class User {

	private Integer userId;
	private String username;
	private String password;
	private String email;
	private UserRolesEnum userRole;
	private UserTypesEnum userType;
	private Boolean userStatus;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	private Timestamp deletedTime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRolesEnum getUserRole() {
		return userRole;
	}

	public void setUserRole(Integer userRole) {
		this.userRole = UserRolesEnum.valueOf(userRole);
	}

	public UserTypesEnum getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = UserTypesEnum.valueOf(userType);
	}

	public Boolean getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Boolean userStatus) {
		this.userStatus = userStatus;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Timestamp getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(Timestamp deletedTime) {
		this.deletedTime = deletedTime;
	}

}