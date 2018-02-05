package mum.swe.mumsched.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany(mappedBy="courseList")
	private Set<Entry> entryList;

	@OneToMany
	private Set<Entry> entryList;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Section> getSectionList() {
		return sectionList;
	}

	public void setSectionList(Set<Section> sectionList) {
		this.sectionList = sectionList;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Entry> getEntryList() {
		return entryList;
	}

	public void setEntryList(Set<Entry> entryList) {
		this.entryList = entryList;
	}

	@NotEmpty
	@Size(max=50)
	private String description;
}
