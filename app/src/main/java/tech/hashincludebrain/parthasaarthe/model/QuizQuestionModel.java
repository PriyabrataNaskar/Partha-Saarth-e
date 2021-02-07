package tech.hashincludebrain.parthasaarthe.model;

/**
 * Created by Priyabrata Naskar on 05-02-2021.
 */
public class QuizQuestionModel {
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    private int imageResourceID;

    /**
     * Constructor
     * @param question
     * @param optionA
     * @param optionB
     * @param optionC
     * @param optionD
     * @param correctAnswer
     * @param imageResourceID
     */
    public QuizQuestionModel(String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer, int imageResourceID) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
        this.imageResourceID = imageResourceID;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public int getImageResourceID() {
        return imageResourceID;
    }
}
