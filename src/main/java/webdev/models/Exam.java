package webdev.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Exam extends Widget {

	@OneToMany(mappedBy = "exam")
	@JsonIgnore
	private List<BaseExamQuestion> questions;

	public List<BaseExamQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<BaseExamQuestion> questions) {
		this.questions = questions;
	}

}
