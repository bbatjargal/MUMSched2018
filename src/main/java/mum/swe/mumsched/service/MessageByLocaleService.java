package mum.swe.mumsched.service;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Brian Nguyen
 * @date Jan 26, 2018
 * @url http://codedevstuff.blogspot.com/2015/05/spring-boot-internationalization-with.html
 * @note from advice of Alex
 */
public interface MessageByLocaleService {
	
	public static final String MESSAGE_ATTRIBUTE = "message";
	public static String MSG_CreateSuccess = "create.success";
	public static String MSG_UpdateSuccess = "update.success";
	public static String MSG_RemoveSuccess = "remove.success";

    public String getMessage(String id);

	String getMessage(String id, Object[] args);

	void addRedirectMessage(RedirectAttributes ra, String code, Object[] args);

	String getUpdateSuccess();

	String getCreateSuccess();

	String getRemoveSuccess();
}