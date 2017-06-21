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
import com.developers.nilmahadevvidhyalaya.Beans.Beans_Daily_Dairy;
import com.developers.nilmahadevvidhyalaya.Beans.Beans_Notice;
import com.developers.nilmahadevvidhyalaya.Global.Globals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Developers on 13-06-2017.
 */

public class DailyDairy extends AppCompatActivity {

    private ProgressDialog pDialog;
    ArrayList<Beans_Daily_Dairy> array_bean = new ArrayList<Beans_Daily_Dairy>();
    ListView lvls;
    String url = Globals.server_link + "GetDairy_master";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dailydairy);
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

                convertView = getLayoutInflater().inflate(R.layout.dailydairy_listrow, null);
            }
            TextView date = (TextView) convertView.findViewById(R.id.textViewdate);
            TextView Division = (TextView) convertView.findViewById(R.id.TextViewdivision);
            TextView Description = (TextView) convertView.findViewById(R.id.textViewdescription);
            TextView Name = (TextView) convertView.findViewById(R.id.textViewteacher);
            TextView Subject_Name = (TextView) convertView.findViewById(R.id.textViewsubjectname);
            TextView Standard = (TextView) convertView.findViewById(R.id.TextViewstandard);

            date.setText(array_bean.get(position).getDates());
            Division.setText(array_bean.get(position).getDivision());
            Description.setText(array_bean.get(position).getDescription());
            Name.setText(array_bean.get(position).getName());
            Subject_Name.setText(array_bean.get(position).getSubject_Name());
            Standard.setText(array_bean.get(position).getStandard());


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

                                Beans_Daily_Dairy info = new Beans_Daily_Dairy();

                                info.setName(object1.getString("Name"));
                                info.setDescription(object1.getString("Description"));
                                info.setDates(object1.getString("date"));
                                info.setDivision(object1.getString("Division"));
                                info.setSubject_Name(object1.getString("Subject_Name"));
                                info.setStandard(object1.getString("Standard"));

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
