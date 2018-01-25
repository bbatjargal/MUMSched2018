package mum.swe.mumsched.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mum.swe.mumsched.model.Entry;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Long> {

	Entry findByName(String name);	
	
}
