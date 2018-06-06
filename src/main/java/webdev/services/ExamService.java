package webdev.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Exam;
import webdev.models.Topic;
import webdev.models.Widget;
import webdev.repositories.ExamRepository;
import webdev.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamService {

	@Autowired
	ExamRepository examRepository;

	@Autowired
	TopicRepository topicRepository;

	@GetMapping("/api/topic/{topicId}/exam")
	public List<Widget> findAllExamsForTopic(@PathVariable("topicId") int topicId) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if (data.isPresent()) {
			Topic topic = data.get();
			List<Widget> widgetList = topic.getWidgets();
			Predicate<Widget> examType = w -> w.getWidgetType().equals("assignment");
			widgetList.removeIf(examType);
			return widgetList;
		}
		return new ArrayList<Widget>();
	}

	@PostMapping("/api/topic/{topicId}/exam")
	public Exam createExam(@RequestBody Exam exam, @PathVariable("topicId") int topicId) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if (data.isPresent()) {
			Topic topic = data.get();
			exam.setName("New Exam");
			exam.setTopic(topic);
			exam.setWidgetType("exam");
			return examRepository.save(exam);
		}
		return new Exam();
	}

	@DeleteMapping("/api/exam/{eid}")
	public void deleteExam(@PathVariable("eid") int eid) {
		Optional<Exam> data = examRepository.findById(eid);
		if (data.isPresent()) {
			examRepository.deleteById(eid);
		}
	}
}
