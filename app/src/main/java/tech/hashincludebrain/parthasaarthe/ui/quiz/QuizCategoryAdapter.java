package tech.hashincludebrain.parthasaarthe.ui.quiz;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import tech.hashincludebrain.parthasaarthe.R;
import tech.hashincludebrain.parthasaarthe.model.Quiz;

/**
 * Created by Priyabrata Naskar on 23-01-2021.
 */
public class QuizCategoryAdapter extends RecyclerView.Adapter<QuizCategoryAdapter.ViewHolder> {

    // Member variables.
    private ArrayList<Quiz> mQuizData;
    private Context mContext;

    public QuizCategoryAdapter(ArrayList<Quiz> mQuizData, Context mContext) {
        this.mQuizData = mQuizData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.quiz_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get current quiz.
        Quiz currentQuiz = mQuizData.get(position);

        // Populate the textViews with data.
        holder.bindTo(currentQuiz);
    }

    @Override
    public int getItemCount() {
        return mQuizData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView quizNameTextView;
        private TextView quizPointTextView;
        private TextView quizQuestionNumberTextView;
        private ImageView quizImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            quizNameTextView = itemView.findViewById(R.id.quiz_name);
            quizPointTextView = itemView.findViewById(R.id.quiz_point);
            quizQuestionNumberTextView = itemView.findViewById(R.id.quiz_question_number);
            quizImageView = itemView.findViewById(R.id.quiz_image);

            //Setting click listener for each card
            itemView.setOnClickListener(this);
        }

        public void bindTo(Quiz currentQuiz) {
            quizNameTextView.setText(currentQuiz.getQuizName());
            quizPointTextView.setText(String.valueOf(currentQuiz.getPoint()) + " points");
            quizQuestionNumberTextView.setText(String.valueOf(currentQuiz.getQuestionNumber()) + "Q");

            // Load the images into the ImageView using the Glide library.
            Glide.with(mContext).load(
                    currentQuiz.getImageResourceID()).into(quizImageView);
        }

        /**
         * Handle click to show DetailActivity.
         *
         * @param view View that is clicked.
         */
        @Override
        public void onClick(View view) {

            Quiz currentQuiz = mQuizData.get(getAdapterPosition());
            Intent quizIntent = new Intent(mContext, QuizActivity.class);
            quizIntent.putExtra("title", currentQuiz.getQuizName());
            quizIntent.putExtra("image_resource",
                    currentQuiz.getImageResourceID());
            Toast.makeText(view.getContext(), currentQuiz.getQuizName() + " clicked", Toast.LENGTH_SHORT).show();

            //Starts New Activity
            //TODO: Uncomment this************************ "mContext.startActivity()"
            mContext.startActivity(quizIntent);
        }
    }
}
