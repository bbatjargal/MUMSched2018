package mum.swe.mumsched.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.swe.mumsched.model.Role;
import mum.swe.mumsched.repository.RoleRepository;
import mum.swe.mumsched.service.RoleService;

@Service("roleService")
public class RoleServiceImpl  implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}
}
