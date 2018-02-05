package mum.swe.mumsched.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import mum.swe.mumsched.enums.MonthEnum;

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
	
	@OneToMany(mappedBy="block", cascade=CascadeType.ALL)
	private Set<Section> sectionList;
	
	@Enumerated
    @Column(columnDefinition = "smallint")
	private MonthEnum month;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fromDate;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate toDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public Set<Section> getSectionList() {
		return sectionList;
	}

	public void setSectionList(Set<Section> sectionList) {
		this.sectionList = sectionList;
	}

	public MonthEnum getMonth() {
		return month;
	}

	public void setMonth(MonthEnum month) {
		this.month = month;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
}
