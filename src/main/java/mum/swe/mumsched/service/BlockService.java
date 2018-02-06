package mum.swe.mumsched.service;

import mum.swe.mumsched.enums.MonthEnum;
import mum.swe.mumsched.model.Block;

/**
 * @author Brian Nguyen
 * @date Jan 31, 2018
 */
public interface BlockService {

	Iterable<Block> getList();

	Block findBlockById(Long id);

	boolean hasExistsBlock(long scheduleId, MonthEnum month, long excludedId);

	Block save(Block Block);

	void delete(Block Block);

	boolean hasSectionRef(Block block);
}
