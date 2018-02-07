package mum.swe.mumsched.service;

import java.util.List;
import java.util.Set;

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

	List<Section> saveAll(List<Section> Sections);
}
