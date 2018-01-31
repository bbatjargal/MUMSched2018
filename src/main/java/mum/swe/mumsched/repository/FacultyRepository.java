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
	
	@Query("select f from Faculty f join f.user u WHERE u.username = :userName")
	public Faculty findByUsername(@Param("userName") String userName);
}