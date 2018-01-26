package mum.swe.mumsched.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mum.swe.mumsched.model.User;

/**
 * @author Batjargal Bayarsaikhan (Alex)
 * Jan 23, 2018
 */
public interface UserService {
    User findByUsername(String username);
    
	User save(User user);
	User findOne(Long id);
	void delete(Long id);
	Page<User> findAll(Pageable pageable);  
}
