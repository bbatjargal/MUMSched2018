package mum.swe.mumsched.service;

import mum.swe.mumsched.model.Schedule;

public interface ScheduleViewService {
	Schedule findOneByEntryId(Long entryId);
}
