package com.example.inclass_zhuohan_926923;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddNoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNoteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
   private Notes notes;

    private OnNoteAddedListener mListener;

    private static final String TOKEN = "token";

    private final OkHttpClient client = new OkHttpClient();

    public AddNoteFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AddNoteFragment newInstance(String xToken) {
        AddNoteFragment fragment = new AddNoteFragment();
        Bundle args = new Bundle();
        args.putString(TOKEN, xToken);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_note, container, false);
        EditText editText = view.findViewById(R.id.InClass07_editTextNote);
        Button buttonAdd = view.findViewById(R.id.Inclass07_addbutton);

        String accessToken = getArguments().getString(TOKEN);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString(); // Move this inside onClick()
                addNote(accessToken, text);


            }
        });


        return view;
    }

    public void addNote(String token, String text){
        String url = "http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/note/post";

        RequestBody requestBody = new FormBody.Builder()
                .add("text", text)
                .build();

        Request request = new Request.Builder()
                .addHeader("x-access-token",token)
                .url(url)
                .post(requestBody)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "No Internet", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "Success Add Note", Toast.LENGTH_LONG).show();
                            if (mListener != null) {
                                mListener.onNoteAdded();

                            }
                        }
                    });
                }
            }
        });

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnNoteAddedListener) {
            mListener = (OnNoteAddedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnNoteAddedListener");
        }
    }

    public interface OnNoteAddedListener {
        void onNoteAdded();
    }
}