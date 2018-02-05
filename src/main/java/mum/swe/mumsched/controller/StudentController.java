package mum.swe.mumsched.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mum.swe.mumsched.service.StudentService;

/**
 * @author Mandakh Nyamdavaa
 * @date Jan 28, 2018
 */


@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value="/studentprofile", method=RequestMethod.GET)
	public String studentProfile(Model model, Pageable pageable, Principal currentUser)
	{
		model.addAttribute("student", studentService.findByUsername(currentUser.getName()));
		return "student/studentForm";
	}
}
