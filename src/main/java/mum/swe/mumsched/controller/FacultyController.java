package mum.swe.mumsched.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mum.swe.mumsched.model.Faculty;
import mum.swe.mumsched.service.CourseService;
import mum.swe.mumsched.service.FacultyService;

/**
 * @author Mandakh Nyamdavaa
 * @date Jan 26, 2018
 */


@Controller
public class FacultyController {
	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value="/facultyprofile", method=RequestMethod.GET)
	public String facultyProfile(Model model,Pageable pageable, Principal currentUser) {	
		model.addAttribute("faculty", facultyService.findByUsername(currentUser.getName()));
		model.addAttribute("courses",courseService.findAll());
		return "faculty/facultyForm";
	}
	
	@RequestMapping(value= {"/updateFaculty"}, method = RequestMethod.POST)
	public String updateFacultyProfile(@ModelAttribute("faculty") Faculty faculty, Model model) {
		
		Faculty facultyDB = facultyService.findOne(faculty.getId());
		facultyDB.getUser().setFirstName(faculty.getUser().getFirstName());
		facultyDB.getUser().setLastName(faculty.getUser().getLastName());
		facultyDB.setCourses(faculty.getCourses());
		facultyDB.setMonthEnums(faculty.getMonthEnums());
		facultyService.save(facultyDB);
		return "redirect:/facultyprofile";
	}
}
