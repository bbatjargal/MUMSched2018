package mum.swe.mumsched.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.swe.mumsched.model.Schedule;
import mum.swe.mumsched.repository.ScheduleViewRepository;
import mum.swe.mumsched.service.ScheduleViewService;

/**
 * @author Mandakh Nyamdavaa
 * @date Feb 06, 2018
 */
@Service
public class ScheduleViewServiceImpl implements ScheduleViewService{
	@Autowired
	ScheduleViewRepository repo;
	
	@Override
	public Schedule findOneByEntryId(Long entryId) {
		// TODO Auto-generated method stub
		return repo.findOneByEntryId(entryId);
	}
}
