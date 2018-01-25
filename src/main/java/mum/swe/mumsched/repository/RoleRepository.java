package mum.swe.mumsched.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.swe.mumsched.model.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long>{

}