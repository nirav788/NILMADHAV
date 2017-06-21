package com.developers.nilmahadevvidhyalaya;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.developers.nilmahadevvidhyalaya.Beans.Beans_Staff_Details;
import com.developers.nilmahadevvidhyalaya.Global.Globals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Developers on 10-05-2017.
 */

public class StaffDetails extends AppCompatActivity {

    private ProgressDialog pDialog;
    ArrayList<Beans_Staff_Details> array_bean = new ArrayList<Beans_Staff_Details>();
    ListView lvls;
    String url = Globals.server_link + "GetStaff_detail";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staffdetails);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lvls = (ListView) findViewById(R.id.listView);
        pDialog = new ProgressDialog(this);


        StaffDeyailsLoad();
    }

    public class CustomAdapter_classwork extends BaseAdapter {

        LayoutInflater inflater;

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

                convertView = getLayoutInflater().inflate(R.layout.staff_details_listrow, null);
            }

            int med1 = Integer.parseInt(array_bean.get(position).getQualification());
            TextView Teachername = (TextView) convertView.findViewById(R.id.name);
            TextView Qualification = (TextView) convertView.findViewById(R.id.Qualifications);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.circle);

            Teachername.setText(array_bean.get(position).getName());


            // Qualification.setText(array_bean.get(position).getQualification());

            if (med1 == 1) {
                Qualification.setText("Principal");
            } else if (med1 == 2) {
                Qualification.setText("Teacher");
            } else if (med1 == 3) {
                Qualification.setText("peon");
            }
            Log.d("staffimage", array_bean.get(position).getImage().toString());

            Glide.with(StaffDetails.this)
                    .load(Globals.StaffImagePath + array_bean.get(position).getImage().toString())
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.progress_animation)
                    .into(imageView);


            return convertView;
        }
    }

    private void StaffDeyailsLoad() {

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

                                Beans_Staff_Details info = new Beans_Staff_Details();

                                info.setName(object1.getString("Name"));
                                info.setQualification(object1.getString("D_Id"));
                                info.setImage(object1.getString("Image"));

                                array_bean.add(info);
                                lvls.setAdapter(new CustomAdapter_classwork());
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
