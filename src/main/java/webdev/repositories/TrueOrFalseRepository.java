package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.TrueOrFalseExamQuestion;

public interface TrueOrFalseRepository extends CrudRepository<TrueOrFalseExamQuestion, Integer>{

}
