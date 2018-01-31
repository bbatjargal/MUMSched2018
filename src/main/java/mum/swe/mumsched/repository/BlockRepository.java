package mum.swe.mumsched.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mum.swe.mumsched.model.Block;
import mum.swe.mumsched.model.Entry;

/**
 * @author Brian Nguyen
 * @date Jan 31, 2018
 */
@Repository
public interface BlockRepository extends CrudRepository<Block, Long> {
	@Query("SELECT e FROM Block e")
	public Iterable<Block> fillAllWithSort(Sort sort);
}
