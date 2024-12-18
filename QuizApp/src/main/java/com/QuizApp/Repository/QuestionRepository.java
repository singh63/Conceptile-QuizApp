package com.QuizApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.QuizApp.Entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}