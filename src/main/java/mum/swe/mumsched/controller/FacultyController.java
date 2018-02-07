package mum.swe.mumsched.controller;

import java.security.Principal;

import mum.swe.mumsched.enums.RoleEnum;
import mum.swe.mumsched.model.User;
import mum.swe.mumsched.service.UserService;
import mum.swe.mumsched.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mum.swe.mumsched.model.Faculty;
import mum.swe.mumsched.service.CourseService;
import mum.swe.mumsched.service.FacultyService;

import javax.persistence.CascadeType;

/**
 * @author Mandakh Nyamdavaa
 * @date Jan 26, 2018
 */

@Controller
public class FacultyController {
	@Autowired
	private FacultyService facultyService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;

	@Autowired
	private CourseService courseService;

	@RequestMapping(value="/faculties", method=RequestMethod.GET)
	public String facultyProfile(Model model,Pageable pageable, Principal currentUser) {
		model.addAttribute("faculties", facultyService.findAll());
		//model.addAttribute("courses",courseService.findAll());
		return "faculty/facultyList";
	}

	@RequestMapping(value="/faculty", method = RequestMethod.GET)
	public String create(Model model){
		model.addAttribute("faculty", new Faculty());
        model.addAttribute("courses",courseService.findAll());
		return "faculty/facultyFormAdmin";
	}

    @RequestMapping(value="/faculty/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model){

	    //System.out.println(facultyService.findOne(id).getUser().getId());

	   // userService.delete(facultyService.findOne(id).getUser().getId());
        facultyService.delete(id);
        return "redirect:/faculties";
    }

    @RequestMapping(value="/faculty/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Long id, Model model){
        model.addAttribute("faculty", facultyService.findOne(id));
        model.addAttribute("courses",courseService.findAll());
        return "faculty/facultyFormAdmin";
    }

	@RequestMapping(value= {"/updateFaculty"}, method = RequestMethod.POST)
	public String updateFacultyProfile(@ModelAttribute("faculty") Faculty faculty, BindingResult result, Model model) {

        userValidator.validateAdmin(faculty.getUser(), result);

        if (result.hasErrors()) {
            model.addAttribute("faculty", faculty);
            return "faculty/facultyFormAdmin";
        }
        Faculty facultyDB= null;

	    if(faculty.getId() == null)
        {
            User user = new User();
            user.setUsername(faculty.getUser().getUsername());
            user.setFirstName(faculty.getUser().getFirstName());
            user.setLastName(faculty.getUser().getLastName());
            user.setPassword(faculty.getUser().getPassword());
            user.setRole(RoleEnum.ROLE_FACULTY);
            user = userService.save(user);

            facultyDB = facultyService.findByUsername(faculty.getUser().getUsername());
            facultyDB.setUser(user);
        }else {
            facultyDB = facultyService.findOne(faculty.getId());
            facultyDB.getUser().setFirstName(faculty.getUser().getFirstName());
            facultyDB.getUser().setLastName(faculty.getUser().getLastName());
        }
        facultyDB.setCourses(faculty.getCourses());
        facultyDB.setMonthEnums(faculty.getMonthEnums());
        facultyDB.setNumberOfSectionPerEntry(faculty.getNumberOfSectionPerEntry());
        facultyService.save(facultyDB);

		return "redirect:/faculties";
	}
}
