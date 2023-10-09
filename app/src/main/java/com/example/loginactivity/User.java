package com.example.loginactivity;

import android.widget.ImageView;

public class User {
    String shloka,translation,verseindex;




    public User(){}

    public User(String shloka, String translation,String verseindex) {
        this.shloka = shloka;
        this.translation = translation;
        this.verseindex = verseindex;
    }

    public String getShloka() {
        return shloka;
    }

    public void setShloka(String shloka) {
        this.shloka = shloka;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getVerseindex() {
        return verseindex;
    }

    public void setVerseindex(String verseindex) {
        this.verseindex = verseindex;
    }
}
