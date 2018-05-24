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
import webdev.models.Topic;
import webdev.repositories.LessonRepository;
import webdev.repositories.ModuleRepository;
import webdev.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TopicService {
	@Autowired
	TopicRepository topicRepository;
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	LessonRepository lessonRepository;

	@PostMapping("/api/module/{moduleId}/lesson/{lessonId}")
	public Topic createTopic(@RequestBody Topic topic, @PathVariable("moduleId") int moduleId,
			@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> dataLesson = lessonRepository.findById(lessonId);
		if (dataLesson.isPresent()) {
			Lesson dbLesson = dataLesson.get();
			Topic dbTopic = new Topic();
			dbTopic.setTitle(topic.getTitle());
			dbTopic.setLesson(dbLesson);
			topicRepository.save(dbTopic);
		}
		return null;
	}

	@GetMapping("/api/module/{moduleId}/lesson/{lessonId}")
	public List<Topic> findAllTopicsForLesson(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> lessonData = lessonRepository.findById(lessonId);
		if (lessonData.isPresent()) {
			Lesson dbLesson = lessonData.get();
			return dbLesson.getTopics();
		}
		return new ArrayList<Topic>();
	}

	@DeleteMapping("/api/topic/{topicId}")
	public void deleteTopic(@PathVariable("topicId") int topicId) {
		topicRepository.deleteById(topicId);
	}
}
