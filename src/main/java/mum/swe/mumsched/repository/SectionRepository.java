package mum.swe.mumsched.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mum.swe.mumsched.model.Section;

/**
 * @author Brian Nguyen
 * @date Jan 31, 2018
 */
public interface SectionRepository extends CrudRepository<Section, Long> {
	@Query("SELECT e FROM Section e")
	public Iterable<Section> fillAllWithSort(Sort sort);
}
