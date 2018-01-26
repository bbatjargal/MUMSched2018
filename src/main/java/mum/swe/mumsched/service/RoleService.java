package mum.swe.mumsched.service;

import mum.swe.mumsched.model.Role;

/**
 * @author Batjargal Bayarsaikhan (Alex)
 * Jan 23, 2018
 */
public interface RoleService {
    Role findByName(String name);
}
