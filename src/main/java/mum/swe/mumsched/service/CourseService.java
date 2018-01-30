package mum.swe.mumsched.service;

import mum.swe.mumsched.model.Course;

public interface CourseService {
	Iterable<Course> getList();
}
