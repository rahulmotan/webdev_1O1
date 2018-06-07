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
import webdev.models.TrueOrFalseExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.TrueOrFalseRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TrueOrFalseQuestionService {

	@Autowired
	ExamRepository examRepository;
	@Autowired
	TrueOrFalseRepository tfRepository;

	@GetMapping("/api/exam/{examId}/tf")
	public List<BaseExamQuestion> findAllTFExamQuestions(@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			List<BaseExamQuestion> questions = exam.getQuestions();
			Predicate<BaseExamQuestion> predicate = q -> !(q.getType().equals("tf"));
			questions.removeIf(predicate);
			return questions;
		}
		return new ArrayList<BaseExamQuestion>();
	}

	@PostMapping("/api/exam/{examId}/tf")
	public TrueOrFalseExamQuestion createTFQuestion(@RequestBody TrueOrFalseExamQuestion tf,
			@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			tf.setExam(exam);
			return tfRepository.save(tf);
		}
		TrueOrFalseExamQuestion temp = new TrueOrFalseExamQuestion();
		temp.setType("tf");
		return temp;
	}

	@DeleteMapping("/api/question/{qid}/tf")
	public void deleteQuestion(@PathVariable("qid") int qid) {
		tfRepository.deleteById(qid);
	}

	@PutMapping("/api/question/{qid}")
	public TrueOrFalseExamQuestion update(@PathVariable("qid") int qid, @RequestBody TrueOrFalseExamQuestion tf) {
		Optional<TrueOrFalseExamQuestion> data = tfRepository.findById(qid);
		if (data.isPresent()) {
			TrueOrFalseExamQuestion question = data.get();
			question.setExam(tf.getExam());
			question.setSubtitle(tf.getSubtitle());
			question.setPoints(tf.getPoints());
			question.setTitle(tf.getTitle());
			question.setType(tf.getType());
			return tfRepository.save(question);
		}
		TrueOrFalseExamQuestion temp = new TrueOrFalseExamQuestion();
		temp.setType("tf");
		return temp;

	}
}
