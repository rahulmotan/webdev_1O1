package webdev.models;

import javax.persistence.Entity;

@Entity
public class Assignment extends Widget {

	private String title;
	private Integer points;
	private String description;
	private String essayAnswer;
	private String filePath;
	private String fileLink;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEssayAnswer() {
		return essayAnswer;
	}

	public void setEssayAnswer(String essayAnswer) {
		this.essayAnswer = essayAnswer;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileLink() {
		return fileLink;
	}

	public void setFileLink(String fileLink) {
		this.fileLink = fileLink;
	}

}
