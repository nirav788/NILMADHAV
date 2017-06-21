package com.developers.nilmahadevvidhyalaya;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.developers.nilmahadevvidhyalaya.Global.Globals;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.developers.nilmahadevvidhyalaya.Extras.CommonConstants.Login_Shared_PREFERENCES;
import static com.developers.nilmahadevvidhyalaya.Extras.CommonConstants.STUDENT;
import static com.developers.nilmahadevvidhyalaya.Extras.CommonConstants.User_KEY;

/**
 * Created by Developers on 24-02-2017.
 */

public class Login extends AppCompatActivity {

    EditText username;
    Button Login;
    TextView ForgotPwd, Create_New_Acc;
    String MobileNumber, FCMKEY;
    private static final String TAG = "MainActivity";
    String url = Globals.server_link+"LoginAuthenticate";
    String ids, Mobileno, OTPno, STUDENID;
    SharedPreferences sharedPreferences;
    String Student, MOB;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = this.getSharedPreferences(Login_Shared_PREFERENCES,
                Context.MODE_PRIVATE);

        if (sharedPreferences.contains(User_KEY)) {
            MOB = sharedPreferences.getString(User_KEY, "");
            Student = sharedPreferences.getString(STUDENT, "");
            Intent intent = new Intent(Login.this, MainActivityTwo.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Welcome user ", Toast.LENGTH_SHORT).show();
        } else {

            username = (EditText) findViewById(R.id.Unmae);
            ForgotPwd = (TextView) findViewById(R.id.forgetpwd);
            Create_New_Acc = (TextView) findViewById(R.id.Ragistartion);
            Login = (Button) findViewById(R.id.Login_btn);
            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            FCMKEY = FirebaseInstanceId.getInstance().getToken();

            Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Login.setVisibility(View.GONE);
                    FCMKEY = FirebaseInstanceId.getInstance().getToken();
                    Log.d("fcmkey", FCMKEY);
                    if (username.getText().toString().equals("") || username.getText().toString().equals(null)) {

                        Toast.makeText(getApplicationContext(), "Please EnterMobile Number", Toast.LENGTH_SHORT).show();
                    } else if (FCMKEY.equals("") || FCMKEY.equals("null")) {

                        Toast.makeText(getApplicationContext(), "Please Wait", Toast.LENGTH_SHORT).show();
                    } else {
                        MobileNumber = username.getText().toString().trim();

                        makeJsonLoginRequest();


                        progressBar.setVisibility(View.VISIBLE);
                    }
                }
            });


            Create_New_Acc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getApplicationContext(), "Please Contact to School", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    private void makeJsonLoginRequest() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject BaseObject = new JSONObject(response);
                            JSONArray abc = BaseObject.getJSONArray("LoginList");


                            for (int i = 0; i < abc.length(); i++) {
                                JSONObject c = abc.getJSONObject(i);

                                ids = c.getString("Id");
                                Mobileno = c.getString("Mobileno");
                                OTPno = c.getString("OTPno");

                                Intent j = new Intent(getApplicationContext(), OneTimePassWord.class);
                                j.putExtra("OTP", OTPno);
                                j.putExtra("MOBILE", Mobileno);
                                j.putExtra("Id", ids);
                                startActivity(j);
                                Toast.makeText(getApplicationContext(), "Please Wait For OTP..!", Toast.LENGTH_SHORT).show();
                                finish();

                            }


                        } catch (JSONException e) {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Login.setVisibility(View.VISIBLE);
                        Toast.makeText(Login.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Mobile", MobileNumber);
                params.put("Gcm", FCMKEY);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


        Log.d("response", stringRequest.toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

}