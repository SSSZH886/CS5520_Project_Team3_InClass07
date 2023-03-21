package com.example.inclass_zhuohan_926923;

import androidx.annotation.NonNull;

public class Notes {
    private String text;
    private String _id;


    public String getText() {
        return text;
    }

    public void setText(String text, String _id) {
        this.text = text;
        this._id = _id;
    }

    public Notes(String text) {
        this.text = text;
    }

    public Notes(){

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "text='" + text + '\'' +
                ", _id='" + _id + '\'' +
                '}';
    }
}
