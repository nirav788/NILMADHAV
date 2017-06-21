package com.developers.nilmahadevvidhyalaya;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
import com.developers.nilmahadevvidhyalaya.Beans.Beans_Management;
import com.developers.nilmahadevvidhyalaya.Global.Globals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Developers on 10-05-2017.
 */

public class Management_Detail extends AppCompatActivity {

    private ProgressDialog pDialog;
    ArrayList<Beans_Management> array_bean = new ArrayList<Beans_Management>();
    ListView lvls;
    String url = Globals.server_link + "GetMgt_Detail";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.management);
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

                convertView = getLayoutInflater().inflate(R.layout.management_details_listrow, null);
            }
            TextView PrincipalName = (TextView) convertView.findViewById(R.id.name);
            WebView Description = (WebView) convertView.findViewById(R.id.description);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.circle);
            PrincipalName.setText(array_bean.get(position).getName());
            Description.setWebViewClient(new MyWebViewClient());

            Description.loadData(array_bean.get(position).getDescription().toString(), "text/html; charset=utf-8", null);

            Glide.with(Management_Detail.this)
                    .load(Globals.ManagementImagePath + array_bean.get(position).getImage().toString())
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.progress_animation)
                    .into(imageView);


            return convertView;
        }

        class MyWebViewClient extends WebViewClient {
            private ProgressDialog progressBar;

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                progressBar = ProgressDialog.show(Management_Detail.this, "Please Wait", "Loading...");
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onLoadResource(WebView view, String url) {

                super.onLoadResource(view, url);

            }
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

                                Beans_Management info = new Beans_Management();

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

