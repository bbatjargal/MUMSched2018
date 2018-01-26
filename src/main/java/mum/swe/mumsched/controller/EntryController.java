package mum.swe.mumsched.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mum.swe.mumsched.helper.AjaxResult;
import mum.swe.mumsched.model.Entry;
import mum.swe.mumsched.service.EntryService;
import mum.swe.mumsched.service.MessageByLocaleService;


/**
 * @author Brian Nguyen
 * @date Jan 25, 2018
 */
@Secured("ROLE_ADMIN")
@RequestMapping(path = "/entry")
@Controller
public class EntryController {
	private static final String NOT_FOUND_MESSAGE = "validate.notFoundP";
	
	@Autowired
	EntryService service;
	
	@Autowired
	MessageByLocaleService msgService;

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
	public String save(@Valid Entry entry, BindingResult result, Model model, RedirectAttributes ra) {
		boolean hasError = result.hasErrors();
		
		if(!hasError) {
			// check exists if update
			if(entry.getId() > 0 && service.findEntryById(entry.getId()) == null){
				result.addError(new FieldError("Entry", "name", 
						msgService.getMessage(NOT_FOUND_MESSAGE, new Object[] {msgService.getMessage("field.entry")})));
				hasError = true;
			}
			
			// check total fpp
			if(entry.getFpp() != entry.getFppCPT() + entry.getFppOPT()){
				result.addError(new FieldError("Entry", "fpp", msgService.getMessage("validate.invalid")));
				hasError = true;
			}
			
			// check total mpp
			if(entry.getMpp() != entry.getMppCPT() + entry.getMppOPT()){
				result.addError(new FieldError("Entry", "mpp", msgService.getMessage("validate.invalid")));
				hasError = true;
			}
			
			//valid unique entry name
			if(service.hasExistsEntryName(entry.getName(), entry.getId())) {
				result.addError(new FieldError("Entry", "name", msgService.getMessage("validate.alreadyExists")));
				hasError = true;
			}
		}
		
		// has error
		if(hasError)
		{
			return "entry/update"; 
		}
		
		// redirect message
		msgService.addRedirectMessage(ra, entry.getId() == 0 ? 
				 msgService.MSG_CreateSuccess: msgService.MSG_UpdateSuccess, null);
		
		// save entry
		service.save(entry);
		
		return "redirect:/entry/";
	}
	
	@PostMapping("/delete/{id}")
    @ResponseBody
    public AjaxResult deleteSmartphone(@PathVariable Long id) {
		Entry entry = service.findEntryById(id);
		
		if(entry == null) {
			return AjaxResult.fail(msgService.getMessage(NOT_FOUND_MESSAGE, new Object[] {msgService.getMessage("field.entry")}));
		}
		
		// TODO valid reference
		
		service.delete(entry);
		
        return AjaxResult.success(msgService.getRemoveSuccess());
    }
}
