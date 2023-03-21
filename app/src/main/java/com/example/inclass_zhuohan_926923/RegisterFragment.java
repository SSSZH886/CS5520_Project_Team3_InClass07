package com.example.inclass_zhuohan_926923;

import android.content.Context;
import android.content.Intent;
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
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FromRegisterToActivity sendData;



    public RegisterFragment() {
        // Required empty public constructor
    }


    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

            getActivity().setTitle("Register");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);


        EditText editTextUserName = view.findViewById(R.id.editText_username);
        EditText editTextUserEmail = view.findViewById(R.id.editText_userEmail);
        EditText editTextUserPassword = view.findViewById(R.id.editText_userPassword);
        Button buttonRegister = view.findViewById(R.id.button_toRegister);
        Button buttonStopRegister = view.findViewById(R.id.button_stopRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextUserName.getText().toString();
                String email = editTextUserEmail.getText().toString();
                String password = editTextUserPassword.getText().toString();
                if(name.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter your name",
                            Toast.LENGTH_LONG).show();
                } else if(email.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter your email",
                            Toast.LENGTH_LONG).show();
                } else if (password.isEmpty()){
                    Toast.makeText(getContext(), "Please enter your password",
                            Toast.LENGTH_LONG).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getContext(), "Please enter a valid email",
                            Toast.LENGTH_LONG).show();
                } else {
                    sendData.toRegister(name, email, password);
                    Intent intent = new Intent(getActivity(), InClass07_main.class);
                    startActivity(intent);

                }

            }
        });

        buttonStopRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData.stopRegister();
            }
        });

        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof FromRegisterToActivity) {
            sendData = (FromRegisterToActivity) context;
        } else{
            throw new RuntimeException(context.toString() + " must implement interface!");
        }
    }

    public interface FromRegisterToActivity {
        void toRegister(String name, String email, String password);
        void stopRegister();
    }
}