package mum.swe.mumsched.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mum.swe.mumsched.model.User;
import mum.swe.mumsched.service.UserService;
import mum.swe.mumsched.validator.UserValidator;

/**
 * @author Batjargal Bayarsaikhan (Alex)
 * @date Jan 25, 2018
 */
@Controller
@Secured("ROLE_ADMIN")
public class UserController {

	@Autowired
	private UserService userService;
    @Autowired
    private UserValidator userValidator;
	
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model,  Pageable pageable) {
		model.addAttribute("users", userService.findAll(pageable));
		return "user/list";
    }

	
	@RequestMapping(value="/user", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("user", new User());
		return "user/edit";
	}
	
	@RequestMapping(value="/user/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model){	
		model.addAttribute("user", userService.findOne(id));
		return "user/edit";
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String edit(@ModelAttribute("user") User user, 
			BindingResult result, Model model)  {

    	userValidator.validate(user, result);
    	
        if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "user/edit";
		}
		user = userService.save(user);
		return "redirect:/users";
	}	
	
	@RequestMapping(value="/user/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Long id, Model model){		
		userService.delete(id);
		return "redirect:/users";
	}	
}