package mum.swe.mumsched.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Brian Nguyen
 * @date Jan 31, 2018
 */
@Entity
@Table(name="section")
public class Section {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="block_id")
	private Block block;
	
	@ManyToOne
	@JoinColumn(name="faculty_id")
	private Faculty faculty;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;
	
	private String roomLocation;
}
