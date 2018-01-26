package mum.swe.mumsched.controller;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mum.swe.mumsched.helper.AjaxResult;
import mum.swe.mumsched.model.Entry;
import mum.swe.mumsched.service.EntryService;


/**
 * @author Brian Nguyen
 * @date Jan 25, 2018
 */
@Secured("ROLE_ADMIN")
@RequestMapping(path = "/entry")
@Controller
public class EntryController {
	@Autowired
	EntryService service;

	@GetMapping("/")
	public String entryList(Model model) {
		model.addAttribute("entryList", service.getList());
		return "entry/entryList";
	}
	
	@GetMapping("/addNew")
	public String newEntry(Model model) {
		model.addAttribute("entry", new Entry());
		return "entry/update";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("entry", service.findEntryById(id));
		return "entry/update";
	}
	
	@PostMapping("/save")
	public String save(@Valid Entry entry, BindingResult result, Model model) {
		boolean hasError = result.hasErrors();
		
		if(!hasError) {
			// check total fpp
			if(entry.getFpp() != entry.getFppCPT() + entry.getFppOPT()){
				result.addError(new FieldError("Entry", "fpp", "not valid"));
				hasError = true;
			}
			
			// check total mpp
			if(entry.getMpp() != entry.getMppCPT() + entry.getMppOPT()){
				result.addError(new FieldError("Entry", "mpp", "not valid"));
				hasError = true;
			}
			
			// TODO valid exists entry name
			// new
			if(entry.getId() == 0) {
				//TODO valid unique by name
//				if(service.findEntry(entry.getName()) != null) {
//					result.addError(new FieldError("Entry", "name", "not valid"));
//					hasError = true;
//				}
			}
			// update
			else {
				
			}
		}
		
		if(hasError)
		{
			return "entry/update"; 
		}
		
		service.save(entry);
		
		return "redirect:/entry/";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST, 
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AjaxResult deleteSmartphone(@PathVariable Long id) {
		Entry entry = service.findEntryById(id);
		
		if(entry == null) {
			// TODO show message in client
		}
		
		// TODO valid reference
		
		//service.delete(entry);
		
        return AjaxResult.success("Something wrong!!!");
    }
}
