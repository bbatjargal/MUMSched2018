package mum.swe.mumsched.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mum.swe.mumsched.enums.RoleEnum;
import mum.swe.mumsched.model.Section;

/**
 * @author Mandakh Nyamdavaa
 * @date Feb 04, 2018
 */
public interface RegisterSectionRepository extends CrudRepository<Section, Long> {
	@Query("SELECT e FROM Section e")
	public Iterable<Section> fillAllWithSort(Sort sort);
}
