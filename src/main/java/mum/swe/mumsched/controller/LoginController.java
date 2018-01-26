package mum.swe.mumsched.controller;

import java.util.Arrays;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mum.swe.mumsched.enums.RoleEnum;
import mum.swe.mumsched.model.Role;
import mum.swe.mumsched.model.User;
import mum.swe.mumsched.service.RoleService;
import mum.swe.mumsched.service.SecurityServices;
import mum.swe.mumsched.service.UserService;
import mum.swe.mumsched.view.ForgotPasswordView;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private SecurityServices securityService;

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView signin(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login/login");
		return modelAndView;
	}	
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public String signup(Model model){
        model.addAttribute("user", new User());
        return "login/signup";
	}	

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute("user") User user, 
    		BindingResult bindingResult, Model model) {
		
		if(hasError(bindingResult, user)) {
			model.addAttribute("user", user);
            return "login/signup";			
		}
		
		Role role = roleService.findByName(RoleEnum.ROLE_STUDENT.toString());		
		user.setRoles(new HashSet<Role>(Arrays.asList(role)) );
        userService.save(user);

        securityService.autologin(user.getUsername(), user.getPasswordConfirm());

        return "redirect:/";
    }
	
	@RequestMapping(value="/forgot-password", method = RequestMethod.GET)
	public String forgotPassword(Model model){
        model.addAttribute("formView", new ForgotPasswordView());
        return "login/forgotpassword";
	}			

    @RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
    public String forgotPasswordForm(@Valid @ModelAttribute("formView") ForgotPasswordView formView, 
    		BindingResult bindingResult, Model model) {
    	
		if(bindingResult.hasErrors()) {
			model.addAttribute("formView", formView);
            return "login/forgotpassword";			
		}

		model.addAttribute("email", formView.getEmail());

        return "login/forgotpasswordSent";
    }	
    
    private boolean hasError(BindingResult bindingResult, User user) {
    	
    	if(bindingResult.hasErrors()) return true;
        
        if(!user.getPassword().equals(user.getPasswordConfirm())) { 
			bindingResult.rejectValue("passwordConfirm", "error.user", "The passwords are not match.");
			return true;
        }
        
		User userExists = userService.findByUsername(user.getUsername());
		if (userExists != null) {
			bindingResult.rejectValue("username", "error.user", "User name is already registered.");
        	return true;
		}
		return false;
    }
}
