package mum.swe.mumsched.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mum.swe.mumsched.model.Student;

/**
 * @author Batjargal Bayarsaikhan (Alex)
 * Jan 23, 2018
 */
public interface StudentService {    
	Student save(Student student);
	Student findOne(Long id);
	void delete(Long id);
	Page<Student> findAll(Pageable pageable);  
}
