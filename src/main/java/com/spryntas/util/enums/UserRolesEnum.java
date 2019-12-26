package com.spryntas.util.enums;

public enum UserRolesEnum {

	UNKNOWN(0), ROLE_USER(1), ROLE_SUPER_USER(2), ROLE_ADMIN(3), ROLE_SUPER_ADMIN(4), ORG_ADMIN(5);

	private int id;

	UserRolesEnum(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static UserRolesEnum valueOf(int id) {
		for (UserRolesEnum role : UserRolesEnum.values()) {
			if (role.id == id)
				return role;
		}
		return UNKNOWN;
	}

	public final boolean equals(UserRolesEnum compareEnum) {
		return this.getId() == (compareEnum.getId());
	}
}