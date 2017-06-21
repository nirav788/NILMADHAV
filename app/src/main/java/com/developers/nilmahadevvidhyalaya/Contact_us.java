package com.developers.nilmahadevvidhyalaya;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Developers on 13-04-2017.
 */

public class Contact_us extends AppCompatActivity {

    AppCompatImageButton contact_one_Button,contact_two_Button;
    TextView emailId_matrix_TextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us);
        contact_one_Button = (AppCompatImageButton) findViewById(R.id.contact_us_call_one);
        emailId_matrix_TextView= (TextView)findViewById(R.id.emailId_matrix_TextView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        contact_one_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:" + "0261-6594888"));
                startActivity(i);
            }
        });


        emailId_matrix_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to = "Info@nilmadhavschol.gmail.com";
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.setData(Uri.parse("mailto:"+to));
                intent.putExtra(Intent.EXTRA_EMAIL,to);
                try{
                    startActivity(Intent.createChooser(intent,"Send Email"));
                }
                catch(Exception e){
                    Toast.makeText(getApplicationContext(),e.getStackTrace().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
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
