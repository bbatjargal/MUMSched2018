package mum.swe.mumsched.helper;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Brian Nguyen
 * @date Jan 25, 2018
 */
public final class MessageHelper {
	public static String MSG_CreateSuccess = "create.success";
	public static String MSG_UpdateSuccess = "update.success";
	public static String MSG_RemoveSuccess = "remove.success";
	
	private static MessageSource messageSource;
	
	private MessageHelper() {
		
    }
	
	private static MessageSource getMessageSource() {
		if(messageSource == null)
			messageSource = StaticContextAccessor.getBean(MessageSource.class);
		
		return messageSource;
	}
	
	/**
	 * add redirect message
	 * @param ra
	 * @param code
	 * @param args
	 */
	public static void addRedirectMessage(RedirectAttributes ra, String code, Object[] args)
	{
		ra.addFlashAttribute("message", MessageHelper.getMessage(code, args));
	}
	
	public static String getMessage(String code, Object[] args) {
		Locale current = LocaleContextHolder.getLocale();
		current = current == null ? Locale.getDefault(): current;
		
		return getMessageSource().getMessage(code, args, current);
	}
	
	public static String getMessage(String code)
	{
		return MessageHelper.getMessage(code, null);
	}
	
	public static String getUpdateSuccess() {
		return MessageHelper.getMessage(MSG_UpdateSuccess, null);
	}
	
	public static String getCreateSuccess() {
		return MessageHelper.getMessage(MSG_CreateSuccess, null);
	}
	
	public static String getRemoveSuccess() {
		return MessageHelper.getMessage(MSG_RemoveSuccess, null);
	}
}
