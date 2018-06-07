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
import webdev.models.EssayExamQuestion;
import webdev.models.Exam;
import webdev.repositories.EssayQuestionRepository;
import webdev.repositories.ExamRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EssayQuestionService {

	@Autowired
	ExamRepository examRepository;
	@Autowired
	EssayQuestionRepository essRepository;

	@GetMapping("/api/exam/{examId}/ess")
	public List<BaseExamQuestion> findAllTFExamQuestions(@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			List<BaseExamQuestion> questions = exam.getQuestions();
			Predicate<BaseExamQuestion> predicate = q -> !(q.getType().equals("ess"));
			questions.removeIf(predicate);
			return questions;
		}
		return new ArrayList<BaseExamQuestion>();
	}

	@PostMapping("/api/exam/{examId}/ess")
	public EssayExamQuestion createTFQuestion(@RequestBody EssayExamQuestion ess, @PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			ess.setExam(exam);
			return essRepository.save(ess);
		}
		EssayExamQuestion temp = new EssayExamQuestion();
		temp.setType("ess");
		return temp;
	}

	@DeleteMapping("/api/question/{qid}/ess")
	public void deleteQuestion(@PathVariable("qid") int qid) {
		essRepository.deleteById(qid);
	}

	@PutMapping("/api/question/{qid}/ess")
	public EssayExamQuestion update(@PathVariable("qid") int qid, @RequestBody EssayExamQuestion ess) {
		Optional<EssayExamQuestion> data = essRepository.findById(qid);
		if (data.isPresent()) {
			EssayExamQuestion question = data.get();
			question.setExam(ess.getExam());
			question.setSubtitle(ess.getSubtitle());
			question.setPoints(ess.getPoints());
			question.setTitle(ess.getTitle());
			question.setType(ess.getType());
			return essRepository.save(question);
		}
		EssayExamQuestion temp = new EssayExamQuestion();
		temp.setType("ess");
		return temp;
	}
}
