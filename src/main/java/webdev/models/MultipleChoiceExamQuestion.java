package webdev.models;

import javax.persistence.Entity;

@Entity
public class MultipleChoiceExamQuestion extends BaseExamQuestion {

	private String options;
	private Integer correctOption;

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public Integer getCorrectOption() {
		return correctOption;
	}

	public void setCorrectOption(Integer correctOption) {
		this.correctOption = correctOption;
	}

}
