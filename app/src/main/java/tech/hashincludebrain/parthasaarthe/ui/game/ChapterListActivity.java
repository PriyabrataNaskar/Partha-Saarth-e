package tech.hashincludebrain.parthasaarthe.ui.game;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tech.hashincludebrain.parthasaarthe.R;
import tech.hashincludebrain.parthasaarthe.model.Chapter;

/**
 * Created by Priyabrata Naskar on 24-01-2021.
 */
public class ChapterListActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ChapterAdapter chapterArrayAdapter;
    ArrayList<Chapter> mChapterData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_list);

        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.chapter_recycler_view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        // Initialize the ArrayList that will contain the data.
        mChapterData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        chapterArrayAdapter = new ChapterAdapter(mChapterData, this);
        mRecyclerView.setAdapter(chapterArrayAdapter);

        // Get the data.
        initializeData();
    }

    private void initializeData() {
        // Get the resources from the XML file.
        String[] chapterNameList = getResources()
                .getStringArray(R.array.chapter_name);

        String[] chapterDetail = getResources()
                .getStringArray(R.array.chapter_detail);

        TypedArray chapterImageResources = getResources()
                .obtainTypedArray(R.array.chapter_images);

        // Clear the existing data (to avoid duplication).
        mChapterData.clear();

        // Create the ArrayList of Chapter objects with the names and
        // information about each chapter
        for (int i = 0; i < chapterNameList.length; i++) {

            //TODO: Handle this part. TEMPORARILY 1st chapter is only open.
            boolean isLocked;
            if (i == 0) {
                isLocked = false;
            } else {
                isLocked = true;
            }
            mChapterData.add(new Chapter(chapterNameList[i], (chapterDetail[i]),
                    chapterImageResources.getResourceId(i, 0), isLocked));
        }

        // Recycle the typed array.
        chapterImageResources.recycle();

        // Notify the adapter of the change.
        chapterArrayAdapter.notifyDataSetChanged();
    }
}