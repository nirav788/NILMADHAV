package com.developers.nilmahadevvidhyalaya;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import static com.developers.nilmahadevvidhyalaya.Extras.CommonConstants.Login_Shared_PREFERENCES;
import static com.developers.nilmahadevvidhyalaya.Extras.CommonConstants.STUDENT;
import static com.developers.nilmahadevvidhyalaya.Extras.CommonConstants.User_KEY;


public class MainActivityTwo extends AppCompatActivity {

    GridView grid;
    public static int display_width = 0;
    SharedPreferences sharedPreferences;
    String MOB, Student;

    private Integer[] mThumbIds = {
            R.drawable.profile, R.drawable.results, R.drawable.attandance, R.drawable.fees
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymaintwo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        display_width = (metrics.widthPixels / 3);

        grid = (GridView) findViewById(R.id.gridView2);
        grid.setAdapter(new ImageAdapter(MainActivityTwo.this));
        sharedPreferences = this.getSharedPreferences(Login_Shared_PREFERENCES, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(User_KEY)) {
            MOB = sharedPreferences.getString(User_KEY, "");
            Student = sharedPreferences.getString(STUDENT, "");

        }

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                if (position == 0) {
                    Intent i = new Intent(MainActivityTwo.this, Student_Profile.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                if (position == 1) {
                    Intent i = new Intent(MainActivityTwo.this, Results.class);
                    startActivity(i);
                }
                if (position == 2) {
                    Intent i = new Intent(MainActivityTwo.this, Student_Attandance.class);
                    startActivity(i);
                }
                if (position == 3) {
                    Intent i = new Intent(MainActivityTwo.this, Student_Fees.class);
                    startActivity(i);
                }/*
                if (position == 4) {
                    Intent i = new Intent(MainActivityTwo.this, School_Facilitys.class);
                    startActivity(i);
                }
                if (position == 5) {
                    Intent i = new Intent(MainActivityTwo.this, Notice.class);
                    startActivity(i);
                }
                if (position == 6) {
                    Intent i = new Intent(MainActivityTwo.this, Events.class);
                    startActivity(i);
                }
                if (position == 7) {
                    Intent i = new Intent(MainActivityTwo.this, Image_Gallery.class);
                    startActivity(i);
                }

                if (position == 10) {
                    Intent i = new Intent(MainActivityTwo.this, Login.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                if (position == 12) {
                    Intent i = new Intent(MainActivityTwo.this, Results.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                if (position == 14) {
                    Intent i = new Intent(MainActivityTwo.this, HomeWork.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                if (position == 17) {
                    Intent i = new Intent(MainActivityTwo.this, Inquiry_School.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                if (position == 18) {
                    Intent i = new Intent(MainActivityTwo.this, Carrer_With_Us.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                if (position == 19) {
                    Intent i = new Intent(MainActivityTwo.this, Contact_us.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }*/

            }
        });
    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(MainActivityTwo.display_width, MainActivityTwo.display_width));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setPadding(4, 2, 4, 2);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (item.getItemId() == android.R.id.home) {
            // close this activity and return to preview activity (if there is any)


            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }


        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_logout) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            Toast.makeText(getApplicationContext(), "Logout Sucessfully", Toast.LENGTH_SHORT).show();
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
