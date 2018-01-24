package mum.swe.mumsched.controller;

import java.lang.ProcessBuilder.Redirect;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mum.swe.mumsched.model.Entry;
import mum.swe.mumsched.service.EntryService;


//@Secured("ROLE_ADMIN")
@RequestMapping(path = "/entry")
@Controller
public class EntryController {
	@Autowired
	EntryService service;
	
//	@ModelAttribute("module")
//	String module() {
//		return "course";
//	}

	@GetMapping(value = "/")
	public String entryList(Model model) {
		model.addAttribute("entryList", service.getList());
		return "entry/entryList";
	}
	
	@GetMapping(value = "/update")
	public String update(Model model) {
		Entry e = new Entry();
		
		e.setName("Jan2018");
		e.setEntryDate(LocalDate.now());
		
		service.save(e);
		
		return "redirect:/entryList";
	}
}
