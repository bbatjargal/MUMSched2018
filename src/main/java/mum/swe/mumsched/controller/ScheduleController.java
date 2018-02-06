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
import mum.swe.mumsched.model.Schedule;
import mum.swe.mumsched.model.Section;
import mum.swe.mumsched.model.User;
import mum.swe.mumsched.service.CourseService;
import mum.swe.mumsched.service.FacultyService;
import mum.swe.mumsched.service.ScheduleService;
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
	private ScheduleService scheduleService;
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

		List<Faculty> facultyList = entry.getFacultyList().stream().collect(Collectors.toList());
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

		int numberOfFppSection = (int) (Math.ceil((double) (fppCpt + fppOpt) / (double) maxEnrollment));
		int numberOfMppSection = (int) (Math.ceil((double) (mppCpt + mppOpt) / (double) maxEnrollment));

		// Start B1
		List<Faculty> facultiesB1 = facultyList.stream().filter(f -> f.getMonthEnums().contains(MonthEnum.November))
				.collect(Collectors.toList());
		Block B1FPP = scheduleService.generateSpecificCourseBlock(MonthEnum.November, numberOfFppSection, facultiesB1,
				courseService.findOneByCode("CS390"));
		Block B1MPP = scheduleService.generateSpecificCourseBlock(MonthEnum.November, numberOfMppSection, facultiesB1,
				courseService.findOneByCode("CS401"));

		// Start B2
		List<Faculty> facultiesB2 = facultyList.stream().filter(f -> f.getMonthEnums().contains(MonthEnum.December))
				.collect(Collectors.toList());
		
		Block B2FPP = scheduleService.generateSpecificCourseBlock(MonthEnum.December, numberOfFppSection, facultiesB2,
				courseService.findOneByCode("CS401"));
		Block B2MPP = scheduleService.generateBlock(MonthEnum.December, numberOfMppSection, facultiesB2, courseList);

		//Start B3
		List<Faculty> facultiesB3 = facultyList.stream().filter(f -> f.getMonthEnums().contains(MonthEnum.January))
				.collect(Collectors.toList());
		Block B3 = scheduleService.generateBlock(MonthEnum.January, numberOfFppSection + numberOfMppSection,
				facultiesB3, courseList);

		// final
		Schedule schedule = new Schedule();
		schedule.setBlockList(new HashSet<Block>() {
			{
				add(B1FPP);
				add(B1MPP);
				add(B2FPP);
				add(B2MPP);
				add(B3);			}
		});

		model.addAttribute("schedule", schedule);
		return "schedule/schedule";
	}

}
