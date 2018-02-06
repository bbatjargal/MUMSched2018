package mum.swe.mumsched.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import mum.swe.mumsched.enums.MonthEnum;
import mum.swe.mumsched.model.Block;
import mum.swe.mumsched.repository.BlockRepository;
import mum.swe.mumsched.service.BlockService;

/**
 * @author Brian Nguyen
 * @date Jan 31, 2018
 */
@Service
public class BlockServiceImpl implements BlockService {
	@Autowired
	BlockRepository blockRepo;
	
	@Override
	public Iterable<Block> getList(){
		return blockRepo.fillAllWithSort(new Sort(Direction.DESC, "month"));
	}
	
	@Override
	public Block findBlockById(Long id) {
		return blockRepo.findOne(id);
	}
	
	@Override
	public boolean hasExistsBlock(long scheduleId, MonthEnum month, long excludedId) {
		return blockRepo.hasExistsBlock(scheduleId, month, excludedId);
	}
	
	@Override
	public boolean hasSectionRef(Block block) {
		return block.getSectionList().size() > 0;
	}
	
	@Override
	public Block save(Block Block) {
		return blockRepo.save(Block);
	}
	
	@Override
	public void delete(Block Block) {
		blockRepo.delete(Block);
	}
}
