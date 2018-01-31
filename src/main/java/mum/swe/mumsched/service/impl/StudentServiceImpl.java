package mum.swe.mumsched.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mum.swe.mumsched.model.Student;
import mum.swe.mumsched.repository.StudentRepository;
import mum.swe.mumsched.service.StudentService;

/**
 * @author Batjargal Bayarsaikhan (Alex)
 * Jan 25, 2018
 */
@Service("studentService")
public class StudentServiceImpl  implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student save(Student student) {
		return studentRepository.save(student);		
    }

	@Override
	public Page<Student> findAll(Pageable pageable) {
		return studentRepository.findAll(pageable);
	}

	@Override
	public Student findOne(Long id) {
		return studentRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		studentRepository.delete(id);
	}

	@Override
	public Student findByUsername(String userName) {
		return studentRepository.findByUsername(userName);
	}
}
