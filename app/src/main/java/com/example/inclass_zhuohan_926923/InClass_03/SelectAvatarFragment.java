package com.example.inclass_zhuohan_926923.InClass_03;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.inclass_zhuohan_926923.R;


public class SelectAvatarFragment extends Fragment {

    private int drawableId;

    private IntefaceToInclass03Activity fromSelectAvatarToActivity;

    private ImageView imageView_w1;
    private ImageView imageView_w2;
    private ImageView imageView_w3;
    private ImageView imageView_m1;
    private ImageView imageView_m2;
    private ImageView imageView_m3;


    public SelectAvatarFragment() {
        // Required empty public constructor
    }


    public static SelectAvatarFragment newInstance() {
        SelectAvatarFragment fragment = new SelectAvatarFragment();
        Bundle args = new Bundle();
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
        View view = inflater.inflate(R.layout.fragment_select_avatar, container, false);
        getActivity().setTitle("Select Avatar");

        imageView_w1 = view.findViewById(R.id.inClass03_imageView_f1);
        imageView_w2 = view.findViewById(R.id.inClass03_imageView_f2);
        imageView_w3 = view.findViewById(R.id.inClass03_imageView_f3);
        imageView_m1 = view.findViewById(R.id.inClass03_imageView_m1);
        imageView_m2 = view.findViewById(R.id.inClass03_imageView_m2);
        imageView_m3 = view.findViewById(R.id.inClass03_imageView_m3);

        imageView_w1.setOnClickListener(this::clickedOnAvatar1);
        imageView_w2.setOnClickListener(this::clickedOnAvatar2);
        imageView_w3.setOnClickListener(this::clickedOnAvatar3);
        imageView_m1.setOnClickListener(this::clickedOnAvatar4);
        imageView_m2.setOnClickListener(this::clickedOnAvatar5);
        imageView_m3.setOnClickListener(this::clickedOnAvatar6);

        return view;
    }


    private void clickedOnAvatar1(View view){
        fromSelectAvatarToActivity.avatarClickedInSelectAvatar(R.drawable.avatar_f_1);
    }

    private void clickedOnAvatar2(View view){
        fromSelectAvatarToActivity.avatarClickedInSelectAvatar(R.drawable.avatar_f_2);
    }

    private void clickedOnAvatar3(View view){
        fromSelectAvatarToActivity.avatarClickedInSelectAvatar(R.drawable.avatar_f_3);
    }

    private void clickedOnAvatar4(View view){
        fromSelectAvatarToActivity.avatarClickedInSelectAvatar(R.drawable.avatar_m_1);
    }

    private void clickedOnAvatar5(View view){
        fromSelectAvatarToActivity.avatarClickedInSelectAvatar(R.drawable.avatar_m_2);
    }

    private void clickedOnAvatar6(View view){
        fromSelectAvatarToActivity.avatarClickedInSelectAvatar(R.drawable.avatar_m_3);
    }


    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        if (context instanceof IntefaceToInclass03Activity){
            fromSelectAvatarToActivity = (IntefaceToInclass03Activity) context;
        }else{
            throw new RuntimeException(context.toString());
        }
    }

}