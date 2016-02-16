package nicknestor.nenfieldassistant.model;

import java.io.Serializable;

public class Category implements Serializable {

    public static final String TAG = "Category";
    private static final long serialVersionUID = -7406082437623008161L;


    private long mCategoryId;
    private String mCategory;



/*    public Location(Long category_id, String category) {
        this.mCategoryId = category_id;
        this.mCategory = category;
    }*/

    public Category(){};


    public long getCategoryId() {return mCategoryId;}
    public void setCategoryId(long mCategoryId) {this.mCategoryId = mCategoryId;}

    public String getCategory() {return mCategory;}
    public void setCategory(String mCategory) {this.mCategory = mCategory;}


}
