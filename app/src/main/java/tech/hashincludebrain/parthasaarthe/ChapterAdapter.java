package tech.hashincludebrain.parthasaarthe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

/**
 * Created by Priyabrata Naskar on 24-01-2021.
 */
public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {

    // Member variables.
    private ArrayList<Chapter> mChapterData;
    private Context mContext;

    /**
     * @param mChapterData
     * @param mContext
     */
    public ChapterAdapter(ArrayList<Chapter> mChapterData, Context mContext) {
        this.mChapterData = mChapterData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChapterAdapter.ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.chapter_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get current quiz.
        Chapter currentChapter = mChapterData.get(position);

        // Populate the textViews with data.
        holder.bindTo(currentChapter);
    }

    @Override
    public int getItemCount() {
        return mChapterData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView chapterNameTextView;
        private TextView chapterDetailTextView;
        private ImageView chapterImageView;
        private ImageView chapterLockImage;

        /**
         * @param itemView
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chapterNameTextView = itemView.findViewById(R.id.chapter_name);
            chapterDetailTextView = itemView.findViewById(R.id.chapter_detail);
            chapterImageView = itemView.findViewById(R.id.chapter_image);
            chapterLockImage = itemView.findViewById(R.id.chapter_lock);
            //Setting click listener on each item
            itemView.setOnClickListener(this);
        }

        /**
         * @param currentChapter
         */
        public void bindTo(Chapter currentChapter) {
            chapterNameTextView.setText(currentChapter.getChapterName());
            chapterDetailTextView.setText(currentChapter.getChapterDetail());

            // Load the images into the ImageView using the Glide library.
            Glide.with(mContext).load(
                    currentChapter.getChapterImageResourceID()).into(chapterImageView);

            //If chapter is locked then only set lock image
            if (currentChapter.isChapterLocked()) {
                Glide.with(mContext).load(R.drawable.ic_lock).into(chapterLockImage);
            }
        }

        /**
         * @param v
         */
        @Override
        public void onClick(View v) {
            Chapter currentChapter = mChapterData.get(getAdapterPosition());
            if (currentChapter.isChapterLocked()) {
                Snackbar.make(v, currentChapter.getChapterName() + " is Locked, can not open locked chapter", Snackbar.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, currentChapter.getChapterName() + " clicked", Toast.LENGTH_SHORT).show();
                //TODO: Implement Intents...
            }
        }
    }
}
