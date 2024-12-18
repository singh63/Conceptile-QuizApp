package com.QuizApp.Repository;


import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserStatRepository {

    private final Map<Long, Map<String, Integer>> userStats = new HashMap<>();

    public void resetStats(Long userId) {
        userStats.put(userId, new HashMap<>(Map.of("correct", 0, "incorrect", 0)));
    }

    public void updateStats(Long userId, boolean isCorrect) {
        Map<String, Integer> stats = userStats.get(userId);
        if (stats == null) {
            resetStats(userId);
            stats = userStats.get(userId);
        }
        String key = isCorrect ? "correct" : "incorrect";
        stats.put(key, stats.getOrDefault(key, 0) + 1);
    }

    public Map<String, Object> getStats(Long userId) {
        Map<String, Integer> stats = userStats.getOrDefault(userId, Map.of("correct", 0, "incorrect", 0));
        Map<String, Object> result = new HashMap<>();
        result.put("userId", userId);
        result.put("correctAnswers", stats.get("correct"));
        result.put("incorrectAnswers", stats.get("incorrect"));
        
        return result;
    }
}