package mum.swe.mumsched.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mum.swe.mumsched.enums.MonthEnum;
import mum.swe.mumsched.helper.AjaxResult;
import mum.swe.mumsched.model.Block;
import mum.swe.mumsched.model.Course;
import mum.swe.mumsched.model.Entry;
import mum.swe.mumsched.model.Faculty;
import mum.swe.mumsched.model.Section;
import mum.swe.mumsched.repository.FacultyRepository;
import mum.swe.mumsched.service.SectionService;
import mum.swe.mumsched.service.BlockService;
import mum.swe.mumsched.service.CourseService;
import mum.swe.mumsched.service.EntryService;
import mum.swe.mumsched.service.FacultyService;
import mum.swe.mumsched.service.MessageByLocaleService;
import mum.swe.mumsched.service.ScheduleService;

/**
 * @author Brian Nguyen
 * @date Jan 31, 2018
 */
@Secured("ROLE_ADMIN")
@RequestMapping(path = "/section")
@Controller
public class SectionController {
	@Autowired
	SectionService service;
	
	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	BlockService blockService;
	
	@Autowired
	FacultyService facultyService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	MessageByLocaleService msgService;
	
	@GetMapping("/")
	public String sectionList(Model model) {
		model.addAttribute("sectionList", service.getList());
		return "section/sectionList";
	}
	
	@GetMapping("/add")
	public String newSection(Model model) {
		model.addAttribute("allSchedule", scheduleService.findAll());
		model.addAttribute("section", new Section());
		return "section/update";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("section", service.findSectionById(id));
		return "section/update";
	}
	
	@PostMapping("/getBlockList/{scheduleId}")
    @ResponseBody
    public Map<Long, MonthEnum> getBlockList(@PathVariable("scheduleId") Long scheduleId){
		return scheduleService.findOneById(scheduleId).getBlockList().stream()
			.collect(Collectors.toMap(Block::getId, Block::getMonth));
	}
	
	@PostMapping("/getFacultyList/{scheduleId}/{blockId}")
    @ResponseBody
    public Map<Long, String> getFacultyListByScheduleId(@PathVariable("scheduleId") Long scheduleId, @PathVariable("blockId") Long blockId){
		// entry's faculties
		Set<Faculty> entryFaculties = scheduleService.findOneById(scheduleId).getEntry().getFacultyList();
		
		// selected block month
		MonthEnum month = blockService.findBlockById(blockId).getMonth();
		
		// get available faculties on selected month
		List<Faculty> monthFaculties = facultyService.findAllByMonth(month);
		
		return entryFaculties.stream()
			.filter(m->monthFaculties.contains(m))
			.collect(Collectors.toMap(Faculty::getId, f->f.getUser().getFullname()));
	}
	
	@PostMapping("/getCourseList/{scheduleId}/{facultyId}")
    @ResponseBody
    public Map<Long, String> getCourseList(@PathVariable("scheduleId") Long scheduleId, @PathVariable("facultyId") Long facultyId){
		Set<Course> entryCourses = scheduleService.findOneById(scheduleId).getEntry().getCourseList();
		List<Course> facultyCourses = facultyService.findOne(facultyId).getCourses();
		
		return entryCourses.stream()
			.filter(m->facultyCourses.contains(m))
			.collect(Collectors.toMap(Course::getId, Course::getName));
	}
	
	@PostMapping("/save")
	public String save(@Valid Section section, BindingResult bindingResult, Model model, RedirectAttributes ra) {
		boolean hasError = bindingResult.hasErrors();
		
		// validate business rules
		if(!hasError) {
			// check exists if update
			if(section.getId() > 0 && service.findSectionById(section.getId()) == null){
				bindingResult.reject("month", null,
						msgService.getMessage(MessageByLocaleService.NOT_FOUND_MESSAGE, new Object[] {msgService.getMessage("field.section")}));
				hasError = true;
			}
			
//			//valid unique section name
//			if(service.hasExistsSection(section.getSchedule().getId(), section.getMonth(), section.getId())) {
//				bindingResult.rejectValue("month", null, msgService.getMessage("validate.alreadyExists"));
//				hasError = true;
//			}
		}
		
		// has error
		if(hasError)
		{
			//model.addAttribute("allEntryList", entryService.getListHadSchedule());
			model.addAttribute("section", section);
			return "section/update"; 
		}
		
		// redirect message
		msgService.addRedirectMessage(ra, section.getId() == 0 ? 
				 MessageByLocaleService.MSG_CreateSuccess: MessageByLocaleService.MSG_UpdateSuccess, null);
		
		// save section
		service.save(section);
		
		return "redirect:/section/";
	}
	
	@PostMapping("/delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable Long id) {
		Section section = service.findSectionById(id);
		
		if(section == null) {
			return AjaxResult.fail(msgService.getMessage(MessageByLocaleService.NOT_FOUND_MESSAGE, 
					new Object[] {msgService.getMessage("field.section")}));
		}
		
//		// has section ref
//		if(service.hasSectionRef(section)) {
//			return AjaxResult.fail(msgService.getMessage(MessageByLocaleService.HAS_REF_MESSAGE, 
//					new Object[] {msgService.getMessage("field.section")}));
//		}
		
		service.delete(section);
		
        return AjaxResult.success(msgService.getRemoveSuccess());
    }
}
