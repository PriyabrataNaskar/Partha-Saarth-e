package tech.hashincludebrain.parthasaarthe;

/**
 * Created by Priyabrata Naskar on 23-01-2021.
 */
public class Quiz {
    private String quizName;
    private String questionNumber;
    private String point;
    private int imageResourceID;

    /**
     * Constructor of Quiz
     *
     * @param quizName
     * @param questionNumber
     * @param point
     * @param imageResourceID
     */
    public Quiz(String quizName, String questionNumber, String point, int imageResourceID) {
        this.quizName = quizName;
        this.questionNumber = questionNumber;
        this.point = point;
        this.imageResourceID = imageResourceID;
    }

    /**
     * @return name of the quiz
     */
    public String getQuizName() {
        return quizName;
    }

    /**
     * @return Number of Questions
     */
    public String getQuestionNumber() {
        return questionNumber;
    }

    /**
     * @return total point
     */
    public String getPoint() {
        return point;
    }

    /**
     * @return ID of the image
     */
    public int getImageResourceID() {
        return imageResourceID;
    }
}
