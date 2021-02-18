package tech.hashincludebrain.parthasaarthe.ui.quiz;

import android.animation.Animator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.List;

import tech.hashincludebrain.parthasaarthe.R;
import tech.hashincludebrain.parthasaarthe.model.QuizQuestionModel;
import tech.hashincludebrain.parthasaarthe.viewmodel.QuizActivityViewModel;

/**
 * Created by Priyabrata Naskar on 25-01-2021.
 */
public class QuizActivity extends AppCompatActivity {
    TextView questionText;

    MaterialButtonToggleGroup toggleGroup;
    Button optionButtonOne;
    Button optionButtonTwo;
    Button optionButtonThree;
    Button optionButtonFour;

    MaterialButton nextButton;

    private LinearLayout optionsLinearLayout;
    //To count child in LinearLayout & display animation for all
    private int count = 0;

    private QuizActivityViewModel mQuizActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.question_text);
        optionsLinearLayout = findViewById(R.id.quiz_linear_layout);

        toggleGroup = findViewById(R.id.toggleButton);
        optionButtonOne = findViewById(R.id.option_button1);
        optionButtonTwo = findViewById(R.id.option_button2);
        optionButtonThree = findViewById(R.id.option_button3);
        optionButtonFour = findViewById(R.id.option_button4);

        nextButton = findViewById(R.id.next_button);

        mQuizActivityViewModel = new ViewModelProvider(this).get(QuizActivityViewModel.class);

        mQuizActivityViewModel.loadQuizQuestions();
        mQuizActivityViewModel.getQuizQuestions().observe(this, new Observer<List<QuizQuestionModel>>() {
            @Override
            public void onChanged(List<QuizQuestionModel> quizQuestionModels) {

            }
        });

        //Handle click on Next Button
        nextButton.setOnClickListener(v -> {

            //Get Selected Button ID
            int id = toggleGroup.getCheckedButtonId();
            if (isCorrectAnswer(mQuizActivityViewModel.getCurrentIndex(), findViewById(id))) {
                mQuizActivityViewModel.increaseScore(10);
                Toast.makeText(this, "Score: " + mQuizActivityViewModel.getScore(), Toast.LENGTH_SHORT).show();
            }

            //Clears the selections
            toggleGroup.uncheck(id);

            //Set count to 0. So that animation will be applied to all
            count = 0;
            playAnim(questionText, 0);

            //Changing index randomly to load next question
            mQuizActivityViewModel.setCurrentIndex();

            //Load new question as per index & clear all tints
            loadQuestion(mQuizActivityViewModel.getQuizQuestions().getValue().get(mQuizActivityViewModel.getCurrentIndex()));

            //remove tint from options & make it clickable
            removeOptionTint();
        });

        nextButton.setClickable(false);

        toggleGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                optionButtonOne.setClickable(false);
                optionButtonTwo.setClickable(false);
                optionButtonThree.setClickable(false);
                optionButtonFour.setClickable(false);

                //Get selected button ID & check for answer validation
                isCorrectAnswer(mQuizActivityViewModel.getCurrentIndex(), findViewById(toggleGroup.getCheckedButtonId()));

                nextButton.setAlpha(1f);
                nextButton.setClickable(true);
            } else {
                //Disabling Next Button & changing its alpha color
                nextButton.setClickable(false);
                nextButton.setAlpha(0.30f);
            }
        });

        loadQuestion(mQuizActivityViewModel.getQuizQuestions().getValue().get(mQuizActivityViewModel.getCurrentIndex()));
    }

    /**
     * Load Current Question & options from list to the View
     *
     * @param currentQuiz stores current question model with options
     */
    private void loadQuestion(QuizQuestionModel currentQuiz) {
        questionText.setText(currentQuiz.getQuestion());
        optionButtonOne.setText(currentQuiz.getOptionA());
        optionButtonTwo.setText(currentQuiz.getOptionB());
        optionButtonThree.setText(currentQuiz.getOptionC());
        optionButtonFour.setText(currentQuiz.getOptionD());
    }

    /**
     * Remove background Tint from all options make all options clickable
     */
    private void removeOptionTint() {
        //Change Background to default & make every option clickable
        optionButtonOne.setClickable(true);
        optionButtonOne.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark, getTheme()));
        optionButtonTwo.setClickable(true);
        optionButtonTwo.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark, getTheme()));
        optionButtonThree.setClickable(true);
        optionButtonThree.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark, getTheme()));
        optionButtonFour.setClickable(true);
        optionButtonFour.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark, getTheme()));
        Toast.makeText(this, "Question Loaded", Toast.LENGTH_SHORT).show();
    }

    /**
     * Checks if selected answer matches with the Right Answer
     * TODO: Change Color of the options
     *
     * @param currentIndex
     * @param view
     * @return
     */
    private boolean isCorrectAnswer(int currentIndex, Button view) {
        final String correctAnswer;
        final String answer;
        correctAnswer = mQuizActivityViewModel.getQuizQuestions().getValue().get(currentIndex).getCorrectAnswer();
        answer = view.getText().toString();
        //return true if correct answer is selected
        if (correctAnswer.equals(answer)) {
            Toast.makeText(this, "Changing to Green", Toast.LENGTH_SHORT).show();
            //view.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00FF00")));
            view.setBackgroundColor(Color.GREEN);
            //view.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            return true;
        } else {
            Toast.makeText(this, "Changing to Red", Toast.LENGTH_SHORT).show();
            //view.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF0000")));
            //view.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            view.setBackgroundColor(Color.RED);
            return false;
        }
    }

    /**
     * PlayAnim method handles animation
     *
     * @param view  holds the view on which animation to be applied
     * @param value handles value for scaling & alpha
     */
    private void playAnim(View view, final int value) {

        view.animate().alpha(value).scaleX(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                String quizData = "";
                if (value == 0 && count < optionsLinearLayout.getChildCount()) {

                    int index = mQuizActivityViewModel.getCurrentIndex();
                    if (count == 0) {
                        quizData = mQuizActivityViewModel.getQuizQuestions().getValue().get(index).getQuestion();
                    } else if (count == 1) {
                        quizData = mQuizActivityViewModel.getQuizQuestions().getValue().get(index).getOptionA();
                    } else if (count == 2) {
                        quizData = mQuizActivityViewModel.getQuizQuestions().getValue().get(index).getOptionB();
                    } else if (count == 3) {
                        quizData = mQuizActivityViewModel.getQuizQuestions().getValue().get(index).getOptionC();
                    } else if (count == 4) {
                        quizData = mQuizActivityViewModel.getQuizQuestions().getValue().get(index).getOptionD();
                    } else if (count == 5) {
                        quizData = mQuizActivityViewModel.getQuizQuestions().getValue().get(index).getCorrectAnswer();
                    }
                    if (optionsLinearLayout.getChildAt(count) != null) {
                        playAnim(optionsLinearLayout.getChildAt(count), 0);
                        count++;
                    }
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //Change Quiz
                if (value == 0) {
                    playAnim(view, 1);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}