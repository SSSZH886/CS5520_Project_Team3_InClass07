package com.example.inclass_zhuohan_926923.InClass_03;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inclass_zhuohan_926923.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "profile";

    private Profile profile;

    private TextView textViewDisplayName, textViewDisplayEmail, textViewDisplayDevice, textViewDisplayMood;

    private ImageView imageViewMood, imageViewAvatar;

    // TODO: Rename and change types of parameters
    private String mParam1;

    public DisplayFragment() {
        // Required empty public constructor
    }

    public static DisplayFragment newInstance(Profile profile) {
        DisplayFragment fragment = new DisplayFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, profile);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            profile = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        textViewDisplayName = view.findViewById(R.id.inClass03_textView_displayName);
        textViewDisplayEmail = view.findViewById(R.id.inClass03_textView_displayEmail);
        textViewDisplayDevice = view.findViewById(R.id.inClass03_textView_displayPhone);
        textViewDisplayMood = view.findViewById(R.id.inClass03_textView_displayMood);
        imageViewAvatar = view.findViewById(R.id.inClass03_imageView_displayAvatar);
        imageViewMood = view.findViewById(R.id.inClass03_imageView_displayMood);

        textViewDisplayName.setText(profile.getName());
        textViewDisplayEmail.setText(profile.getEmail());
        textViewDisplayDevice.setText(profile.getDevice());
        textViewDisplayMood.setText(profile.getMood());
        imageViewAvatar.setImageResource(profile.getAvatarDrawable());

        if (profile.getMood().equals("Angry")){
            imageViewMood.setImageResource(R.drawable.angry);
        }else if(profile.getMood().equals("Sad")){
            imageViewMood.setImageResource(R.drawable.sad);
        }else if(profile.getMood().equals("Happy")) {
            imageViewMood.setImageResource(R.drawable.happy);
        }else if(profile.getMood().equals("Awesome")){
            imageViewMood.setImageResource(R.drawable.awesome);
        }

        return view;
    }

}