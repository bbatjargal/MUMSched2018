package mum.swe.mumsched.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mum.swe.mumsched.service.BlockService;

/**
 * @author Brian Nguyen
 * @date Jan 31, 2018
 */
@Secured("ROLE_ADMIN")
@RequestMapping(path = "/block")
@Controller
public class BlockController {
	@Autowired
	BlockService service;
	
	
	@GetMapping("/")
	public String blockList(Model model) {
		//model.addAttribute("blockList", service.getList());
		return "block/blockList";
	}
}
