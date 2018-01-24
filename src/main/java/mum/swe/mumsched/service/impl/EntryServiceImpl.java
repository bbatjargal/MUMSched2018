package mum.swe.mumsched.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.swe.mumsched.model.Entry;
import mum.swe.mumsched.repository.EntryRepository;
import mum.swe.mumsched.service.EntryService;

@Service
public class EntryServiceImpl implements EntryService {
	@Autowired
	EntryRepository entryRepo;
	
	@Override
	public Iterable<Entry> getList(){
		return entryRepo.findAll();
	}
	
	@Override
	public Entry findEntry(String name) {
		return entryRepo.findByName(name);
	}
	
	@Override
	public Entry save(Entry entry) {
		return entryRepo.save(entry);
	}
}
