package mum.swe.mumsched.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mum.swe.mumsched.model.Faculty;

/**
 * @author Batjargal Bayarsaikhan (Alex)
 * Jan 23, 2018
 */
public interface FacultyService {    
	Faculty save(Faculty faculty);
	Faculty findOne(Long id);
	void delete(Long id);
	Page<Faculty> findAll(Pageable pageable);  
}
