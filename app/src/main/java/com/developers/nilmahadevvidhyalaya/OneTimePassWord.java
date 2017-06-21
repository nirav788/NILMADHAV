package com.developers.nilmahadevvidhyalaya;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.developers.nilmahadevvidhyalaya.Extras.CommonConstants.Login_Shared_PREFERENCES;
import static com.developers.nilmahadevvidhyalaya.Extras.CommonConstants.STUDENT;
import static com.developers.nilmahadevvidhyalaya.Extras.CommonConstants.User_KEY;

/**
 * Created by Developers on 27-05-2017.
 */

public class OneTimePassWord extends AppCompatActivity {

    EditText OneTimePWD;
    Button Submit;
    String otp, myotp, MOBILE, STUDENTID;
    SharedPreferences sharedPreferences;
    String TAG = OneTimePassWord.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onetimepwd);

        sharedPreferences = this.getSharedPreferences(Login_Shared_PREFERENCES, Context.MODE_PRIVATE);
        OneTimePWD = (EditText) findViewById(R.id.OTP);
        Submit = (Button) findViewById(R.id.Submit);

        otp = getIntent().getStringExtra("OTP");
        MOBILE = getIntent().getStringExtra("MOBILE");
        STUDENTID = getIntent().getStringExtra("Id");

        if (otp.equals("") || otp.equals(null)) {
            Toast.makeText(getApplicationContext(), "Please Wait", Toast.LENGTH_SHORT).show();
        } else {
        }

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myotp = OneTimePWD.getText().toString();
                if (otp.equals(myotp)) {

                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(User_KEY, MOBILE.toString());
                    editor.putString(STUDENT, STUDENTID.toString());
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), MainActivityTwo.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);


                } else {

                    Toast.makeText(getApplicationContext(), "Please Enter Vaild OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
