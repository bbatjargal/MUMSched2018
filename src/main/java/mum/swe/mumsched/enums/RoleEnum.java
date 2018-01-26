package mum.swe.mumsched.enums;

/**
 * @author Batjargal Bayarsaikhan (Alex)
 * Jan 25, 2018
 */
public enum RoleEnum {
	ROLE_ADMIN(1), ROLE_FACULTY(2), ROLE_STUDENT(3);
	
	@SuppressWarnings("unused")
	private final int code;
	private RoleEnum(int code){
		this.code = code;
	}
	public int getCode() {
		return code;
	}
}
