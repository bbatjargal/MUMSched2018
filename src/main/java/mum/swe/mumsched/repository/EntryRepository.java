package mum.swe.mumsched.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mum.swe.mumsched.model.Entry;

/**
 * @author Brian Nguyen
 * @date Jan 25, 2018
 */
@Repository
public interface EntryRepository extends CrudRepository<Entry, Long> {
}
