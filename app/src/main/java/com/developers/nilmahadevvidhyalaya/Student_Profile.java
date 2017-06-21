package com.developers.nilmahadevvidhyalaya;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.developers.nilmahadevvidhyalaya.Global.Globals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import static com.developers.nilmahadevvidhyalaya.Extras.CommonConstants.Login_Shared_PREFERENCES;
import static com.developers.nilmahadevvidhyalaya.Extras.CommonConstants.User_KEY;

/**
 * Created by Developers on 01-06-2017.
 */

public class Student_Profile extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String MOB;
    String url = Globals.server_link + "GetStudent_Profile";
    String GRNO, S_NAME, STD, DIV, ROLLNO, MOBILENOS, ADDRESS;
    ImageView StudentPIC;
    TextView StudName, GR_NO, std, div, roll_no, Mobile_no, Address;
    ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.studentprofile);
        StudentPIC = (ImageView) findViewById(R.id.StudentPIC);
        StudName = (TextView) findViewById(R.id.name);
        GR_NO = (TextView) findViewById(R.id.about);
        std = (TextView) findViewById(R.id.std);
        div = (TextView) findViewById(R.id.div);
        roll_no = (TextView) findViewById(R.id.roll_no);
        Mobile_no = (TextView) findViewById(R.id.Mobile_no);
        Address = (TextView) findViewById(R.id.Address);
        back = (ImageView) findViewById(R.id.back);

        sharedPreferences = this.getSharedPreferences(Login_Shared_PREFERENCES, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(User_KEY)) {
            MOB = sharedPreferences.getString(User_KEY, "");

        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivityTwo.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        makeJsonLoginRequest();

    }

    private void makeJsonLoginRequest() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject BaseObject = new JSONObject(response);
                            JSONArray abc = BaseObject.getJSONArray("Profile");


                            for (int i = 0; i < abc.length(); i++) {
                                JSONObject c = abc.getJSONObject(i);

                                GRNO = c.getString("GrNo");
                                S_NAME = c.getString("S_Name");
                                STD = c.getString("Std");
                                DIV = c.getString("Division");
                                ROLLNO = c.getString("Rollno");
                                MOBILENOS = c.getString("Mobile");
                                ADDRESS = c.getString("Address");

                            }


                            StudName.setText(S_NAME);
                            GR_NO.setText("GR.NO | " + GRNO);
                            std.setText(STD);
                            div.setText(DIV);
                            roll_no.setText(ROLLNO);
                            Mobile_no.setText(MOBILENOS);
                            Address.setText(ADDRESS);

                            // Log.d("data", GRNO + "\n" + S_NAME + "\n" + STD + "\n" + DIV + "\n" + ROLLNO + "\n" + MOBILENOS + "\n" + ADDRESS);

                        } catch (JSONException e) {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Student_Profile.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Mobile", MOB);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


        Log.d("response", stringRequest.toString());
    }

}
