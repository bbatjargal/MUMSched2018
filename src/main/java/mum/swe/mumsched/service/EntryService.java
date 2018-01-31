package mum.swe.mumsched.service;

import mum.swe.mumsched.model.Entry;

/**
 * @author Brian Nguyen
 * @date Jan 25, 2018
 */
public interface EntryService {

	Iterable<Entry> getList();

	Entry save(Entry entry);

	Entry findEntryById(Long id);

	void delete(Entry entry);

	boolean hasExistsEntryName(String name, long excludedId);

}
