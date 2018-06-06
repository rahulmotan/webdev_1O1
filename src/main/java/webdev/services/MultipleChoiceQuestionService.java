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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.BaseExamQuestion;
import webdev.models.Exam;
import webdev.models.MultipleChoiceExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.MultipleChoiceQuestionRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MultipleChoiceQuestionService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	MultipleChoiceQuestionRepository mcqRepository;

	@GetMapping("/api/exam/{examId}/mcq")
	public List<BaseExamQuestion> findAllMCQExamQuestions(@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			List<BaseExamQuestion> questions = exam.getQuestions();
			Predicate<BaseExamQuestion> predicate = q -> q.getType().equals("mcq");
			questions.removeIf(predicate);
			return questions;
		}
		return new ArrayList<BaseExamQuestion>();
	}

	@PostMapping("/api/exam/{examId}/mcq")
	public MultipleChoiceExamQuestion createMCQuestion(@RequestBody MultipleChoiceExamQuestion mcq,
			@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			mcq.setExam(exam);
			return mcqRepository.save(mcq);
		}
		MultipleChoiceExamQuestion temp = new MultipleChoiceExamQuestion();
		temp.setType("mcq");
		return temp;
	}

	@DeleteMapping("/api/question/{qid}/mcq")
	public void deleteQuestion(@PathVariable("qid") int qid) {
		mcqRepository.deleteById(qid);
	}

	@PutMapping("/api/question/{qid}")
	public MultipleChoiceExamQuestion update(@PathVariable("qid") int qid,
			@RequestBody MultipleChoiceExamQuestion mcq) {
		Optional<MultipleChoiceExamQuestion> data = mcqRepository.findById(qid);
		if (data.isPresent()) {
			MultipleChoiceExamQuestion question = data.get();
			question.setExam(mcq.getExam());
			question.setCorrectOption(mcq.getCorrectOption());
			question.setOptions(mcq.getOptions());
			question.setSubtitle(mcq.getSubtitle());
			question.setPoints(mcq.getPoints());
			question.setTitle(mcq.getTitle());
			question.setType(mcq.getType());
			return mcqRepository.save(question);
		}
		MultipleChoiceExamQuestion temp = new MultipleChoiceExamQuestion();
		temp.setType("mcq");
		return temp;

	}

}
