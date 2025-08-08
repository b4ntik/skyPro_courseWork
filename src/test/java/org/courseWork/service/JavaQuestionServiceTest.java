package org.courseWork.service;

import org.courseWork.model.error.ThereIsNotQuestionError;
import org.courseWork.model.error.ThisQuestionAlreadyExist;
import org.courseWork.model.question.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class JavaQuestionServiceTest {

    private JavaQuestionService javaQuestionService;
    private Question mockQuestion;

    @BeforeEach
    void setUp() {
        javaQuestionService = new JavaQuestionService();
        mockQuestion = new Question("Столица Франции?", "Париж");
    }

    @Test
    void testAddQuestion_AlreadyExists() {

        Question question = new Question("Сколько будет 2+2?", "4");

        Exception exception = assertThrows(ThisQuestionAlreadyExist.class, () -> {
            javaQuestionService.addQuestion(question);
        });

        assertEquals("Такой вопрос уже существует в коллекции", exception.getMessage());
    }

    @Test
    void testRemoveQuestion_Success() {
        Question question = new Question("Сколько будет 2+2?", "4");
       // javaQuestionService.addQuestion(question);
        javaQuestionService.removeQuestion(question);
        assertFalse(javaQuestionService.findQuestion(question));
    }

    @Test
    void testRemoveQuestion_NotExists() {
        Question question = new Question();

        Exception exception = assertThrows(ThereIsNotQuestionError.class, () -> {
            javaQuestionService.removeQuestion(question);
        });

        assertEquals("404 вопрос отсутствует", exception.getMessage());
    }

    @Test
    void testGetRandomQuestions_EmptyCollection() {
        javaQuestionService.clearQuestions();
        Exception exception = assertThrows(ThereIsNotQuestionError.class, () -> {
            javaQuestionService.getRandomQuestion(1);
        });

        assertEquals("404 вопрос отсутствует", exception.getMessage());
    }

    @Test
    void testGetRandomQuestions_Success() {

        Question question1 = new Question("Сколько будет 3+3?", "6");
        Question question2 = new Question("Смысл жизни?", "42");
        //добавил логирования для отлова бага в неработающем тесте
        //System.out.println("Добавляем первый вопрос");
        javaQuestionService.addQuestion(question1);
        //System.out.println("Первый вопрос добавлен успешно");
        //System.out.println("Добавляем второй вопрос");
        javaQuestionService.addQuestion(question2);
        //System.out.println("Второй вопрос добавлен успешно");

        List<Question> questions = javaQuestionService.getRandomQuestion(2);
        assertEquals(2, questions.size());
    }

    @Test
    void testCheckCorrectAnswer_Success() {
        javaQuestionService.clearQuestions();
        javaQuestionService.addQuestion(mockQuestion);
        javaQuestionService.setQuestion(mockQuestion);
        // Устанавливаем как бы другой правильный ответ
        mockQuestion.setAnswer("Париж");
        mockQuestion.setUserAnswer("Париж");
        //отлов багов
        //System.out.println("ответ установлен");
        //System.out.println(javaQuestionService.getAllQuestions());
        assertTrue(javaQuestionService.checkCorrectAnswer(), "Этот ответ должен быть правильным");
    }

    @Test
    void testCheckCorrectAnswer_Failure() {
        javaQuestionService.clearQuestions();
        Question question = new Question("Сколько будет 3+3?", "6");

        javaQuestionService.addQuestion(question);
        question.setUserAnswer("5");
        List<Question> questions = javaQuestionService.getRandomQuestion(1);
        //выдать один вопрос
        Question currentQuestion = questions.get(0);
        javaQuestionService.setQuestion(currentQuestion);
        //отлов багов
        //System.out.println(javaQuestionService.getAllQuestions());

        assertFalse(javaQuestionService.checkCorrectAnswer());
    }

    @Test
    void testCheckCorrectAnswer_NoQuestion() {
        javaQuestionService.clearQuestions();
        Exception exception = assertThrows(ThereIsNotQuestionError.class, () -> {
            javaQuestionService.checkCorrectAnswer();
        });

        assertEquals("404 вопрос отсутствует", exception.getMessage());
    }
}