package tech.hashincludebrain.parthasaarthe.ui.quiz;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tech.hashincludebrain.parthasaarthe.R;
import tech.hashincludebrain.parthasaarthe.model.Quiz;

/**
 * Created by Priyabrata Naskar on 23-01-2021.
 */
public class QuizCategoryActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    QuizCategoryAdapter quizArrayAdapter;
    ArrayList<Quiz> mQuizData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_category);

        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.recycler_view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        // Initialize the ArrayList that will contain the data.
        mQuizData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        quizArrayAdapter = new QuizCategoryAdapter(mQuizData, this);
        mRecyclerView.setAdapter(quizArrayAdapter);

        // Get the data.
        initializeData();
    }

    private void initializeData() {
        // Get the resources from the XML file.
        String[] quizNameList = getResources()
                .getStringArray(R.array.quiz_name);

        String[] questionNumber = getResources()
                .getStringArray(R.array.question_number);

        String[] quizPoint = getResources()
                .getStringArray(R.array.quiz_point);

        TypedArray quizImageResources = getResources()
                .obtainTypedArray(R.array.quiz_images);

        // Clear the existing data (to avoid duplication).
        mQuizData.clear();

        // Create the ArrayList of Quiz objects with the names and
        // information about each quiz
        for (int i = 0; i < quizNameList.length; i++) {
            mQuizData.add(new Quiz(quizNameList[i], (questionNumber[i]), (quizPoint[i]),
                    quizImageResources.getResourceId(i, 0)));
        }

        // Recycle the typed array.
        quizImageResources.recycle();

        // Notify the adapter of the change.
        quizArrayAdapter.notifyDataSetChanged();
    }
}