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
import webdev.models.FillInTheBlanksExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.FillInTheBlanksRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FillInTheBlanksQuestionService {

	@Autowired
	ExamRepository examRepository;
	@Autowired
	FillInTheBlanksRepository fibRepository;

	@GetMapping("/api/exam/{examId}/fib")
	public List<BaseExamQuestion> findAllFIBExamQuestions(@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			List<BaseExamQuestion> questions = exam.getQuestions();
			Predicate<BaseExamQuestion> predicate = q -> !(q.getType().equals("fib"));
			questions.removeIf(predicate);
			return questions;
		}
		return new ArrayList<BaseExamQuestion>();
	}

	@PostMapping("/api/exam/{examId}/fib")
	public FillInTheBlanksExamQuestion createFIBQuestion(@RequestBody FillInTheBlanksExamQuestion fib,
			@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			Exam exam = data.get();
			fib.setExam(exam);
			return fibRepository.save(fib);
		}
		FillInTheBlanksExamQuestion temp = new FillInTheBlanksExamQuestion();
		temp.setType("fib");
		return temp;
	}

	@DeleteMapping("/api/question/{qid}/fib")
	public void deleteQuestion(@PathVariable("qid") int qid) {
		fibRepository.deleteById(qid);
	}

	@PutMapping("/api/question/{qid}/fib")
	public FillInTheBlanksExamQuestion update(@PathVariable("qid") int qid,
			@RequestBody FillInTheBlanksExamQuestion fib) {
		Optional<FillInTheBlanksExamQuestion> data = fibRepository.findById(qid);
		if (data.isPresent()) {
			FillInTheBlanksExamQuestion question = data.get();
			question.setSubtitle(fib.getSubtitle());
			question.setBlanks(fib.getBlanks());
			question.setPoints(fib.getPoints());
			question.setTitle(fib.getTitle());
			question.setType(fib.getType());
			return fibRepository.save(question);
		}
		FillInTheBlanksExamQuestion temp = new FillInTheBlanksExamQuestion();
		temp.setType("fib");
		return temp;

	}
}
