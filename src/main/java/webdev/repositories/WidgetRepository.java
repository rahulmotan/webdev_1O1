package webdev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import webdev.models.Topic;
import webdev.models.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {
	@Query("SELECT w from Widget w WHERE w.topic=:topic ORDER BY w.orderNumber")
	List<Widget> findAllWidgetsByTopicSorted(@Param("topic") Topic topic);

}
