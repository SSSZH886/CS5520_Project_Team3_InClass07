package com.example.inclass_zhuohan_926923;

import java.util.ArrayList;

public class NotesArray {

    private ArrayList<Notes> notes;

    public ArrayList<Notes> getNotesArray() {
        return notes;
    }

    @Override
    public String toString() {
        return "NotesArray{" +
                "notesArray=" + notes +
                '}';
    }

    public void setNotesArray(ArrayList<Notes> notes) {
        this.notes = notes;
    }

    public NotesArray() {

    }
}