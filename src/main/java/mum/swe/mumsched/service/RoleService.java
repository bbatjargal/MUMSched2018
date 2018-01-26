package mum.swe.mumsched.service;

import mum.swe.mumsched.model.Role;

public interface RoleService {
    Role findByName(String name);
}
