package mum.swe.mumsched.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mum.swe.mumsched.model.Block;

/**
 * @author Brian Nguyen
 * @date Jan 31, 2018
 */
@Repository
public interface BlockRepository extends CrudRepository<Block, Long> {

}
