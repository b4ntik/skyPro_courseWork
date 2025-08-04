package org.courseWork.service;

import org.courseWork.model.error.ThereIsNotQuestionError;
import org.courseWork.model.error.ThisQuestionAlreadyExist;
import org.courseWork.model.question.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExaminerServiceImplTest {

    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        examinerService = new ExaminerServiceImpl();
    }

    @Test
    void testCreateTestQuestions_initializesQuestions() {
        Set<Question> questions = examinerService.createTestQuestions();
        assertNotNull(questions);
        assertEquals(3, questions.size());
    }

    @Test
    void testAddQuestion_success() {
        Question newQuestion = new Question();
        newQuestion.addQuestion("Новый вопрос?", "Ответ");
        examinerService.addQuestion(newQuestion);
        assertTrue(examinerService.findQuestion(newQuestion));
    }

    @Test
    void testAddQuestion_duplicate_throwsException() {
        try{
            examinerService.clearQuestions();
            Question uniqueQuestion = new Question();
            uniqueQuestion.addQuestion("Уникальный текст вопроса", "ответ");
            examinerService.addQuestion(uniqueQuestion);

            //отлавливал баг
//            System.out.println("Добавляем первый раз");
//            System.out.println("Вопросов в сервисе до теста: " + examinerService.getCollectionsOfQuestions().size());
//            System.out.println("Первый раз добавлен успешно");

        // При попытке добавить второй раз — исключение
        ThisQuestionAlreadyExist exception = assertThrows(ThisQuestionAlreadyExist.class, () -> {
            examinerService.addQuestion(uniqueQuestion);
        });
        System.out.println("Actual exception message: '" + exception.getMessage() + "'");

        assertEquals("Такой вопрос уже существует в коллекции", exception.getMessage());
    } catch (Throwable t) {
            t.printStackTrace();
            fail("Exception" + t);
        }}

    @Test
    void testRemoveQuestion_success() {
        Question q = new Question();
        q.addQuestion("Вопрос для удаления?", "Ответ");
        examinerService.addQuestion(q);

        assertTrue(examinerService.findQuestion(q));
        examinerService.removeQuestion(q);
        assertFalse(examinerService.findQuestion(q));
    }

    @Test
    void testRemoveQuestion_notExist_throwsException() {
        Question q = new Question();
        q.addQuestion("Вопрос отсутствующий?", "Ответ");

        ThisQuestionAlreadyExist exception = assertThrows(ThisQuestionAlreadyExist.class, () -> {
            examinerService.removeQuestion(q);
        });
        assertEquals("Такой вопрос отсутствует в коллекции", exception.getMessage());
    }

    @Test
    void testGetRandomQuestion_returnsQuestion() {
        Question question = examinerService.getRandomQuestion();
        assertNotNull(question);
        assertTrue(examinerService.findQuestion(question));
    }

    @Test
    void testGetRandomQuestion_emptySet_throwsException() {
        ExaminerServiceImpl emptyService = new ExaminerServiceImpl() {
            @Override
            public Set<Question> createTestQuestions() {
                return Set.of(); // пустой набор вопросов
            }
        };

        // При вызове getRandomQuestion() на пустом наборе — исключение
        assertThrows(ThereIsNotQuestionError.class, emptyService::getRandomQuestion);
    }

    @Test
    void testCheckCorrectAnswer_correct() {
        Question q = new Question();
        q.addQuestion("Вопрос?", "Ответ");
        examinerService.addQuestion(q);

        // Установим текущий вопрос вручную, вызовем getRandomQuestion и подменим
        examinerService.getRandomQuestion(); // чтобы currentQuestion не был null
        // Подменим currentQuestion на наш вопрос
        setCurrentQuestion(examinerService, q);

        assertTrue(examinerService.checkCorrectAnswer("Ответ"));
        assertTrue(examinerService.checkCorrectAnswer("ответ")); // проверка ignoreCase
    }

    @Test
    void testCheckCorrectAnswer_incorrect() {
        Question q = new Question();
        q.addQuestion("Вопрос?", "Ответ");
        examinerService.addQuestion(q);
        examinerService.getRandomQuestion();
        setCurrentQuestion(examinerService, q);

        assertFalse(examinerService.checkCorrectAnswer("Другое"));
    }

    @Test
    void testCheckCorrectAnswer_noCurrentQuestion() {
        ExaminerServiceImpl service = new ExaminerServiceImpl() {
            {
                // Очистим вопросы, чтобы currentQuestion не устанавливался
                this.questions.clear();
                this.currentQuestion = null;
            }
        };
        assertFalse(service.checkCorrectAnswer("Ответ"));
    }

    @Test
    void testGetQuestions_delegatesToSuper() {

        Collection<Question> questions = examinerService.getQuestions(2);
        assertNotNull(questions);
    }

    @Test
    void testEquals_returnsFalse() {
        Question q = new Question();
        assertFalse(examinerService.equals(q));
    }

    // Вспомогательный метод для установки currentQuestion через рефлексию
    private void setCurrentQuestion(ExaminerServiceImpl service, Question question) {
        try {
            java.lang.reflect.Field field = ExaminerServiceImpl.class.getDeclaredField("currentQuestion");
            field.setAccessible(true);
            field.set(service, question);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}