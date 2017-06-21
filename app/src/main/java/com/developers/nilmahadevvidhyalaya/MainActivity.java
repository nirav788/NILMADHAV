package com.developers.nilmahadevvidhyalaya;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.developers.nilmahadevvidhyalaya.Global.Globals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    GridView grid;
    ImageView img_pop;
    ProgressDialog loadingView;
    public static int display_width = 0;
    private SliderLayout mDemoSlider;
    String url = "http://mobileapp.nilmadhavvidhyalaya.com/webservice/WebServiceAndroid.asmx/GetBanner_detail";
    private Integer[] mThumbIds = {
            R.drawable.about_us, R.drawable.staffdetails, R.drawable.principal_message, R.drawable.management, R.drawable.school_facility, R.drawable.noticebord, R.drawable.events, R.drawable.gallery, R.drawable.video_gallery,
            R.drawable.activitys, R.drawable.student_zone, R.drawable.assignment, R.drawable.results, R.drawable.homework, R.drawable.calender, R.drawable.inquiry, R.drawable.carrer, R.drawable.contact_us,
            R.drawable.daily_dayri, R.drawable.facebook
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tryout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        display_width = (metrics.widthPixels / 3);

        grid = (GridView) findViewById(R.id.gridView1);
        grid.setAdapter(new ImageAdapter(MainActivity.this));
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                if (position == 0) {
                    Intent i = new Intent(MainActivity.this, About_School.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                if (position == 1) {
                    Intent i = new Intent(MainActivity.this, StaffDetails.class);
                    startActivity(i);
                }
                if (position == 2) {
                    Intent i = new Intent(MainActivity.this, Principal_message.class);
                    startActivity(i);
                }
                if (position == 3) {
                    Intent i = new Intent(MainActivity.this, Management_Detail.class);
                    startActivity(i);
                }
                if (position == 4) {
                    Intent i = new Intent(MainActivity.this, School_Facilitys.class);
                    startActivity(i);
                }
                if (position == 5) {
                    Intent i = new Intent(MainActivity.this, Notice.class);
                    startActivity(i);
                }
                if (position == 6) {
                    Intent i = new Intent(MainActivity.this, Events.class);
                    startActivity(i);
                }
                if (position == 7) {
                    Intent i = new Intent(MainActivity.this, Image_Gallery.class);
                    startActivity(i);
                }
                if (position == 8) {
                    Intent i = new Intent(MainActivity.this, Video_Gallary.class);
                    startActivity(i);
                }
                if (position == 9) {
                    Intent i = new Intent(MainActivity.this, ActivityDetails.class);
                    startActivity(i);
                }

                if (position == 10) {
                    Intent i = new Intent(MainActivity.this, Login.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                if (position == 12) {
                    Intent i = new Intent(MainActivity.this, Results.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                if (position == 13) {
                    Intent i = new Intent(MainActivity.this, HomeWork.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                if (position == 15) {
                    Intent i = new Intent(MainActivity.this, Inquiry_School.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                if (position == 16) {
                    Intent i = new Intent(MainActivity.this, Carrer_With_Us.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                if (position == 17) {
                    Intent i = new Intent(MainActivity.this, Contact_us.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                if (position == 18) {
                    Intent i = new Intent(MainActivity.this, DailyDairy.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }

            }
        });
        GallaryLoad();


    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getApplicationContext(), slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

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
                imageView.setLayoutParams(new GridView.LayoutParams(MainActivity.display_width, MainActivity.display_width));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setPadding(4, 2, 4, 2);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

    }

    private void GallaryLoad() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        try {
                            JSONArray jsonObject = response.getJSONArray("Image");
                            for (int i = 0; i < jsonObject.length(); i++) {
                                JSONObject jobjin = jsonObject.getJSONObject(i);


                                String names = jobjin.getString("B_Name");
                                String price_dry = jobjin.getString("ImageName");

                                HashMap<String, String> add_dry = new HashMap<String, String>();
                                add_dry.put(names, Globals.BannermagePath + price_dry);


                                Log.d("key", names);
                                Log.d("val", price_dry);

                                for (String name : add_dry.keySet()) {
                                    TextSliderView textSliderView = new TextSliderView(MainActivity.this);
                                    // initialize a SliderLayout
                                    textSliderView
                                            .description(name)
                                            .image(add_dry.get(name))
                                            .setScaleType(BaseSliderView.ScaleType.Fit)
                                            .setOnSliderClickListener(MainActivity.this);

                                    //add your extra information
                                    textSliderView.bundle(new Bundle());
                                    textSliderView.getBundle()
                                            .putString("extra", name);

                                    mDemoSlider.addSlider(textSliderView);
                                }
                                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                                mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                                mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                                mDemoSlider.setDuration(4000);
                                mDemoSlider.addOnPageChangeListener(MainActivity.this);


                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Not available", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();

            }

        });
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
