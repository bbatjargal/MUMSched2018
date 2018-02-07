package mum.swe.mumsched.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mum.swe.mumsched.model.Block;
import mum.swe.mumsched.model.Schedule;
import mum.swe.mumsched.service.ScheduleViewService;

/**
 * @author Mandakh Nyamdavaa
 * @date Fed 06, 2018
 */

@Controller
public class ScheduleViewController {

	@Autowired
	private ScheduleViewService scheduleViewService;
	
//	@RequestMapping(value="/scheduleView", method=RequestMethod.GET)
//	public String scheduleView() {
//		return "schedule/viewschedule";
//	}

	@GetMapping("/scheduleView")
	public String list(Model model) {
		System.out.println("*** Schedule ***");
		List<Schedule> schedules = scheduleViewService.findAll();
		model.addAttribute("schedules", schedules);
		return "schedule/list";
	}

	@RequestMapping(value="/scheduleView/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model){	
		System.out.println("*** Schedule ***");
		Schedule schedule = scheduleViewService.findOneByEntryId(id);
		schedule.getBlockList().stream().sorted(Comparator.comparing(Block::getMonth));
		model.addAttribute("schedule", schedule);
		return "schedule/viewschedule";
	}
}
