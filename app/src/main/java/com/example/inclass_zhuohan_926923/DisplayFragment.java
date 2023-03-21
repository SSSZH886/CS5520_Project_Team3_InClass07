package com.example.inclass_zhuohan_926923;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inclass_zhuohan_926923.InClass_07.model.NotesAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String NAME = "name";
    private static final String EMAIL = "email";

    private static final String TOKEN = "token";
    //loadNotesFragment listener;


    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;
    private NotesArray notesArray;
    private ArrayList<Notes> notes;
    private final OkHttpClient client = new OkHttpClient();
    private RecyclerView.LayoutManager layoutManager;

    private Button button_logout;

    private String token;



    // TODO: Rename and change types of parameters

    public DisplayFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DisplayFragment newInstance(String name, String email, String token) {
        DisplayFragment fragment = new DisplayFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        args.putString(EMAIL, email);
        args.putString(TOKEN, token);
        //Log.d(TAG, "newInstance: " + token);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Profile");
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_display2, container, false);
        TextView textViewName = view.findViewById(R.id.textView_DisplayName);
        TextView textViewEmail = view.findViewById(R.id.textView_DisplayEmail);
        button_logout = view.findViewById(R.id.buttonLogout);
        token = getArguments().getString(TOKEN);




        //Log.d(TAG, "onCreateView: " + token);
        textViewName.setText(getArguments().getString(NAME));
        textViewEmail.setText(getArguments().getString(EMAIL));

        notesAdapter = new NotesAdapter(new ArrayList<>(), (NotesAdapter.OnDeleteNoteListener) getActivity());
        recyclerView = view.findViewById(R.id.InClass07_recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setAdapter(notesAdapter);
        loadNotes(token);

        recyclerView.setAdapter(notesAdapter);

        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                token = null;
                notesAdapter.clearNotes();

                Intent intent = new Intent(getActivity(), InClass07_main.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                Toast.makeText(getContext(), "Logout Successful", Toast.LENGTH_LONG).show();

                startActivity(intent);
                getActivity().finish();



            }
        });





        return view;
    }

    public void loadNotes(String token){
        String url = "http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/note/getall";
        Request request = new Request.Builder()
                .addHeader("x-access-token",token)
                .url(url)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(getContext() ,"No Internet", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    Gson gsonData = new Gson();
                    //Log.d(TAG, "onResponse: " + gsonData);
                    notes = gsonData.fromJson(response.body().charStream(), NotesArray.class).getNotesArray();
                    Log.d(TAG, "onResponse: " + notes);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            notesAdapter.updateNotes(notes);

                        }
                    });

                }
            }
        });

    }

//    @Override
//    public void onAttach(@NonNull Context context){
//        super.onAttach(context);
//        if (context instanceof DisplayFragment.loadNotesFragment){
//            listener = (DisplayFragment.loadNotesFragment) context;
//        } else {
//            throw new RuntimeException(context.toString() + "must implement AddButtonAction");
//        }
//    }
//
//    public interface loadNotesFragment{
//        void onNotesLoaded(ArrayList<Notes>notes);
//    }
}