package mum.swe.mumsched.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.swe.mumsched.model.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>  {

}
