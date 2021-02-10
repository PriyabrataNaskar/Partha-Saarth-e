package tech.hashincludebrain.parthasaarthe.repository;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import tech.hashincludebrain.parthasaarthe.model.QuizQuestionModel;

/**
 * Created by Priyabrata Naskar on 09-02-2021.
 */

/**
 * Singleton Pattern
 */
public class QuizQuestionRepository {
    private static QuizQuestionRepository instance;
    private ArrayList<QuizQuestionModel> quizQuestionList = new ArrayList<>();

    public static QuizQuestionRepository getInstance() {
        if (instance == null) {
            instance = new QuizQuestionRepository();
        }
        return instance;
    }

    public MutableLiveData<List<QuizQuestionModel>> getQuizQuestionList() {
        setQuizQuestionList();
        MutableLiveData<List<QuizQuestionModel>> data = new MutableLiveData<>();
        data.setValue(quizQuestionList);
        return data;
    }

    public void setQuizQuestionList() {
        quizQuestionList.add(new QuizQuestionModel("Q1", "A", "B", "C", "D", "A"));
        quizQuestionList.add(new QuizQuestionModel("Q2", "A", "B", "C", "D", "B"));
        quizQuestionList.add(new QuizQuestionModel("Q3", "A", "B", "C", "D", "C"));
        quizQuestionList.add(new QuizQuestionModel("Q4", "A", "B", "C", "D", "D"));
        quizQuestionList.add(new QuizQuestionModel("Q5", "A", "B", "C", "D", "B"));
        quizQuestionList.add(new QuizQuestionModel("Q6", "A", "B", "C", "D", "A"));
        //getResources().openRawResource(R.raw.body_language);
        //myJson=inputStreamToString(context.getResources().openRawResource(R.raw.body_language);
    }

    public String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }
}
