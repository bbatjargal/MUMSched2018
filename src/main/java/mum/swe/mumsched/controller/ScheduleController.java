package mum.swe.mumsched.controller;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mum.swe.mumsched.helper.AjaxResult;
import mum.swe.mumsched.model.Block;
import mum.swe.mumsched.service.BlockService;
import mum.swe.mumsched.service.EntryService;
import mum.swe.mumsched.enums.MonthEnum;
import mum.swe.mumsched.enums.RoleEnum;
import mum.swe.mumsched.model.Course;
import mum.swe.mumsched.model.Entry;
import mum.swe.mumsched.model.Faculty;
import mum.swe.mumsched.model.Section;
import mum.swe.mumsched.model.User;
import mum.swe.mumsched.service.CourseService;
import mum.swe.mumsched.service.FacultyService;
import mum.swe.mumsched.service.UserService;
import mum.swe.mumsched.validator.UserValidator;

/**
 * @author Huu Tam Huynh
 * @date Fed 5, 2018
 */
@Secured("ROLE_ADMIN")
@RequestMapping(path = "/schedule")
@Controller
public class ScheduleController {
	@Autowired
	BlockService service;

	@Autowired
	EntryService entryService;

	@Autowired
	private UserService userService;
	@Autowired
	private FacultyService facultyService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserValidator userValidator;

	@GetMapping("/generate/{id}")
	public String edit(@PathVariable("id") Long entryId, Model model) {
		// model.addAttribute("allEntryList", entryService.getList());
		Entry entry = entryService.findEntryById(entryId);
		int maxEnrollment = 25;
		int Month = entry.getEntryDate().getMonthValue();

		List<Course> courseList = entry.getCourseList().stream().sorted(Comparator.comparing(Course::getCode))
				.collect(Collectors.toList());
		Set<Faculty> facultyList = entry.getFacultyList();
		int fppCpt = entry.getFppCPT();
		int fppOpt = entry.getFppOPT();
		int mppCpt = entry.getMppCPT();
		int mppOpt = entry.getMppOPT();

		// Calculate number of Blocks
		int numberOfBlocks;
		if (fppOpt > 0) {
			numberOfBlocks = 7;
		} else if (fppCpt > 0 || mppOpt > 0) {
			numberOfBlocks = 6;
		} else {
			numberOfBlocks = 5;
		}

		// Start B1
		Block B1 = new Block();
		B1.setMonth(MonthEnum.November);
		// TODO: get all faculties available for the Block's Month
		List<Faculty> facultiesB1 = facultyList.stream().filter(f -> f.getMonthEnums().contains(B1.getMonth()))
				.collect(Collectors.toList());
		// B1 for FPP
		int numberOfFppSection = (int)(Math.ceil( (double)(fppCpt + fppOpt) / (double)maxEnrollment));
		Set<Section> fppSections = new HashSet<Section>();
		for (int i = 0; i < numberOfFppSection; i++) {
			Section s = new Section();
			Course FPP = courseService.findOneByCode("CS390");
			courseList.remove(FPP);
			s.setCourse(FPP);
			List<Faculty> availableFacultiesForCourse = facultiesB1.stream().filter(f -> f.getCourses().contains(FPP))
					.collect(Collectors.toList());
			if (availableFacultiesForCourse.size() > 0) {
				s.setFaculty(availableFacultiesForCourse.get(0));
				facultiesB1.remove(availableFacultiesForCourse.get(0));
			}
			fppSections.add(s);
		}

		

		// B1 for MPP
		int numberOfMppSection = (int)(Math.ceil((double)(mppCpt + mppOpt) / (double)maxEnrollment));
		Set<Section> mppSections = new HashSet<Section>();
		// TODO: get all faculties available for the Block's Month
		int falmpp = 0;
		for (int i = 0; i < numberOfMppSection; i++) {
			Section s = new Section();
			Course MPP = courseService.findOneByCode("CS401");
			courseList.remove(MPP);
			s.setCourse(MPP);
			List<Faculty> availableFacultiesForCourse = facultiesB1.stream().filter(f -> f.getCourses().contains(MPP))
					.collect(Collectors.toList());
			falmpp = availableFacultiesForCourse.size();
			if (availableFacultiesForCourse.size() > 0) {
				s.setFaculty(availableFacultiesForCourse.get(0));
				facultiesB1.remove(availableFacultiesForCourse.get(0));
			}
			mppSections.add(s);
		}
		//fppSections.addAll(mppSections);
		//B1.setSectionList(fppSections);

		
		model.addAttribute("numberOfFaculties", numberOfFppSection);
		model.addAttribute("fppSections", fppSections);
		model.addAttribute("mppSections", mppSections);
		//model.addAttribute("times", B1.getSectionList().size());
		return "schedule/schedule";
	}

}
