package mum.swe.mumsched.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import mum.swe.mumsched.model.Section;
import mum.swe.mumsched.repository.SectionRepository;
import mum.swe.mumsched.service.SectionService;

/**
 * @author Brian Nguyen
 * @date Jan 31, 2018
 */
@Service
public class SectionServiceImpl implements SectionService {
	@Autowired
	SectionRepository sectionRepo;
	
	@Override
	public Section findSectionById(Long id) {
		return sectionRepo.findOne(id);
	}
	
	@Override
	public Iterable<Section> getList(){
		return sectionRepo.fillAllWithSort(new Sort(Direction.DESC, "id"));
	}
	
	@Override
	public boolean hasExistsSection(long blockId, long courseId, long facultyId) {
		return false; 
	}
	
	@Override
	public boolean hasStudentRef(Section Section) {
		return false;
	}
	
	@Override
	public Section save(Section Section) {
		return sectionRepo.save(Section);
	}
	
	@Override
	public void delete(Section Section) {
		sectionRepo.delete(Section);
	}
}
