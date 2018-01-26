package mum.swe.mumsched.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mum.swe.mumsched.enums.RoleEnum;
import mum.swe.mumsched.model.Faculty;
import mum.swe.mumsched.model.Role;
import mum.swe.mumsched.model.Student;
import mum.swe.mumsched.model.User;
import mum.swe.mumsched.repository.FacultyRepository;
import mum.swe.mumsched.repository.StudentRepository;
import mum.swe.mumsched.repository.UserRepository;
import mum.swe.mumsched.service.UserService;

/**
 * @author Batjargal Bayarsaikhan (Alex)
 * Jan 23, 2018
 */
@Service("userService")
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User save(User user) {

    	Faculty faculty =null;
    	Student student = null;
    	
		if(user.getId() == null)
		{
			for(Role role : user.getRoles()) {
				if(role.getName().equals(RoleEnum.ROLE_FACULTY.toString())){
					if(faculty == null) {
						faculty = new Faculty();
						//TODO: set properties
					}
				}else if(role.getName().equals(RoleEnum.ROLE_STUDENT.toString())) {
					if(student == null) {
						student = new Student();
						//TODO: set properties
					}
				}
			}
		}
		if(!user.getPassword().isEmpty())
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		user = userRepository.save(user);

		if(faculty != null) {
			faculty.setUser(user);
			facultyRepository.save(faculty);
		}
		if(student != null) {
			student.setUser(user);
			studentRepository.save(student);
		}
		
		return user;
		
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		userRepository.delete(id);
	}
}
