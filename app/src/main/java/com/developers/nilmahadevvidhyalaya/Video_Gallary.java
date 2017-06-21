package com.developers.nilmahadevvidhyalaya;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.developers.nilmahadevvidhyalaya.Beans.Beans_Video_Gallery;
import com.developers.nilmahadevvidhyalaya.Global.Globals;

import android.widget.AdapterView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Developers on 20-05-2017.
 */

public class Video_Gallary extends AppCompatActivity {
    private ProgressDialog pDialog;
    ArrayList<Beans_Video_Gallery> array_bean = new ArrayList<Beans_Video_Gallery>();
    ListView lvls;
    String url = Globals.server_link + "GetVideo_detail";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gallery);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lvls = (ListView) findViewById(R.id.listView);
        pDialog = new ProgressDialog(this);

        lvls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent j = new Intent(getApplicationContext(),Youtube_Player.class);
                j.putExtra("id", array_bean.get(position).getVideo());


                startActivity(j);
            }
        });


        GallaryLoad();
        /* lvls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(getApplicationContext(),Youtube_Player.class);
                intent.putExtra("id", array_bean.get(position).getVideo());
                startActivity(intent);
            }
        });*/
    }

    public class CustomAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return array_bean.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            // TODO Auto-generated method stub

            if (convertView == null) {

                convertView = getLayoutInflater().inflate(R.layout.videogallary, null);
            }

            TextView imageView = (TextView) convertView.findViewById(R.id.videoname);

            imageView.setText(array_bean.get(position).getVideo_Name());


            return convertView;
        }
    }

    private void GallaryLoad() {

        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        try {
                            JSONArray jsonObject = response.getJSONArray("Image");
                            for (int i = 0; i < jsonObject.length(); i++) {
                                JSONObject object1 = jsonObject.getJSONObject(i);

                                Beans_Video_Gallery info = new Beans_Video_Gallery();

                                info.setVideo_Name(object1.getString("Video_Name"));
                                info.setVideo(object1.getString("Video"));

                                array_bean.add(info);
                                lvls.setAdapter(new CustomAdapter());
                                pDialog.hide();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Not available", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                            pDialog.hide();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.hide();
                Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();

            }

        });
        Volley.newRequestQueue(this).add(jsonObjectRequest);
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