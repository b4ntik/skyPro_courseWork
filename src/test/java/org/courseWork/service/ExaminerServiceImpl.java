package org.courseWork.service;

import org.courseWork.model.question.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Nested
class ExaminerServiceImplTest {

    private ExaminerServiceImpl examinerService;
    private JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        examinerService = new ExaminerServiceImpl();
        javaQuestionService = new JavaQuestionService();
    }

    @Test
    void testCreateTestQuestions_initializesQuestions() {
        System.out.println("записываем тестовые вопросы");
        Set<Question> questions = javaQuestionService.createTestQuestions();
        System.out.println(questions);
        assertNotNull(questions);
        assertEquals(3, questions.size());
        // Проверка содержания вопросов
        assertTrue(questions.stream().anyMatch(q -> q.getQuestion().equals("Столица Великобритании?")));
        assertTrue(questions.stream().anyMatch(q -> q.getQuestion().equals("Сколько будет 2+2?")));
        assertTrue(questions.stream().anyMatch(q -> q.getQuestion().equals("Кто написал 'Идиот'? (Фамилия автора)")));
    }


    @Test
    void testGetCollectionsOfQuestions() {
        Collection<Question> questions = javaQuestionService.getCollectionsOfQuestions();
        assertEquals(3, questions.size());
    }
}

