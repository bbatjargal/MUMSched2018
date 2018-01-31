package mum.swe.mumsched.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mum.swe.mumsched.model.Student;

/**
 * @author Batjargal Bayarsaikhan (Alex)
 * Jan 25, 2018
 */
@Repository("studentRepository")
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	@Query("select s from mum.swe.mumsched.Student s left join mum.swe.mumsched.model.User u on s.id = u.id where u.userName = :userName")
	public Student findByUsername(@Param("userName") String userName);
}