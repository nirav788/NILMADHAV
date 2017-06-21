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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.developers.nilmahadevvidhyalaya.Beans.Beans_Homework;
import com.developers.nilmahadevvidhyalaya.Global.Globals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Developers on 16-06-2017.
 */

public class ExtraActivity extends AppCompatActivity {

    private Spinner spinner, spinner1, spinner2;
    private ArrayList<String> Satnderd;
    private ArrayList<String> Division;
    private ArrayList<String> Dates;
    private JSONArray result;
    String cardStatusString;
    String Std, Div, Datess;
    Button search;
    private ProgressDialog pDialog;
    ArrayList<Beans_Homework> array_bean = new ArrayList<Beans_Homework>();
    ListView lvls;
    SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date;
    String newdate;
    String url = "http://mobileapp.nilmadhavvidhyalaya.com/webservice/WebServiceAndroid.asmx/GetFinalhomework?std=" + Std + "&div=" + Div + "&date=" + newdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.extra);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Satnderd = new ArrayList<String>();
        Division = new ArrayList<String>();
        Dates = new ArrayList<String>();

        //Initializing Spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        search = (Button) findViewById(R.id.search);
        lvls = (ListView) findViewById(R.id.listView);
        pDialog = new ProgressDialog(this);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StaffDeyailsLoad();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int pos, long id) {
                // cardStatusString = parent.getItemAtPosition(pos).toString();
                Std = parent.getItemAtPosition(pos).toString();
                // Toast.makeText(getApplicationContext(), cardStatusString, Toast.LENGTH_SHORT).show();

                getData1();

                Division.clear();
                Dates.clear();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int pos, long id) {
                // cardStatusString = parent.getItemAtPosition(pos).toString();
                Div = parent.getItemAtPosition(pos).toString();
                //Toast.makeText(getApplicationContext(), cardStatusString, Toast.LENGTH_SHORT).show();


                getData2();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int pos, long id) {
                // cardStatusString = parent.getItemAtPosition(pos).toString();


                Datess = parent.getItemAtPosition(pos).toString();


                Log.d("Dates", Datess);


                try {
                    date = originalFormat.parse(Datess);

                    System.out.println("Old Format :   " + originalFormat.format(date));
                    System.out.println("New Format :   " + targetFormat.format(date));

                    newdate = targetFormat.format(date);

                    Toast.makeText(getApplicationContext(), targetFormat.format(date), Toast.LENGTH_SHORT).show();
                } catch (ParseException ex) {
                    // Handle Exception.
                }

                //Toast.makeText(getApplicationContext(), cardStatusString, Toast.LENGTH_SHORT).show();
                //getData1();


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        getData();
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

                convertView = getLayoutInflater().inflate(R.layout.homework_listrow, null);
            }
            TextView Lession = (TextView) convertView.findViewById(R.id.lessionss);
            TextView STD = (TextView) convertView.findViewById(R.id.stdd);
            TextView Div = (TextView) convertView.findViewById(R.id.Div);
            TextView Date = (TextView) convertView.findViewById(R.id.Date);
            TextView Subjectname = (TextView) convertView.findViewById(R.id.Subjectname);

            Lession.setText(array_bean.get(position).getLession());
            STD.setText(array_bean.get(position).getSt_Id());
            Div.setText(array_bean.get(position).getDiv_Id());
            Date.setText(array_bean.get(position).getDate());
            Subjectname.setText(array_bean.get(position).getSubject_Name());

            return convertView;
        }
    }

    private void getData() {
        //Creating a string request
        StringRequest stringRequest = new StringRequest("http://mobileapp.nilmadhavvidhyalaya.com/webservice/WebServiceAndroid.asmx/Getstd",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray("ContactList");

                            //Calling method getStudents to get the students from the JSON Array
                            getStudents(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getStudents(JSONArray j) {
        //Traversing through all the items in the json array
        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                Satnderd.add(json.getString("Standard"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        spinner.setAdapter(new ArrayAdapter<String>(ExtraActivity.this, android.R.layout.simple_spinner_dropdown_item, Satnderd));
    }


    private void getData1() {
        //Creating a string request
        StringRequest stringRequest = new StringRequest("http://mobileapp.nilmadhavvidhyalaya.com/webservice/WebServiceAndroid.asmx/getdemotest?ddlstd=" + Std,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray("Profile");

                            //Calling method getStudents to get the students from the JSON Array
                            getStudents1(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getStudents1(JSONArray j) {
        //Traversing through all the items in the json array
        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                Division.add(json.getString("Division"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        spinner1.setAdapter(new ArrayAdapter<String>(ExtraActivity.this, android.R.layout.simple_spinner_dropdown_item, Division));
    }

    private void getData2() {

        Log.d("response", Std + Div);
        StringRequest stringRequest = new StringRequest("http://mobileapp.nilmadhavvidhyalaya.com/webservice/WebServiceAndroid.asmx/GetFinalDate?std=" + Std + "&div=" + Div,


                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray("ContactList");

                            //Calling method getStudents to get the students from the JSON Array
                            getStudents2(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getStudents2(JSONArray j) {
        //Traversing through all the items in the json array
        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                Dates.add(json.getString("Date"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        spinner2.setAdapter(new ArrayAdapter<String>(ExtraActivity.this, android.R.layout.simple_spinner_dropdown_item, Dates));
    }


    private void StaffDeyailsLoad() {

        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://mobileapp.nilmadhavvidhyalaya.com/webservice/WebServiceAndroid.asmx/GetFinalhomework?std=" + Std + "&div=" + Div + "&date=" + newdate, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!

                        Log.d("res", Std + "\n" + "&div=" + Div + "\n" + "&date=" + newdate);
                        try {
                            JSONArray jsonObject = response.getJSONArray("ContactList");
                            for (int i = 0; i < jsonObject.length(); i++) {
                                JSONObject object1 = jsonObject.getJSONObject(i);

                                Beans_Homework info = new Beans_Homework();

                                info.setDate(object1.getString("Date"));
                                info.setSt_Id(object1.getString("Standard"));
                                info.setDiv_Id(object1.getString("Division"));
                                info.setLession(object1.getString("Lession"));
                                info.setSubject_Name(object1.getString("Subject_Name"));

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
