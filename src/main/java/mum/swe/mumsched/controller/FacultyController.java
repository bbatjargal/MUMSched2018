/**
 * @author Mandakh Nyamdavaa
 * @date Jan 26, 2018
 */

package mum.swe.mumsched.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mum.swe.mumsched.repository.CourseRepository;
import mum.swe.mumsched.service.FacultyService;

@Controller
public class FacultyController {
	@Autowired
	private FacultyService facultyService;
	
	@RequestMapping(value="/faculties", method=RequestMethod.GET)
	public String facultyProfileList(Model model,Pageable pageable) {	
		model.addAttribute("faculties", facultyService.findAll(pageable));
		return "faculty/facultyList";
	}
	
	@RequestMapping(value="/facultyUpdate/{id}", method = RequestMethod.GET)
	public String facultyUpdate(@PathVariable Long id, Model model, Principal currentUser){	
		//List<Map<String, Object>> listCat =   CourseRepository();
//	    model.addAttribute("listCat",listCat);
		model.addAttribute("faculty", facultyService.findOne(id));
		return "faculty/facultyForm";
	}
}
