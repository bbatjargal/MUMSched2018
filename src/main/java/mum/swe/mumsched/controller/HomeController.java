package mum.swe.mumsched.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mum.swe.mumsched.model.User;
import mum.swe.mumsched.service.UserService;

/**
 * @author Batjargal Bayarsaikhan (Alex)
 * @date Jan 24, 2018
 */
@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(Principal currentUser, HttpServletRequest request) {     
    	
		ModelAndView modelAndView = new ModelAndView();
		
    	if(currentUser != null)
    	{
			User user = userService.findByUsername(currentUser.getName());		
			modelAndView.addObject("userName", "Welcome " + user.getUsername() + "!");    		
    	}
	
		//User user = new User();
		//modelAndView.addObject("user", user);
		modelAndView.setViewName("home/index");
		return modelAndView;
    }
}
