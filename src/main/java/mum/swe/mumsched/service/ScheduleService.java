package mum.swe.mumsched.service;

import mum.swe.mumsched.model.Schedule;

/**
 * @author Brian Nguyen
 * @date Feb 5, 2018
 */
public interface ScheduleService {

	Iterable<Schedule> findAll();

	Schedule findOneById(long id);

	void save(Schedule item);

}
