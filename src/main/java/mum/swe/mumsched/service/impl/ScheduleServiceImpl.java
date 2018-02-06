package mum.swe.mumsched.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.swe.mumsched.model.Schedule;
import mum.swe.mumsched.repository.ScheduleRepository;
import mum.swe.mumsched.service.ScheduleService;

/**
 * @author Brian Nguyen
 * @date Feb 5, 2018
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	ScheduleRepository repo;
	
	@Override
	public Iterable<Schedule> findAll(){
		return repo.findAll();
	}
	
	@Override
	public Schedule findOneById(long id) {
		return repo.findOne(id);
	}
	
	@Override
	public void save(Schedule item) {
		repo.save(item);
	}
}
