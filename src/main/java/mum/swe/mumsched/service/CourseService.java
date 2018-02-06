package mum.swe.mumsched.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mum.swe.mumsched.model.Course;

public interface CourseService {
	Iterable<Course> getList();
	Course save(Course course);
	Course findOne(Long id);
	Course findOneByCode(String code);
	void delete(Long id);
	List<Course> findAll();
}
