package mum.swe.mumsched.service;

import mum.swe.mumsched.model.User;

public interface UserService {

	User save(User user);
    User findByUsername(String username);
}
