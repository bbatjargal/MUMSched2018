package mum.swe.mumsched.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import mum.swe.mumsched.model.Section;
import mum.swe.mumsched.repository.RegisterSectionRepository;
import mum.swe.mumsched.repository.SectionRepository;
import mum.swe.mumsched.service.RegisterSectionService;

/**
 * @author Mandakh Nyamdavaa
 * @date Feb 04, 2018
 */
@Service
public class RegisterSectionServiceImp implements RegisterSectionService{
	@Autowired
	RegisterSectionRepository registerSectionRepo;
	
	@Override
	public Iterable<Section> getList(){
		return registerSectionRepo.fillAllWithSort(new Sort(Direction.DESC, "id"));
	}
	
	@Override
	public Section findSectionById(Long id) {
		return registerSectionRepo.findOne(id);
	}
	
	
	@Override
	public Section save(Section Section) {
		return registerSectionRepo.save(Section);
	}
}
