package mum.swe.mumsched.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Brian Nguyen
 * @date Jan 31, 2018
 */
@Secured("ROLE_ADMIN")
@RequestMapping(path = "/section")
@Controller
public class SectionController {

}
