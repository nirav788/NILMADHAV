package com.developers.nilmahadevvidhyalaya.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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
import com.developers.nilmahadevvidhyalaya.Beans.Beans_Events;
import com.developers.nilmahadevvidhyalaya.Global.Globals;
import com.developers.nilmahadevvidhyalaya.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Developers on 14-02-2017.
 */

public class FourFragment extends Fragment {

    ArrayList<Beans_Events> array_bean = new ArrayList<Beans_Events>();
    ListView lvls;
    String url = Globals.server_link + "GetCalender_Events";
    public ProgressDialog pDialog;

    View view;

    public FourFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.class_rooms, container, false);


        lvls = (ListView) view.findViewById(R.id.listView);

        pDialog = new ProgressDialog(getActivity());
        StaffDeyailsLoad();

        return view;
    }


    public class CustomAdapter_classwork extends BaseAdapter {

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

                convertView = getActivity().getLayoutInflater().inflate(
                        R.layout.event_listrow, null);
            }


            TextView Teachername = (TextView) convertView.findViewById(R.id.name);
            TextView Qualification = (TextView) convertView.findViewById(R.id.Qualifications);

            Teachername.setText(array_bean.get(position).getEvent_Name());
            Qualification.setText(array_bean.get(position).getColumn1());


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

                                Beans_Events info = new Beans_Events();

                                info.setColumn1(object1.getString("Column1"));
                                info.setEvent_Name(object1.getString("Event_Name"));

                                array_bean.add(info);
                                lvls.setAdapter(new CustomAdapter_classwork());
                                pDialog.hide();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getActivity(), "Not available", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                            pDialog.hide();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.hide();
                Toast.makeText(getActivity(), "something went wrong", Toast.LENGTH_LONG).show();

            }

        });
        Volley.newRequestQueue(getActivity()).add(jsonObjectRequest);
    }

}