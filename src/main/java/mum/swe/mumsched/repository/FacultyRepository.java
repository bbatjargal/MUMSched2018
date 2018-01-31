package mum.swe.mumsched.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mum.swe.mumsched.model.Faculty;

/**
 * @author Batjargal Bayarsaikhan (Alex)
 * Jan 25, 2018
 */
@Repository("facultyRepository")
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
	
	@Query("select f from mum.swe.mumsched.Faculty f left join mum.swe.mumsched.model.User u on f.id = u.id where u.userName = :userName")
	public Faculty findByUsername(@Param("userName") String userName);
}