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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Topic;
import webdev.models.Widget;
import webdev.repositories.TopicRepository;
import webdev.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {

	@Autowired
	WidgetRepository widgetRepository;

	@Autowired
	TopicRepository topicRepository;

	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) widgetRepository.findAll();
	}

	@PostMapping("/api/widget/save")
	public void saveAllWidgets(@RequestBody List<Widget> widgets) {
		widgetRepository.deleteAll();
		for (Widget w : widgets) {
			widgetRepository.save(w);
		}
	}

	@GetMapping("/api/widget/{widgetId}")
	public Widget findWidgetById(@PathVariable("widgetId") int widgetId) {
		Optional<Widget> widgetData = widgetRepository.findById(widgetId);
		if (widgetData.isPresent()) {
			return widgetData.get();
		} else {
			return new Widget();
		}
	}

	@GetMapping("/api/topic/{topicId}/widgets")
	public List<Widget> findAllWidgetsForTopic(@PathVariable("topicId") int topicId) {
		Optional<Topic> topicData = topicRepository.findById(topicId);
		if (topicData.isPresent()) {
			List<Widget> widgetList = topicData.get().getWidgets();
			return widgetList;
		} else {
			return new ArrayList<Widget>();
		}
	}

	@PostMapping("/api/topic/{topicId}/widget")
	public Widget createWidget(@PathVariable("topicId") int topicId, @RequestBody Widget widget) {
		Optional<Topic> topicData = topicRepository.findById(topicId);
		if (topicData.isPresent()) {
			Topic topic = topicData.get();
			widget.setTopic(topic);
			return widgetRepository.save(widget);
		} else {
			return new Widget();
		}

	}

	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int widgetId) {
		widgetRepository.deleteById(widgetId);
	}

	@PutMapping("/api/widget/{widgetId}")
	public Widget updateWidget(@PathVariable("widgetId") int widgetId, @RequestBody Widget widget) {
		Optional<Widget> widgetData = widgetRepository.findById(widgetId);
		if (widgetData.isPresent()) {
			Widget dbWidget = widgetData.get();
			dbWidget.setHeight(widget.getHeight());
			dbWidget.setHref(widget.getHref());
			dbWidget.setListItems(widget.getListItems());
			dbWidget.setName(widget.getName());
			dbWidget.setListType(widget.getListType());
			dbWidget.setOrder_num(widget.getOrder_num());
			dbWidget.setSize(widget.getSize());
			dbWidget.setSrc(widget.getSrc());
			dbWidget.setStyle(widget.getStyle());
			dbWidget.setText(widget.getText());
			dbWidget.setWidth(widget.getWidth());
			dbWidget.setTopic(widget.getTopic());
			return widgetRepository.save(dbWidget);
		} else {
			return new Widget();
		}
	}
}
