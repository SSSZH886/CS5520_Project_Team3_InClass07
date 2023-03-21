package com.example.inclass_zhuohan_926923;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    FromLoginToMain sendData;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_EMAIL = "email";
    private static final String ARG_PASSWORD = "password";

    // TODO: Rename and change types of parameters
    private String mParam1Email;
    private String mParam2Password;

    public LoginFragment() {
        // Required empty public constructor
    }


    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Login");
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        EditText editText_enterEmail = view.findViewById(R.id.editText_enterEmail);
        EditText editText_enterPassword = view.findViewById(R.id.editText_enterPassword);
        Button buttonLogin = view.findViewById(R.id.button_toLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editText_enterEmail.getText().toString();
                String password = editText_enterPassword.getText().toString();

                if(email.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter your email",
                            Toast.LENGTH_LONG).show();
                } else if (password.isEmpty()){
                    Toast.makeText(getContext(), "Please enter your password",
                            Toast.LENGTH_LONG).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getContext(), "Please enter a valid email",
                            Toast.LENGTH_LONG).show();
                } else {
                    sendData.toLogin(email, password);
                    editText_enterEmail.setText("");
                    editText_enterPassword.setText("");;
                }
            }
        });



        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof RegisterFragment.FromRegisterToActivity) {
            sendData = (LoginFragment.FromLoginToMain) context;
        } else{
            throw new RuntimeException(context.toString() + " must implement interface!");
        }
    }

    public interface FromLoginToMain{
        void toLogin(String email, String password);

    }
}