package mum.swe.mumsched.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mum.swe.mumsched.service.SectionService;

/**
 * @author Mandakh Nyamdavaa
 * @date Feb 04, 2018
 */
@Controller
public class RegisterSectionController {
	@Autowired
	private SectionService sectionService;
	@RequestMapping(value="/registerforsection", method=RequestMethod.GET)
	public String registerForSection(Model model, Pageable pageable, Principal currentUser) {
		model.addAttribute("sections",sectionService.getList());
		return "section/registersectionlist";
	}
}
