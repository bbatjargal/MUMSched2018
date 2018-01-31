package mum.swe.mumsched.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToMany(mappedBy="course", cascade=CascadeType.ALL)
	private Set<Section> sectionList = new HashSet<Section>(0);
	
	@NotEmpty
	@Size(max=10)
	private String code;
	
	@NotEmpty
	@Size(max=10)
	private String name;
	
	@NotEmpty
	@Size(max=50)
	private String description;
}
