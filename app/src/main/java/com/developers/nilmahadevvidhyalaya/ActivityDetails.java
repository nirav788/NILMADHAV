package com.developers.nilmahadevvidhyalaya;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.developers.nilmahadevvidhyalaya.Beans.Beans_Notice;
import com.developers.nilmahadevvidhyalaya.Global.Globals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Developers on 27-05-2017.
 */

public class ActivityDetails extends AppCompatActivity{

    private ProgressDialog pDialog;
    ArrayList<Beans_Notice> array_bean = new ArrayList<Beans_Notice>();
    ListView lvls;
    String url = Globals.server_link+"GetActivity_Detail";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activitys);
        lvls = (ListView) findViewById(R.id.listView);
        pDialog = new ProgressDialog(this);


        PrincipalMessagesLoad();

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

                convertView = getLayoutInflater().inflate(R.layout.activity_list_row, null);
            }
            TextView PrincipalName = (TextView) convertView.findViewById(R.id.name);
            TextView Description = (TextView) convertView.findViewById(R.id.description);
            ImageView imageView = (ImageView)convertView.findViewById(R.id.circle);

            PrincipalName.setText(array_bean.get(position).getName());
            Description.setText(array_bean.get(position).getDescription());
            Glide.with(ActivityDetails.this)
                    .load(Globals.ActivityImagePath + array_bean.get(position).getImage().toString())
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.progress_animation)
                    .into(imageView);



            return convertView;
        }
    }

    private void PrincipalMessagesLoad() {

        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        try {
                            JSONArray jsonObject = response.getJSONArray("ContactList");
                            for (int i = 0; i < jsonObject.length(); i++) {
                                JSONObject object1 = jsonObject.getJSONObject(i);

                                Beans_Notice info = new Beans_Notice();

                                info.setName(object1.getString("Name"));
                                info.setDescription(object1.getString("Description"));
                                info.setImage(object1.getString("Image"));

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
