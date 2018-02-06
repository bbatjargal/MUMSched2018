package mum.swe.mumsched.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.swe.mumsched.enums.MonthEnum;
import mum.swe.mumsched.model.Block;
import mum.swe.mumsched.model.Course;
import mum.swe.mumsched.model.Faculty;
import mum.swe.mumsched.model.Schedule;
import mum.swe.mumsched.model.Section;
import mum.swe.mumsched.repository.ScheduleRepository;
import mum.swe.mumsched.service.ScheduleService;

/**
 * @author Tam Huynh
 * @date Feb 5, 2018
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	ScheduleRepository repo;

	@Override
	public Iterable<Schedule> findAll() {
		return repo.findAll();
	}

	@Override
	public Schedule findOneById(long id) {
		return repo.findOne(id);
	}

	@Override
	public void save(Schedule item) {
		repo.save(item);
	}

	// B3 for MPP
	// Set<Section> mppSectionsB3 = new HashSet<Section>();
	// for (int i = 0; i < numberOfMppSection; i++) {
	// Section s = new Section();
	// if(courseList.size()==0) continue;
	// Course c = courseList.get(0);
	// courseList.remove(c);
	// s.setCourse(c);
	// List<Faculty> availableFacultiesForCourse = facultiesB3.stream().filter(f ->
	// f.getCourses().contains(c))
	// .collect(Collectors.toList());
	// if (availableFacultiesForCourse.size() > 0) {
	// s.setFaculty(availableFacultiesForCourse.get(0));
	// facultiesB3.remove(availableFacultiesForCourse.get(0));
	// }
	// mppSectionsB3.add(s);
	// }
	@Override
	public Block generateBlock(MonthEnum month, int numberOfSection, List<Faculty> listFacultyForBlock,
			List<Course> courses) {
		Block block = new Block();
		block.setMonth(month);

		List<Section> sections = new ArrayList<Section>();
		for (int i = 0; i < numberOfSection; i++) {
			Section section = new Section();
			for (int j = 0; j < courses.size(); j++) {
				Course course = courses.get(0);
				List<Faculty> listFacultyForCourse = listFacultyForBlock.stream()
						.filter(f -> f.getCourses().contains(course))
						.sorted(Comparator.comparing(Faculty::getNumberOfSectionPerEntry)).collect(Collectors.toList());
				if (listFacultyForCourse.size() > 0) {
					Faculty faculty = listFacultyForCourse.get(0);
					section.setCourse(course);
					section.setFaculty(faculty);
					sections.add(section);
					listFacultyForBlock.remove(faculty);
					courses.remove(course);
					faculty.setNumberOfSectionPerEntry(faculty.getNumberOfSectionPerEntry() - 1);
					break;
				}
			}
		}
		block.setSectionList(new HashSet<Section>(sections));
		return block;
	}

	@Override
	public Block generateSpecificCourseBlock(MonthEnum month, int numberOfSection, List<Faculty> listFacultyForBlock,
			Course course) {
		Block block = new Block();
		block.setMonth(month);
		List<Section> sections = new ArrayList<Section>();
		
		for (int i = 0; i < numberOfSection; i++) {
			Section section = new Section();
			List<Faculty> listFacultyForCourse = listFacultyForBlock.stream()
					.filter(f -> f.getCourses().contains(course))
					.sorted(Comparator.comparing(Faculty::getNumberOfSectionPerEntry)).collect(Collectors.toList());
			if (listFacultyForCourse.size() > 0) {
				Faculty faculty = listFacultyForCourse.get(0);
				section.setCourse(course);
				section.setFaculty(faculty);
				sections.add(section);
				listFacultyForBlock.remove(faculty);
				faculty.setNumberOfSectionPerEntry(faculty.getNumberOfSectionPerEntry() - 1);
			}
		}
		block.setSectionList(new HashSet<Section>(sections));
		return block;
	}

}
