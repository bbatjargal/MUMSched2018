package mum.swe.mumsched.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mum.swe.mumsched.model.Section;
import mum.swe.mumsched.model.Student;
import mum.swe.mumsched.service.RegisterSectionService;
import mum.swe.mumsched.service.StudentService;

/**
 * @author Mandakh Nyamdavaa
 * @date Feb 04, 2018
 */
@Controller
public class RegisterSectionController {
	@Autowired
	private RegisterSectionService registerSectionService;

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/registerforsection", method = RequestMethod.GET)
	public String registerForSection(Model model, Pageable pageable, Principal currentUser) {
		model.addAttribute("sectionList", registerSectionService.getList());
		return "section/registersectionlist";
	}

	@RequestMapping(value = "/registerforsectionpost/{id}", method = RequestMethod.GET)
	public String registerForSectionPost(@ModelAttribute("section") Section section, Model model, Principal currentUser,
			@PathVariable Long id) {
		Section sectionDB = registerSectionService.findSectionById(section.getId());
		Student studentDB = studentService.findByUsername(currentUser.getName());
		
		if (sectionDB.getStudentList() == null)
			sectionDB.setStudentList(new HashSet<Student>());
		
		sectionDB.getStudentList().add(studentDB);
		registerSectionService.save(sectionDB);
		return "redirect:/registerforsection";
	}
}
