package webdev.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Course;
import webdev.repositories.CourseRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseService {
	@Autowired
	CourseRepository courseRepository;

	@GetMapping("api/course")
	public List<Course> findAllCourses() {
		return (List<Course>) courseRepository.findAll();
	}

	@PostMapping("api/course")
	public Course createCourse(@RequestBody Course course) {
		return courseRepository.save(course);
	}

	@DeleteMapping("api/course/{courseId}")
	public void deleteCourse(@PathVariable("courseId") int id) {
		System.out.println("id : " + id);
		courseRepository.deleteById(id);
	}

	@GetMapping("api/course/{courseId}")
	public Course findCourseById(@PathVariable("courseId") int courseId) {
		Optional<Course> data = courseRepository.findById(courseId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}

	@PostMapping("api/courses")
	public List<Course> findAllEnrolledCourses(@RequestBody Integer[] courseIds) {
		List<Course> enrolledCourses = new ArrayList<>();
		for (int i = 0; i < courseIds.length; i++) {
			Optional<Course> data = courseRepository.findById(courseIds[i]);
			if (data.isPresent()) {
				Course ec = data.get();
				enrolledCourses.add(ec);
			}
		}
		return enrolledCourses;
	}

	@GetMapping("api/course/sorted")
	public List<Course> findAllCoursesSorted() {
		return courseRepository.findAllCoursesSorted();
	}
}
