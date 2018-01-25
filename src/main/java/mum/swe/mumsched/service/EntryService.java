package mum.swe.mumsched.service;

import mum.swe.mumsched.model.Entry;

public interface EntryService {

	Iterable<Entry> getList();

	Entry save(Entry entry);

	Entry findEntry(String name);

}
