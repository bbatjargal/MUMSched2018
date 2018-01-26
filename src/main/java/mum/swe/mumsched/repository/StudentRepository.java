package mum.swe.mumsched.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.swe.mumsched.model.Student;

/**
 * @author Batjargal Bayarsaikhan (Alex)
 * Jan 25, 2018
 */
@Repository("studentRepository")
public interface StudentRepository extends JpaRepository<Student, Long> {
}