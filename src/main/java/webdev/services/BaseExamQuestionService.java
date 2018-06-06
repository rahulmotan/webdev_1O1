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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.BaseExamQuestion;
import webdev.models.Exam;
import webdev.repositories.BaseExamQuestionRepository;
import webdev.repositories.ExamRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BaseExamQuestionService {
	@Autowired
	BaseExamQuestionRepository baseExamQuestionRepository;

	@Autowired
	ExamRepository examRepository;

	@GetMapping("/api/exam/{examId}/baseExamQuestions")
	public List<BaseExamQuestion> findAllBaseExamQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			List<BaseExamQuestion> qList = exam.getQuestions();
			return qList;
		}
		return new ArrayList<BaseExamQuestion>();
	}

	@PostMapping("/api/exam/{examId}/basequestion")
	public BaseExamQuestion createQuestion(@RequestBody BaseExamQuestion question, @PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			question.setExam(exam);
			return baseExamQuestionRepository.save(question);
		}
		return new BaseExamQuestion();
	}

	@DeleteMapping("/api/question/{qid}")
	public void deleteQuestion(@PathVariable("qid") int qid) {
		baseExamQuestionRepository.deleteById(qid);
	}
}
