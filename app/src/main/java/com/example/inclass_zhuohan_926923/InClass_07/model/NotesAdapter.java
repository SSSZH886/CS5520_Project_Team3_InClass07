package com.example.inclass_zhuohan_926923.InClass_07.model;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inclass_zhuohan_926923.Notes;
import com.example.inclass_zhuohan_926923.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private ArrayList<Notes> notes;
    private OnDeleteNoteListener listener;
    private String noteId;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewpoints;
        private final Button buttonDelete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewpoints = itemView.findViewById(R.id.InClass07_textViewNote);
            buttonDelete = itemView.findViewById(R.id.InClass07_buttonDelete);

        }

        public TextView getTextViewpoints() {
            return textViewpoints;
        }

        public Button getButtonDelete() {
            return buttonDelete;
        }
    }

    public NotesAdapter(ArrayList<Notes> notes, OnDeleteNoteListener listener) {
        this.notes = notes;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notes_list_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        holder.getTextViewpoints().setText(notes.get(position).getText());
        holder.getButtonDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noteId = notes.get(holder.getAdapterPosition()).get_id();
                Log.d("demo", "noteIdInAdapter: " + noteId);
                notes.remove(position);
                notifyDataSetChanged();
                listener.onDeleteNote(noteId);

            }

        });
    }

    public void updateNotes(ArrayList<Notes> newNotesList) {
        notes = newNotesList;
        notifyDataSetChanged();
    }
    public void clearNotes() {
        notes = null;
    }

    public List<Notes> getNotes() {
        return notes;
    }


    @Override
    public int getItemCount() {
        return notes.size();
    }



    public interface OnDeleteNoteListener {
        void onDeleteNote(String noteId);
    }


}

