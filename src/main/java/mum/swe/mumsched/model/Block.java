package mum.swe.mumsched.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Brian Nguyen
 * @date Jan 31, 2018
 */
@Entity
@Table(name="block")
public class Block {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne()
	@JoinColumn(name="entry_id")
	private Entry entry;
	
	@Column(name="entry_id", insertable = false, updatable = false)
	private int entry_id;
	
	@OneToMany(mappedBy="block", cascade=CascadeType.ALL)
	private Set<Section> sectionList;
	
	
	private int month;
	@NotEmpty
	private String nameOfMonth;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fromDate;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate toDate;
}
