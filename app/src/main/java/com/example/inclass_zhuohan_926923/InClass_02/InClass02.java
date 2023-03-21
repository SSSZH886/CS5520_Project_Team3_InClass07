package com.example.inclass_zhuohan_926923.InClass_02;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inclass_zhuohan_926923.InClass_01.InClass01;
import com.example.inclass_zhuohan_926923.R;

import java.util.regex.Pattern;

public class InClass02 extends AppCompatActivity {

    private Button button_submit;
    private EditText editText_Name;
    private EditText editText_Email;
    private ImageView imageView_Avatar;

    private RadioGroup radioGroup;
    private TextView textView_Mood;
    private ImageView image_Mood;
    private SeekBar seekBar_Mood;

    final static String userKey = "toDisplay";

    private String avatar;

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

    ActivityResultLauncher<Intent> startActivityForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK){
                avatar = result.getData().getStringExtra("backToEdit");
                if (avatar.equals("f1")){
                    imageView_Avatar.setImageResource(R.drawable.avatar_f_1);
                }else if (avatar.equals("f3")){
                    imageView_Avatar.setImageResource(R.drawable.avatar_f_2);
                }else if (avatar.equals("f2")){
                    imageView_Avatar.setImageResource(R.drawable.avatar_f_3);
                }else if (avatar.equals("m1")){
                    imageView_Avatar.setImageResource(R.drawable.avatar_m_1);
                }else if (avatar.equals("m2")){
                    imageView_Avatar.setImageResource(R.drawable.avatar_m_2);
                }else if (avatar.equals("m3")){
                    imageView_Avatar.setImageResource(R.drawable.avatar_m_3);
                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class02);

        setTitle("Edit Profile Activity");

        button_submit = findViewById(R.id.button_Submit);
        seekBar_Mood = findViewById(R.id.seekBar);
        image_Mood = findViewById(R.id.imageView2);
        editText_Email = findViewById(R.id.editTextEmail);
        editText_Name = findViewById(R.id.editText_Name);
        imageView_Avatar = findViewById(R.id.imageView_select);
        radioGroup = findViewById(R.id.radioGroup);
        textView_Mood = findViewById(R.id.textView_mood);





        seekBar_Mood.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch (i) {
                    case 0:
                        image_Mood.setImageResource(R.drawable.angry);
                        textView_Mood.setText("Angry");
                        break;
                    case 1:
                        image_Mood.setImageResource(R.drawable.sad);
                        textView_Mood.setText("Sad");
                        break;
                    case 2:
                        image_Mood.setImageResource(R.drawable.happy);
                        textView_Mood.setText("Happy");
                        break;
                    case 3:
                        image_Mood.setImageResource(R.drawable.awesome);
                        textView_Mood.setText("Awesome");
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

        imageView_Avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSelectAvatar = new Intent(InClass02.this, selectAvatar.class);
                startActivityForResult.launch(toSelectAvatar);
            }
        });

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText_Name.getText().toString();
                String email = editText_Email.getText().toString();
                String mood = textView_Mood.getText().toString();
                Integer buttonChecked = radioGroup.getCheckedRadioButtonId();
                String selectedRadioButton = ((RadioButton)findViewById(buttonChecked)).getText().toString();

                if(name.length() == 0){
                    editText_Name.setError("No input for name");
                    Toast.makeText(InClass02.this,"No input for name!", Toast.LENGTH_LONG).show();
                }
                if(email.length() == 0){
                    editText_Email.setError("No input for email");
                    Toast.makeText(InClass02.this,"No input for email!", Toast.LENGTH_LONG).show();
                }
                if (isValid(email)){
                    Intent toDisplay = new Intent(InClass02.this, Display.class);

                    User input = new User(name, email, selectedRadioButton, mood, avatar);
                    toDisplay.putExtra(userKey, input);

                    startActivity(toDisplay);
                }else{
                    editText_Email.setError("Invalid input for email");
                    Toast.makeText(InClass02.this,"Invalid input for email!", Toast.LENGTH_LONG).show();
                }





            }
        });
    }
}