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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Batjargal Bayarsaikhan (Alex)
 * @date Jan 25, 2018
 */
@Entity
@Table(name = "faculties")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    @OneToOne
    private User user;
    
    @ManyToMany(mappedBy="facultyList")
    private Set<Entry> entryList;
    
    @OneToMany(mappedBy="faculty", cascade=CascadeType.ALL)
	private Set<Section> sectionList = new HashSet<Section>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Entry> getEntryList() {
		return entryList;
	}

	public void setEntryList(Set<Entry> entryList) {
		this.entryList = entryList;
	}
}
