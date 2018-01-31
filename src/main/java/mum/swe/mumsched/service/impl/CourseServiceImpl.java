package mum.swe.mumsched.service.impl;

import org.springframework.data.domain.Sort;

import mum.swe.mumsched.model.Course;
import mum.swe.mumsched.model.Entry;
import mum.swe.mumsched.repository.CourseRepository;
import mum.swe.mumsched.repository.EntryRepository;
import mum.swe.mumsched.service.CourseService;

public class CourseServiceImpl implements CourseService{
	CourseRepository courseRepo;
	
	@Override
	public Iterable<Course> getList(){
		return courseRepo.courseList(new Sort("name"));
	}
}
