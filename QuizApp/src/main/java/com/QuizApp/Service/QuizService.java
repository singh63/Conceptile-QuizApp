package com.QuizApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QuizApp.Entities.Question;
import com.QuizApp.Repository.QuestionRepository;
import com.QuizApp.Repository.UserStatRepository;

import java.util.*;

@Service
public class QuizService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserStatRepository userStatsRepository;

    private Long activeSessionUserId = 1L; // Simulated single-user system

    public String startQuizSession() {
        userStatsRepository.resetStats(activeSessionUserId);
        return "Quiz session started!";
    }

    public Question getRandomQuestion() {
        List<Question> questions = questionRepository.findAll();
        Random random = new Random();
        return questions.get(random.nextInt(questions.size()));
    }

    public String submitAnswer(Long questionId, String submittedAnswer) {
        Question question = questionRepository.findById(questionId).orElseThrow(
            () -> new IllegalArgumentException("Question not found"));

        String correctAnswer = question.getCorrectAnswer();
        if (correctAnswer == null) {
            throw new IllegalStateException("Correct answer is not set for this question");
        }

        if (correctAnswer.equalsIgnoreCase(submittedAnswer)) {
            return "Correct!";
        } else {
            return "Incorrect!";
        }
    }


    public Map<String, Object> getQuizStats() {
        return userStatsRepository.getStats(activeSessionUserId);
    }
}
