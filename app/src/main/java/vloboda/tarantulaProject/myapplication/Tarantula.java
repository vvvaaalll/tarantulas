package vloboda.tarantulaProject.myapplication;

public class Tarantula {
    String mSpecies, mName, mOrigin, mOwner, imgURL;

    long mTemper, mVenom, mHairs;

    public Tarantula(){

    }

    public Tarantula(String mSpecies, String mName, String mOrigin, String mOwner, String imgURL, long mTemper, long mVenom, long mHairs) {
        this.mSpecies = mSpecies;
        this.mName = mName;
        this.mOrigin = mOrigin;
        this.mOwner = mOwner;
        this.imgURL = imgURL;
        this.mTemper = mTemper;
        this.mVenom = mVenom;
        this.mHairs = mHairs;
    }

    public void setmSpecies(String mSpecies) {
        this.mSpecies = mSpecies;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmOrigin(String mOrigin) {
        this.mOrigin = mOrigin;
    }

    public void setmTemper(long mTemper) {
        this.mTemper = mTemper;
    }

    public void setmVenom(long mVenom) {
        this.mVenom = mVenom;
    }

    public void setmHairs(long mHairs) {
        this.mHairs = mHairs;
    }

    public String getmSpecies() {
        return mSpecies;
    }

    public String getmName() {
        return mName;
    }

    public void setmOwner(String mOwner) {
        this.mOwner = mOwner;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getmOrigin() {
        return mOrigin;
    }

    public long getmTemper() {
        return mTemper;
    }

    public long getmVenom() {
        return mVenom;
    }

    public long getmHairs() {
        return mHairs;
    }
    public String getmOwner() {
        return mOwner;
    }

    public String getImgURL() {
        return imgURL;
    }
}
