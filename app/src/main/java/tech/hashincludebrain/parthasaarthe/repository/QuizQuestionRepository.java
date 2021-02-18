package tech.hashincludebrain.parthasaarthe.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import tech.hashincludebrain.parthasaarthe.R;
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
    private String myJson;
    public static QuizQuestionRepository getInstance(final Context context) {
        if (instance == null) {
            instance = new QuizQuestionRepository(context);
        }
        return instance;
    }

    public QuizQuestionRepository(Context context){
        this.myJson = inputStreamToString(context.getApplicationContext().getResources().openRawResource(R.raw.body_language));
    }
    public MutableLiveData<List<QuizQuestionModel>> getQuizQuestionList() {
        setQuizQuestionList();
        MutableLiveData<List<QuizQuestionModel>> data = new MutableLiveData<>();
        data.setValue(quizQuestionList);
        return data;
    }

    public void setQuizQuestionList() {
        //quizQuestionList.add(new QuizQuestionModel("Q1", "A", "B", "C", "D", "A"));
        //quizQuestionList.add(new QuizQuestionModel("Q2", "A", "B", "C", "D", "B"));
        //quizQuestionList.add(new QuizQuestionModel("Q3", "A", "B", "C", "D", "C"));
        //quizQuestionList.add(new QuizQuestionModel("Q4", "A", "B", "C", "D", "D"));
        //quizQuestionList.add(new QuizQuestionModel("Q5", "A", "B", "C", "D", "B"));
        //quizQuestionList.add(new QuizQuestionModel("Q6", "A", "B", "C", "D", "A"));
        //getResources().openRawResource(R.raw.body_language);
        try {
            // Parse the data into jsonobject to get original data in form of json.
            JSONObject jObject = new JSONObject(myJson);
            //Log.e("JSON",myJson);
            //JSONObject jObjectResult = jObject.getJSONObject("response");
            JSONArray jArray = jObject.getJSONArray("response");
            Log.e("JSON","length "+ jArray.length());

            for (int i = 0; i < jArray.length(); i++) {

                JSONObject prodObj = jArray.getJSONObject(i);
                Log.e("JSON","INSIDE LOOP");
                String question = prodObj.getString("question");
                Log.e("JSON",question);
                String option1 = prodObj.getString("option1");
                String option2 = prodObj.getString("option2");
                String option3 = prodObj.getString("option3");
                String option4 = prodObj.getString("option4");
                String correctAnswer = prodObj.getString("answer");
                String explanation = prodObj.getString("explanation");
                Log.e("After Parse: ",question + option1 + option2 + option4 + correctAnswer + explanation);
                QuizQuestionModel model = new QuizQuestionModel(question,option1,option2,option3,option4,correctAnswer);
                quizQuestionList.add(model);
                Log.e("Quiz",model.getQuestion()+ " at position "+ i);
                //quiz = new QuizQuestionModel(position,brand,name,description);
                //data.add(brands);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
