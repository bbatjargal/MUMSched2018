package mum.swe.mumsched.enums;

public enum RoleEnum {
	ROLE_ADMIN(1), ROLE_FACULTY(2), ROLE_STUDENT(3);
	
	private final int code;
	private RoleEnum(int code){
		this.code = code;
	}

}
