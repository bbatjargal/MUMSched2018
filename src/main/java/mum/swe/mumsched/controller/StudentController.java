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

import mum.swe.mumsched.model.Student;
import mum.swe.mumsched.service.EntryService;
import mum.swe.mumsched.service.RegisterSectionService;
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
	private StudentService studentService;

	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private RegisterSectionService registerSectionService;
	
	@Autowired
	private EntryService entryService;

	@RequestMapping(value = "/studentprofile", method = RequestMethod.GET)
	public String studentProfile(Model model, Pageable pageable, Principal currentUser) {
		model.addAttribute("student", studentService.findByUsername(currentUser.getName()));
		model.addAttribute("entries", entryService.getList());
		return "student/studentForm";
	}

	@RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
	public String adminProfile(@ModelAttribute("student") Student student, BindingResult result, Principal currentUser,
			Model model) {
		userValidator.validateAdmin(student.getUser(), result);
    	
        if (result.hasErrors()) {
			model.addAttribute("student", student);
			return "student/studentForm";
		}
		
		Student studentDB = studentService.findOne(student.getId());
		studentDB.getUser().setFirstName(studentDB.getUser().getFirstName());
		studentDB.getUser().setLastName(studentDB.getUser().getLastName());
		studentDB.getUser().setPassword(studentDB.getUser().getPassword());
		studentDB.setEntry(student.getEntry());
		studentService.save(studentDB);
		//model.addAttribute("user", userRecord);
		return "redirect:/studentprofile";
	}
}
