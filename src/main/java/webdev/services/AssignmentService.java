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

import webdev.models.Assignment;
import webdev.models.Topic;
import webdev.models.Widget;
import webdev.repositories.AssignmentRepository;
import webdev.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssignmentService {
	@Autowired
	AssignmentRepository assignmentRepository;
	@Autowired
	TopicRepository topicRepository;

	@GetMapping("/api/assignment")
	public List<Assignment> findAllAssignments() {
		return (List<Assignment>) assignmentRepository.findAll();
	}

	@PostMapping("/api/topic/{topicId}/assignment")
	public Assignment createAssignment(@PathVariable("topicId") int topicId, @RequestBody Assignment agn) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if (data.isPresent()) {
			Topic topic = data.get();
			agn.setTopic(topic);
			agn.setWidgetType("assignment");
			return assignmentRepository.save(agn);
		}
		return new Assignment();
	}

	@GetMapping("/api/topic/{topicId}/assignment")
	public List<Widget> findAllAssignmentForTopic(@PathVariable("topicId") int topicId) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if (data.isPresent()) {
			Topic topic = data.get();
			List<Widget> widgetList = topic.getWidgets();
			Predicate<Widget> assignmentType = w -> w.getWidgetType().equals("exam");
			widgetList.removeIf(assignmentType);
			return topic.getWidgets();
		}
		return new ArrayList<Widget>();
	}

	@DeleteMapping("/api/assignment/{aid}")
	public void deleteAssignment(@PathVariable("aid") int aid) {
		Optional<Assignment> data = assignmentRepository.findById(aid);
		if (data.isPresent()) {
			assignmentRepository.deleteById(aid);
		}
	}
}
