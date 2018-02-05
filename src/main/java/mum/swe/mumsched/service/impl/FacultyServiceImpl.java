package mum.swe.mumsched.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mum.swe.mumsched.model.Faculty;
import mum.swe.mumsched.repository.FacultyRepository;
import mum.swe.mumsched.service.FacultyService;

/**
 * @author Batjargal Bayarsaikhan (Alex)
 * Jan 25, 2018
 */
@Service("facultyService")
public class FacultyServiceImpl  implements FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public Faculty save(Faculty faculty) {
		return facultyRepository.save(faculty);	
    }

	@Override
	public Page<Faculty> findAll(Pageable pageable) {
		return facultyRepository.findAll(pageable);
	}
	
	@Override
	public List<Faculty> findAll() {
		return facultyRepository.findAll();
	}

	@Override
	public Faculty findOne(Long id) {
		return facultyRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		facultyRepository.delete(id);
	}

	@Override
	public Faculty findByUsername(String userName) {
		return facultyRepository.findByUsername(userName);
	}
}
