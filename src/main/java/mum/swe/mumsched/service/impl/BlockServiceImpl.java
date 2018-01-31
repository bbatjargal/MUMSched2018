package mum.swe.mumsched.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

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
	BlockRepository BlockRepo;
	
	@Override
	public Iterable<Block> getList(){
		return BlockRepo.fillAllWithSort(new Sort(Direction.DESC, "BlockDate"));
	}
	
	@Override
	public Block findBlockById(Long id) {
		return BlockRepo.findOne(id);
	}
	
	@Override
	public boolean hasExistsBlock(long entryId, String nameOfYear, long excludedId) {
		return false;
	}
	
	@Override
	public boolean hasSectionRef(Block block) {
		return false;
	}
	
	@Override
	public Block save(Block Block) {
		return BlockRepo.save(Block);
	}
	
	@Override
	public void delete(Block Block) {
		BlockRepo.delete(Block);
	}
}
