package com.QuizApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.QuizApp.Entities.Question;
import com.QuizApp.Service.QuizService;

import java.util.Map;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/start")
    public String startQuiz() {
        return quizService.startQuizSession();
    }

    @GetMapping("/question")
    public Question getRandomQuestion() {
        return quizService.getRandomQuestion();
    }

    @PostMapping("/submit")
    public String submitAnswer(@RequestParam Long questionId, @RequestParam String answer) {
        return quizService.submitAnswer(questionId, answer);
    }

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        return quizService.getQuizStats();
    }
}