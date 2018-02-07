package mum.swe.mumsched.service;

import mum.swe.mumsched.model.Section;

/**
 * @author Brian Nguyen
 * @date Jan 31, 2018
 */
public interface SectionService {

	Iterable<Section> getList();

	boolean hasStudentRef(Section section);

	Section save(Section Section);

	void delete(Section Section);

	Section findSectionById(Long id);

	boolean hasExistsSection(long blockId, long facultyId, long courseId, long excludedId);

	boolean hasExistsFacultyBlock(long blockId, long facultyId, long excludedId);
}
