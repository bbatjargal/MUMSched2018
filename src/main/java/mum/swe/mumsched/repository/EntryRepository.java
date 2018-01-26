package mum.swe.mumsched.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import mum.swe.mumsched.model.Entry;

/**
 * @author Brian Nguyen
 * @date Jan 25, 2018
 */
@Repository
public interface EntryRepository extends CrudRepository<Entry, Long> {
	
//	@Query("select s from Student s where s.email = :email")
//	public Student findStudentByEmail(@Param("email") String email);
	
	@Query("SELECT e FROM Entry e ORDER BY :orderBy")
	public Iterable<Entry> fillAllOrderBy(@Param("orderBy") String orderBy);
	
	@Query("SELECT CASE  WHEN count(e)> 0 THEN true ELSE false END FROM Entry e WHERE e.name = :name AND e.id <> :excludedId")
	public boolean hasExistsEntryName(@Param("name") String name, @Param("excludedId") Long excludedId);
}
