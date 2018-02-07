package mum.swe.mumsched.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
		Student student = studentService.findByUsername(currentUser.getName());		
		model.addAttribute("sectionList", registerSectionService.findByEntryId(student.getEntry().getId()));

		List<Long> mySections = student.getSectionList().stream()
				.mapToLong(x->x.getId()).boxed()
				.collect(Collectors.toList());
		
		model.addAttribute("mySections", mySections);
		return "section/registersectionlist";
	}

	@RequestMapping(value = "/registerforsectionpost/{id}", method = RequestMethod.GET)
	public String registerForSectionPost(@ModelAttribute("section") Section section, Model model, Principal currentUser,
			@PathVariable Long id) {
		Section sectionDB = registerSectionService.findSectionById(section.getId());
		Student studentDB = studentService.findByUsername(currentUser.getName());
		
		if (sectionDB.getStudentList() == null)
			sectionDB.setStudentList(new HashSet<Student>());
		
		if (studentDB.getSectionList() == null)
			studentDB.setSectionList(new HashSet<Section>());
		
		sectionDB.getStudentList().add(studentDB);
		registerSectionService.save(sectionDB);
		
		studentDB.getSectionList().add(sectionDB);
		studentService.save(studentDB);
		
		return "redirect:/registerforsection";
	}

	@RequestMapping(value = "/registerforsection/signout/{id}", method = RequestMethod.GET)
	public String registerForSectionSignout(@ModelAttribute("section") Section section, Model model, Principal currentUser,
			@PathVariable Long id) {
		Section sectionDB = registerSectionService.findSectionById(section.getId());
		Student studentDB = studentService.findByUsername(currentUser.getName());
		
		if (sectionDB.getStudentList() != null) {
			List<Student> students = new ArrayList<Student>(sectionDB.getStudentList());
			int len = sectionDB.getStudentList().size();
			for(int i = len-1; i >= 0; i--) {
				if(students.get(i).getId() == studentDB.getId() ) {
					students.remove(i);
				}
			}
			sectionDB.setStudentList(new HashSet(students));
		}
		
		if (studentDB.getSectionList() != null) {
			List<Section> sections = new ArrayList<Section>(studentDB.getSectionList());
			int len = studentDB.getSectionList().size();
			for(int i = len-1; i >= 0; i--) {
				if(sections.get(i).getId() == sectionDB.getId() ) {
					sections.remove(i);
				}
			}
			studentDB.setSectionList(new HashSet(sections));
		}
		
		registerSectionService.save(sectionDB);
		studentService.save(studentDB);
		
		return "redirect:/registerforsection";
	}
}
