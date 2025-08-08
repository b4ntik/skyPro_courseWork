package org.courseWork.service;

import org.courseWork.model.error.ThereIsNotQuestionError;
import org.courseWork.model.error.ThisQuestionAlreadyExist;
import org.courseWork.model.question.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
    public class JavaQuestionService implements QuestionService{
    private Set<Question> questions = new HashSet<>();
    private Random random = new Random();
    private Question question;
    private Question currentQuestion;

        public JavaQuestionService(){
            this.questions = createTestQuestions();
        }

        @Override
        public Question getRandomQuestion() {
            int index = random.nextInt(questions.size());
            question = (Question) questions.toArray()[index];
            return question;
        }

        @Override
        public boolean checkCorrectAnswer() {

            if (currentQuestion == null){
                throw new ThereIsNotQuestionError();
            }
            //отлов багов
            //System.out.println("Проверяем вопрос " + currentQuestion.getQuestion());
            String correctAnswer = currentQuestion.getCorrectAnswer();
            String userAnswer = currentQuestion.getUserAnswer();
            return correctAnswer.equalsIgnoreCase(userAnswer);
        }

        @Override
        public boolean equals(Question question) {
            boolean a = false;
            if (question == null){ a = false;}
            if (this.question.getQuestion() != null && this.question.getQuestion().equals(question.getQuestion()) &&
                    (this.question.getCorrectAnswer() != null && this.question.getCorrectAnswer().equals(question.getCorrectAnswer()))){
                a = true;
            }
            return a;
        }
    public void addQuestion(Question question) {
        //System.out.println("Добавляем вопрос: " + question);
        //System.out.println("Текущие вопросы: " + questions);
        //System.out.println("Содержится ли вопрос? " + questions.contains(question));
        if (questions.contains(question)) {
            throw new ThisQuestionAlreadyExist("Такой вопрос уже существует в коллекции");
        }
        questions.add(question);
       //System.out.println("Вопрос добавлен");
    }

    public List<Question> getRandomQuestion(int amount) {

        if (questions == null || questions.isEmpty() || amount > questions.size()) {
            throw new ThereIsNotQuestionError();
        }
        List<Question> questionList = new ArrayList<>(questions);
        Collections.shuffle(questionList);

        return questionList.subList(0, amount);
    }
    public void removeQuestion(Question question) {

        if (!questions.contains(question)) {
            throw new ThereIsNotQuestionError();
        }
        questions.remove(question);
    }

    public boolean findQuestion(Question question) {
        return questions.contains(question);
    }

    public Collection<Question> getAllQuestions() {
        return questions;
    }
    @Override
    public String toString(){

        return questions.toString();
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

    public void clearQuestions() {
        questions.clear();
    }
    public void setQuestion(Question question) {
        this.currentQuestion = question;
    }

}
