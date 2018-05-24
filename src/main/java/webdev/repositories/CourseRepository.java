package webdev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import webdev.models.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {

	@Query("SELECT c from Course c ORDER BY c.title")
	List<Course> findAllCoursesSorted();
}
