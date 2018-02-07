package mum.swe.mumsched.service;

import java.util.List;

import mum.swe.mumsched.model.Section;

/**
 * @author Mandakh Nyamdavaa
 * @date Feb 04, 2018
 */
public interface RegisterSectionService {
	Iterable<Section> getList();

	Section save(Section Section);

	Section findSectionById(Long id);
	
	Iterable<Section> getStudentSectionList();

	List<Section> findByEntryId(Long entryId);
}
