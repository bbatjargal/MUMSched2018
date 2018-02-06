package mum.swe.mumsched.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mum.swe.mumsched.service.CourseService;
import mum.swe.mumsched.service.EntryService;
import mum.swe.mumsched.service.FacultyService;

@Secured("ROLE_ADMIN")
@RequestMapping(path = "/import")
@Controller
public class ImportController {


	@Autowired
	EntryService service;
	
	@Autowired
	FacultyService facultyService;
	
	@Autowired
	CourseService courseService;

	@GetMapping("/data")
	@ResponseBody
	public ResponseEntity index(Model model) {
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
}
