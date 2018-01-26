package mum.swe.mumsched.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.swe.mumsched.model.User;

/**
 * @author Batjargal Bayarsaikhan (Alex)
 * Jan 23, 2018
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}