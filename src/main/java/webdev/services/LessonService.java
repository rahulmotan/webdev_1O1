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

import webdev.models.Lesson;
import webdev.models.Module;
import webdev.repositories.LessonRepository;
import webdev.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	ModuleRepository moduleRepository;

	@GetMapping("/api/lesson")
	public List<Lesson> findAllLessons() {
		return (List<Lesson>) lessonRepository.findAll();
	}

	@PostMapping("/api/course/{cid}/module/{mid}/lesson")
	public Lesson createLesson(@RequestBody Lesson newLesson, @PathVariable("cid") int cid,
			@PathVariable("mid") int mid) {
		Optional<Module> moduleData = moduleRepository.findById(mid);
		if (moduleData.isPresent()) {
			Lesson lesson = new Lesson();
			Module module = moduleData.get();
			// List<Lesson> lessonList = module.getLessons();
			lesson.setTitle(newLesson.getTitle());
			lesson.setModule(module);
			// lessonList.add(lesson);
			return lessonRepository.save(lesson);
		}
		return null;
	}

	@DeleteMapping("/api/lesson/{id}")
	public void deleteLesson(@PathVariable("id") int id) {
		lessonRepository.deleteById(id);
	}

	@GetMapping("/api/course/{cid}/module/{mid}/lesson")
	public List<Lesson> findAllLessonsForModule(@PathVariable("cid") int cid, @PathVariable("mid") int mid) {
		Optional<Module> moduleData = moduleRepository.findById(mid);
		if (moduleData.isPresent()) {
			Module module = moduleData.get();
			return (List<Lesson>) module.getLessons();
		}
		return new ArrayList<Lesson>();
	}

	@GetMapping("/api/lesson/{id}")
	public Lesson findLessonById(@PathVariable("id") int id) {
		Optional<Lesson> lessonData = lessonRepository.findById(id);
		if (lessonData.isPresent()) {
			System.out.println("-----------Lesson Present----------");
			Lesson lesson = lessonData.get();
			return lesson;
		}
		return null;
	}

}
