package mum.swe.mumsched.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.swe.mumsched.model.Entry;
import mum.swe.mumsched.repository.EntryRepository;
import mum.swe.mumsched.service.EntryService;

/**
 * @author Brian Nguyen
 * @date Jan 25, 2018
 */
@Service
public class EntryServiceImpl implements EntryService {
	@Autowired
	EntryRepository entryRepo;
	
	@Override
	public Iterable<Entry> getList(){
		return entryRepo.findAll();
	}
	
//	@Override
//	public boolean hasEntryByName(String name, Long excludeId) {
//		return entryRepo.hasEntryByName(name, excludeId);
//	}
	
	@Override
	public Entry findEntryById(Long id) {
		return entryRepo.findOne(id);
	}
	
	@Override
	public Entry save(Entry entry) {
		return entryRepo.save(entry);
	}
	
	@Override
	public void delete(Entry entry) {
		entryRepo.delete(entry);
	}
}
