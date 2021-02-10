package tech.hashincludebrain.parthasaarthe.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.Random;

import tech.hashincludebrain.parthasaarthe.model.QuizQuestionModel;
import tech.hashincludebrain.parthasaarthe.repository.QuizQuestionRepository;

/**
 * Created by Priyabrata Naskar on 09-02-2021.
 */
public class QuizActivityViewModel extends ViewModel {

    private MutableLiveData<List<QuizQuestionModel>> mQuizQuestions;

    private QuizQuestionRepository mRepo;

    //holds index of the current quiz
    private int currentIndex = -1;

    private int score;

    public void loadQuizQuestions() {
        if (mQuizQuestions != null) {
            return;
        }
        mRepo = QuizQuestionRepository.getInstance();
        mQuizQuestions = mRepo.getQuizQuestionList();
    }

    public LiveData<List<QuizQuestionModel>> getQuizQuestions() {
        return mQuizQuestions;
    }

    public int getCurrentIndex() {
        if (currentIndex == -1) {
            currentIndex = new Random().nextInt(mQuizQuestions.getValue().size());
        }
        return currentIndex;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int point) {
        score = score + point;
    }

    public void setCurrentIndex() {
        currentIndex = new Random().nextInt(mQuizQuestions.getValue().size());
    }
}
