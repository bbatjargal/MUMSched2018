package mum.swe.mumsched.service;

import mum.swe.mumsched.model.Section;

/**
 * @author Brian Nguyen
 * @date Jan 31, 2018
 */
public interface SectionService {

	Iterable<Section> getList();

	boolean hasExistsSection(long blockId, long courseId, long facultyId);

	boolean hasStudentRef(Section Section);

	Section save(Section Section);

	void delete(Section Section);

	Section findSectionById(Long id);
}
