package com.example.inclass_zhuohan_926923;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.inclass_zhuohan_926923.InClass_07.model.NotesAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class InClass07_main extends AppCompatActivity implements NotesAdapter.OnDeleteNoteListener, RegisterFragment.FromRegisterToActivity, LoginFragment.FromLoginToMain, AddNoteFragment.OnNoteAddedListener{

    private Button button_register, button_login;
    private ConstraintLayout constraintLayoutRoot_registerFragment, getConstraintLayoutRoot_loginFragment;

    private final OkHttpClient client = new OkHttpClient();
    private final static String authenticationURL = "http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/auth";
    private final static String notesURL = "http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/note";
    private String token = null;

    private ArrayList<Notes> notes;

    private NotesArray notesArray;

    private NotesAdapter adapter;


//    private static final String AUTH_TOKEN_KEY = "auth_token";
//    private String authToken;
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        SharedPreferences preferences = getSharedPreferences("my_app_name", Context.MODE_PRIVATE);
//        authToken = preferences.getString(AUTH_TOKEN_KEY, null);
//        if (authToken == null) {
//            // The token has expired or hasn't been obtained yet, go to the register fragment
//
//        } else {
//            // The token is still valid, use it to make authenticated API calls
//
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class07_main);

        constraintLayoutRoot_registerFragment = findViewById(R.id.rootConstraintLayout_register);
        getConstraintLayoutRoot_loginFragment = findViewById(R.id.rootConstraintLayout_login);

        button_register = findViewById(R.id.button_register);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constraintLayoutRoot_registerFragment.setVisibility(View.VISIBLE);
                button_login.setVisibility(View.INVISIBLE);
                button_register.setVisibility(View.INVISIBLE);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.rootConstraintLayout_register, RegisterFragment.newInstance(), "registerFragment")
                        .commit();
            }
        });


        button_login = findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getConstraintLayoutRoot_loginFragment.setVisibility(View.VISIBLE);
                button_login.setVisibility(View.INVISIBLE);
                button_register.setVisibility(View.INVISIBLE);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.rootConstraintLayout_login, LoginFragment.newInstance(), "loginFragment")
                        .commit();
            }
        });

    }

    public void toRegister(String name, String email, String password) {
        getSupportFragmentManager().popBackStack();
        RegisterFragment register = (RegisterFragment) getSupportFragmentManager().findFragmentByTag("registerFragment");
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("email", email);
        bundle.putString("password", password);
        register.setArguments(bundle);

        HttpUrl url = HttpUrl.parse(authenticationURL + "/register")
                .newBuilder()
                .build();

        RequestBody requestBody = new FormBody.Builder()
                .add("name", name)
                .add("email", email)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url(authenticationURL + "/register?" +
                        "name=" + name +
                        "&email=" + email +
                        "&password=" + password)
                .post(requestBody)
                .build();

        Log.d("demo", "Register URL: " + request.url());

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    try {
                        String string = response.body().string();
                        JSONObject jsonObject = new JSONObject(string);
                        token = jsonObject.getString("token");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(InClass07_main.this, "Successfully Register", Toast.LENGTH_SHORT).show();


//                                LoginFragment loginFragment = LoginFragment.newInstance();
//
//                                getSupportFragmentManager().beginTransaction()
//                                        .replace(R.id.rootConstraintLayout_register, loginFragment, "loginFragment")
//                                        .commit();

                            }
                        });
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

    }

    public void toLogin(String email, String password) {
        //getSupportFragmentManager().popBackStack();
        LoginFragment login = (LoginFragment) getSupportFragmentManager().findFragmentByTag("loginFragment");
        Bundle bundle = new Bundle();
        bundle.putString("email", email);
        bundle.putString("password", password);
        login.setArguments(bundle);


        HttpUrl url = HttpUrl.parse(authenticationURL + "/login")
                .newBuilder()
                .build();

        RequestBody requestBody = new FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    try {
                        String string = response.body().string();
                        JSONObject jsonObject = new JSONObject(string);
                        token = jsonObject.getString("token");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(InClass07_main.this, "Successfully Login", Toast.LENGTH_LONG).show();

//                                DisplayFragment displayFragment = new DisplayFragment();
//                                Bundle bundle = new Bundle();
//                                bundle.putString("token", token);
//                                displayFragment.setArguments(bundle);
//
//                                getSupportFragmentManager().beginTransaction()
//                                        .replace(R.id.rootConstraintLayout_login, displayFragment, "meFragment")
//                                        .addToBackStack(null)
//                                        .commit();
                                constraintLayoutRoot_registerFragment.setVisibility(View.INVISIBLE);
                                Log.d("demo", "login?: ");
//                                getConstraintLayoutRoot_loginFragment.setVisibility(View.INVISIBLE);
                                // TODO: recyclerView Visible
                                getInfoAndToMeFragment(token);

                            }
                        });
                    } catch (JSONException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(InClass07_main.this, "Wrong Password", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(InClass07_main.this, "Please Register Firstly", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }

    public void getInfoAndToMeFragment(String token) {
        Log.d("demo", "login??: ");
        HttpUrl url = HttpUrl.parse(authenticationURL + "/me")
                .newBuilder()
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("x-access-token", token)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    String string = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(string);
                        String name = jsonObject.getString("name");
                        String email = jsonObject.getString("email");

                        AddNoteFragment addNoteFragment = new AddNoteFragment();

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.rootConstraintLayout_login,
                                        DisplayFragment.newInstance(name, email, token),
                                        "meFragment")
                                .commit();

                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.addFragment, AddNoteFragment.newInstance(token))
                                .commit();

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void stopRegister() {
        constraintLayoutRoot_registerFragment.setVisibility(View.INVISIBLE);
        button_register.setVisibility(View.VISIBLE);
        button_login.setVisibility(View.VISIBLE);
    }


//    @Override
//    public void onNotesLoaded(ArrayList<Notes> notes) {
//        // Do nothing here, as we'll update the adapter directly from the DisplayFragment
//    }


    @Override
    public void onNoteAdded() {
        DisplayFragment displayFragment = (DisplayFragment) getSupportFragmentManager().findFragmentByTag("meFragment");
        if (displayFragment != null) {
            displayFragment.loadNotes(token);
        }
    }

//    public void deleteNote(String token, String noteId) {
//        String url = "http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/note/delete";
//
//        RequestBody requestBody = new FormBody.Builder()
//                .add("_id", noteId)
//                .build();
//
//        Request request = new Request.Builder()
//                .addHeader("x-access-token", token)
//                .url(url)
//                .post(requestBody)
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                // Handle the error
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            // Refresh the notes list
//                            DisplayFragment displayFragment = (DisplayFragment) getSupportFragmentManager().findFragmentByTag("meFragment");
//                            if (displayFragment != null) {
//                                displayFragment.loadNotes(token);
//                            }
//                        }
//                    });
//                }
//            }
//        });
//    }
//
//    @Override
//    public void onDeleteNote(String noteId) {
//        deleteNote(token, noteId);
//    }

    @Override
    public void onDeleteNote(String noteId) {
        getSupportFragmentManager().popBackStack();
        DisplayFragment delete = (DisplayFragment) getSupportFragmentManager().findFragmentByTag("meFragment");
        if(delete != null) {
            Bundle bundle = new Bundle();
            bundle.putString("noteId", noteId);
            Log.d("demo", "noteIdInBundle: " + noteId);
            delete.setArguments(bundle);
        }


        HttpUrl url = HttpUrl.parse(notesURL + "/delete")
                .newBuilder()
                .build();


        Log.d("demo", "noteIdInURL: " + noteId);
        RequestBody requestBody = new FormBody.Builder()
                .add("id", noteId)
                .build();

        Request request = new Request.Builder()
                .addHeader("x-access-token", token)
                .url(url)
                .post(requestBody)
                .build();

        Log.d("demo", "url: " + request.url());

//        HttpUrl url = HttpUrl.parse(authenticationURL + "/register")
//                .newBuilder()
//                .build();
//
//        RequestBody requestBody = new FormBody.Builder()
//                .add("name", name)
//                .add("email", email)
//                .add("password", password)
//                .build();
//
//        Request request = new Request.Builder()
//                .url(authenticationURL + "/register?" +
//                        "name=" + name +
//                        "&email=" + email +
//                        "&password=" + password)
//                .post(requestBody)
//                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d("demo", "onResponse: " + response.code());
                Log.d("demo", "onResponse: " + response.message());
                Log.d("demo", "noteId: " + noteId);
                if (response.isSuccessful()) {

                } else {
                    // the request failed, display an error message
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(InClass07_main.this, "Failed to delete note", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}