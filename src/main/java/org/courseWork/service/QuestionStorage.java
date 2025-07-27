package org.courseWork.service;

import org.courseWork.model.error.ThereIsNotQuestionError;
import org.courseWork.model.error.ThisQuestionAlreadyExist;
import org.courseWork.model.question.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class QuestionStorage implements QuestionService {
    private final List<Question> questions = new ArrayList<>();
    private Random random = new Random();

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
        return questions.get(index);
    }

    @Override
    public boolean checkCorrectAnswer() {
        return false;
    }

    @Override
    public boolean equals(Question question) {
        return false;
    }

    public Collection<Question> getCollectionsOfQuestions() {

        return List.of((Question) questions);
    }

    public void createTestQuestions() {
        List<Question> questions = new ArrayList<>();
        Question q1 = new Question();
        q1.addQuestion("Столица Великобритании?", "Лондон");
        Question q2 = new Question();
        q2.addQuestion("Сколько будет 2+2?", "4");
        Question q3 = new Question();
        q3.addQuestion("Кто написал 'Идиот'? (Фамилия автора)", "Достоевский");
    }
}
