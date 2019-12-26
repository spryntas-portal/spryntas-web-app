package com.spryntas.util.enums;

public enum UserTypesEnum {

	UNKNOWN(0), DEVELOPER(1), TESTER(2), INTERN(3), TRAINEE(4), TRAINER(5),
	HELPER(6), ADMIN(7), SUPERVISOR(8);

	private Integer id;

	UserTypesEnum(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public static UserTypesEnum valueOf(Integer id) {
		for (UserTypesEnum role : UserTypesEnum.values()) {
			if (role.id == id)
				return role;
		}
		return UNKNOWN;
	}

	public final boolean equals(UserTypesEnum compareEnum) {
		return this.getId() == (compareEnum.getId());
	}
}