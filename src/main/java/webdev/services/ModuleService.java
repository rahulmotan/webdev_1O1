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
import webdev.models.Module;
import webdev.repositories.CourseRepository;
import webdev.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleService {

	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	CourseRepository courseRepository;

	@PostMapping("/api/course/{courseId}/module")
	public Module createModule(@PathVariable("courseId") int courseId, @RequestBody Module newModule) {
		Optional<Course> data = courseRepository.findById(courseId);

		if (data.isPresent()) {
			Course course = data.get();
			Module mod = new Module();
			mod.setCourse(course);
			mod.setTitle(newModule.getTitle());
			return moduleRepository.save(mod);
		}
		return null;
	}

	@GetMapping("/api/course/{courseId}/module")
	public List<Module> findAllModulesForCourse(@PathVariable("courseId") int courseId) {
		Optional<Course> courseById = courseRepository.findById(courseId);
		if (courseById.isPresent()) {
		Course course = courseById.get();
			return course.getModules();
		} else {
			return new ArrayList<Module>();
		}

	}

	@DeleteMapping("/api/module/{moduleId}")
	public void deleteModule(@PathVariable("moduleId") int moduleId) {
		moduleRepository.deleteById(moduleId);
	}

	@GetMapping("/api/module")
	public List<Module> findAllModules() {
		return (List<Module>) moduleRepository.findAll();
	}

}
