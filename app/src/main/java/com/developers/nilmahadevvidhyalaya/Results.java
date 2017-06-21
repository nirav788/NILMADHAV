package com.developers.nilmahadevvidhyalaya;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.developers.nilmahadevvidhyalaya.webview.ElevenStd;
import com.developers.nilmahadevvidhyalaya.webview.NinthStd;
import com.developers.nilmahadevvidhyalaya.webview.Preprimary;
import com.developers.nilmahadevvidhyalaya.webview.Primary;


/**
 * Created by Developers on 11-05-2017.
 */

public class Results extends AppCompatActivity {

    Button button1, button2, button3, button4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button1 = (Button) findViewById(R.id.preprimery);
        button2 = (Button) findViewById(R.id.primery);
        button3 = (Button) findViewById(R.id.ninth);
        button4 = (Button) findViewById(R.id.eleventh);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(getApplicationContext(), Preprimary.class);

                startActivity(i);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Primary.class);
                startActivity(i);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), NinthStd.class);
                startActivity(i);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), ElevenStd.class);
                startActivity(i);
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
