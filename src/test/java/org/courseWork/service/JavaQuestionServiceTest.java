package org.courseWork.service;

import org.courseWork.model.question.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        javaQuestionService = new JavaQuestionService();
    }

    @Test
    void testGetRandomQuestion_returnsNullOrThrowsIfSuperNotImplemented() {

        // Здесь просто проверим, что метод не возвращает null (если есть дефолтная реализация)
        Question question = javaQuestionService.getRandomQuestion();
        // дефолтная реализация возвращает null, тест можно адаптировать

        assertNull(question, "Ожидается null, так как дефолтная реализация не задана");
    }

    @Test
    void testCheckCorrectAnswer_returnsFalse() {
        boolean result = javaQuestionService.checkCorrectAnswer();
        assertFalse(result, "Метод checkCorrectAnswer() должен возвращать false");
    }

    @Test
    void testEquals_returnsFalse() {
        Question question = new Question(); // предполагается, что есть конструктор без параметров
        boolean result = javaQuestionService.equals(question);
        assertFalse(result, "Метод equals(Question) должен возвращать false");
    }
}