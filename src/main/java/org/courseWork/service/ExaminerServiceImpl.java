package org.courseWork.service;

import org.courseWork.model.error.ThereIsNotQuestionError;
import org.courseWork.model.error.ThisQuestionAlreadyExist;
import org.courseWork.model.question.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    Set<Question> questions = new HashSet<>();
    private Random random = new Random();
    Question currentQuestion;

    public ExaminerServiceImpl() {
        this.questions = createTestQuestions();
    }

    public void addQuestion(Question question) {
        if (questions.contains(question)) {
            throw new ThisQuestionAlreadyExist("Такой вопрос уже существует в коллекции");
        }
        questions.add(question);
    }

    public Question getRandomQuestion() {
        if (questions == null || questions.isEmpty()) {
            throw new ThereIsNotQuestionError();
        }

        int index = random.nextInt(questions.size());

        Iterator<Question> iterator = questions.iterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        currentQuestion = iterator.next();
        return currentQuestion;
    }


    public void removeQuestion(Question question) {

        if (!questions.contains(question)) {
            throw new ThisQuestionAlreadyExist("Такой вопрос отсутствует в коллекции");
        }
        questions.remove(question);
    }

    public boolean findQuestion(Question question) {
        return questions.contains(question);
    }

    @Override
    public boolean checkCorrectAnswer(String userAnswer) {
        if (currentQuestion == null || userAnswer == null) {
            return false;
        }
        return userAnswer.equalsIgnoreCase(currentQuestion.getAnswer());
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        return ExaminerService.super.getQuestions(amount);
    }

    @Override
    public boolean equals(Question question) {
        return false;
    }

    //очистка вопросов
    public void clearQuestions() {
        questions.clear();
    }

    //тестовые вопросы
    public Set<Question> createTestQuestions() {
        Set<Question> questions = new HashSet<>();
        Question q1 = new Question();
        q1.addQuestion("Столица Великобритании?", "Лондон");
        questions.add(q1);
        Question q2 = new Question();
        q2.addQuestion("Сколько будет 2+2?", "4");
        questions.add(q2);
        Question q3 = new Question();
        q3.addQuestion("Кто написал 'Идиот'? (Фамилия автора)", "Достоевский");
        questions.add(q3);

        return questions;
    }

    public Collection<Question> getCollectionsOfQuestions() {
        return questions;
    }
}
