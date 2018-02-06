package mum.swe.mumsched.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mum.swe.mumsched.model.User;
import mum.swe.mumsched.service.StudentService;
import mum.swe.mumsched.service.UserService;
import mum.swe.mumsched.validator.UserValidator;

/**
 * @author Mandakh Nyamdavaa
 * @date Jan 28, 2018
 */

@Controller
public class StudentController {
	
	@Autowired
	private UserService userService;
	
	 @Autowired
	    private UserValidator userValidator;
	
	@RequestMapping(value="/studentprofile", method=RequestMethod.GET)
	public String studentProfile(Model model, Pageable pageable, Principal currentUser)
	{
		model.addAttribute("user", userService.findByUsername(currentUser.getName()));
		return "student/studentForm";
	}
	
	@RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
	public String adminProfile(@ModelAttribute("user") User user, 
			BindingResult result, Principal currentUser, Model model)  {
		User userRecord = userService.findByUsername(currentUser.getName());
    	userValidator.validateAdmin(user, result);
        System.out.println("*** hello ***");
        userRecord.setFirstName(user.getFirstName());
        userRecord.setLastName(user.getLastName());
        userRecord.setPassword(user.getPassword());
        
		user = userService.save(userRecord);
		model.addAttribute("user", userRecord);
		return "redirect:/studentprofile";
	}
}
