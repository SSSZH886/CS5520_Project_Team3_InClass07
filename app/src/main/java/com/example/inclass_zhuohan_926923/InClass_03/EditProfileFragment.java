package com.example.inclass_zhuohan_926923.InClass_03;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inclass_zhuohan_926923.InClass_02.InClass02;
import com.example.inclass_zhuohan_926923.R;

import java.util.regex.Pattern;

public class EditProfileFragment extends Fragment {

    private String name, email, mood;
    private int device;

    // TODO: Rename and change types of parameters
    private String selectedAvtar;

    private int avatarDrawable;
    private Profile profile;

    private EditText editTextName, editTextEmail;
    private Button buttonSubmit;
    private SeekBar seekBar;
    private ImageView imageViewAvatar, imageViewMood;
    private TextView textViewMood;
    private RadioGroup radioGroup;

    private IntefaceToInclass03Activity fromEditToActivity;
    public EditProfileFragment() {
        // Required empty public constructor
    }

    public static EditProfileFragment newInstance() {
        EditProfileFragment fragment = new EditProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        profile = new Profile();

        if (getArguments() != null) {

        }
    }

    public static boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        editTextName = rootView.findViewById(R.id.inClass03_editText_Name);
        editTextEmail = rootView.findViewById(R.id.inClass03_editText_Email);
        buttonSubmit = rootView.findViewById(R.id.inClass03_button_submit);
        seekBar = rootView.findViewById(R.id.inClass03_seekBar);
        imageViewAvatar = rootView.findViewById(R.id.inClass03_imageView_emptyAvatar);
        imageViewMood = rootView.findViewById(R.id.inClass03_imageView_mood);
        textViewMood = rootView.findViewById(R.id.inClass03_textView_moood);
        radioGroup = rootView.findViewById(R.id.inClass03_radioGroup);

        getActivity().setTitle("Edit Profile");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch (i) {
                    case 0:
                        imageViewMood.setImageResource(R.drawable.angry);
                        textViewMood.setText("Angry");
                        break;
                    case 1:
                        imageViewMood.setImageResource(R.drawable.sad);
                        textViewMood.setText("Sad");
                        break;
                    case 2:
                        imageViewMood.setImageResource(R.drawable.happy);
                        textViewMood.setText("Happy");
                        break;
                    case 3:
                        imageViewMood.setImageResource(R.drawable.awesome);
                        textViewMood.setText("Awesome");
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        imageViewAvatar.setOnClickListener(this::onSelectAvatarClicked);


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = String.valueOf(editTextName.getText());
                email = String.valueOf(editTextEmail.getText());
                mood = String.valueOf(textViewMood.getText());
                Integer buttonChecked = radioGroup.getCheckedRadioButtonId();


                if (name.length() == 0){
                    editTextName.setError("No input for name");
                    Toast.makeText(getActivity(), "No input for name!", Toast.LENGTH_LONG).show();
                }else if (email.length() == 0){
                    editTextEmail.setError("No input for email");
                    Toast.makeText(getActivity(), "No input for email!", Toast.LENGTH_LONG).show();
                }else if(!isValid(email)){
                    editTextEmail.setError("Not validate input for email");
                    Toast.makeText(getActivity(), "Not validate input for email!", Toast.LENGTH_LONG).show();
                }else if(avatarDrawable == 0){
                    Toast.makeText(getActivity(), "No input for avatar!", Toast.LENGTH_LONG).show();
                }else if (buttonChecked < 0){
                    Toast.makeText(getActivity(), "No input for radio group!", Toast.LENGTH_LONG).show();
                }else{

                    String selectedRadioButton = ((RadioButton) rootView.findViewById(buttonChecked)).getText().toString();
                    profile.setName(name);
                    profile.setEmail(email);
                    profile.setMood(mood);
                    profile.setDevice(selectedRadioButton);
                    profile.setAvatarDrawable(avatarDrawable);

                    //sending the profile object to Activity......
                    fromEditToActivity.submitButtonClickedInEditProfile(profile);
                }


            }
        });



        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getArguments() != null){
            Bundle receivedData = getArguments();
            // Checking if we have a new selected avatar here.....
            if(receivedData.containsKey("drawableID")){
                avatarDrawable = receivedData.getInt("drawableID");
                imageViewAvatar.setImageResource(avatarDrawable);
            }
        }
    }

    private void onSelectAvatarClicked(View view) {
        fromEditToActivity.selectAvtarClickedInEditProfile();
    }

//    private void onSubmitButtonClicked(View view) {
//        //TODO: validation checks, update the profile, and send the profile object to activity....
//        //Omitting validation for this example...
//        name = String.valueOf(editTextName.getText());
//        email = String.valueOf(editTextEmail.getText());
//        mood = String.valueOf(textViewMood.getText());
//        Integer buttonChecked = radioGroup.getCheckedRadioButtonId();
//        String selectedRadioButton = ((RadioButton) rootView.findViewById(buttonChecked)).getText().toString();
//
//        profile.setName(name);
//        profile.setEmail(email);
//        profile.setMood(mood);
//        profile.setDevice(selectedRadioButton);
//        profile.setAvatarDrawable(avatarDrawable);
//
//        //sending the profile object to Activity......
//        fromEditToActivity.submitButtonClickedInEditProfile(profile);
//
//    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof IntefaceToInclass03Activity){
            fromEditToActivity = (IntefaceToInclass03Activity) context;
        }else {
            throw new RuntimeException(context.toString()+ "must implement InterfaceFromEditToActivity");
        }

    }


}