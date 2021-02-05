package tech.hashincludebrain.parthasaarthe.model;

/**
 * Created by Priyabrata Naskar on 24-01-2021.
 */
public class Chapter {
    private String chapterName;
    private String chapterDetail;
    private int chapterImageResourceID;
    private boolean isLocked;

    /**
     * @param chapterName
     * @param chapterDetail
     * @param chapterImageResourceID
     */
    public Chapter(String chapterName, String chapterDetail, int chapterImageResourceID) {
        this.chapterName = chapterName;
        this.chapterDetail = chapterDetail;
        this.chapterImageResourceID = chapterImageResourceID;

        //By default chapter is locked
        this.isLocked = true;
    }

    /**
     * @param chapterName
     * @param chapterDetail
     * @param chapterImageResourceID
     * @param isLocked
     */
    public Chapter(String chapterName, String chapterDetail, int chapterImageResourceID, boolean isLocked) {
        this.chapterName = chapterName;
        this.chapterDetail = chapterDetail;
        this.chapterImageResourceID = chapterImageResourceID;
        this.isLocked = isLocked;
    }

    /**
     * @return name of the chapter
     */
    public String getChapterName() {
        return chapterName;
    }

    /**
     * @return detail of the chapter
     */
    public String getChapterDetail() {
        return chapterDetail;
    }

    /**
     * @return image of the chapter
     */
    public int getChapterImageResourceID() {
        return chapterImageResourceID;
    }

    /**
     * Check if the chapter locked or not
     * @return boolean value
     */
    public boolean isChapterLocked() {
        return this.isLocked;
    }

}
