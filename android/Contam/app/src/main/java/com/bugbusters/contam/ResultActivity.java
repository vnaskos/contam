package com.bugbusters.contam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.bugbusters.contam.util.JSONParser;
import com.bugbusters.contam.util.MyLocation;
import com.bugbusters.contam.util.Pair;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasilis on 18/10/16.
 */

public class ResultActivity extends AppCompatActivity {

    private List<String> listItems=new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private JSONParser jParser;
    private MyLocation myLocation;
    private String lat,lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_list);

        jParser = new JSONParser();

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        ListView listView = (ListView) findViewById(R.id.list_view);

        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        listView.setAdapter(adapter);

        final EditText searchField = (EditText) findViewById(R.id.editText2);
        searchField.setText(message);

        Button searchBtn = (Button) findViewById(R.id.button2);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchFor(searchField.getText().toString());
            }
        });

        myLocation = new MyLocation();
        myLocation.getLocation(getApplicationContext(), locationResult);
    }

    public MyLocation.LocationResult locationResult = new MyLocation.LocationResult() {

        @Override
        public void gotLocation(Location location) {
            final double longitude = location.getLongitude();
            final double latitude = location.getLatitude();

            try {
                SharedPreferences locationPref = getApplication()
                        .getSharedPreferences("location", MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = locationPref.edit();
                prefsEditor.putString("Longitude", longitude + "");
                prefsEditor.putString("Latitude", latitude + "");
                prefsEditor.commit();
                lat = latitude + "";
                lon = longitude + "";
                Log.d("Long Lat", longitude + " " + latitude);
            } catch (Exception e) {
                Log.e("Location", e.toString());
            }
        }
    };

    private void searchFor(final String message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Pair> params = new ArrayList<>();

                Log.d("location", lat + "," + lon);

                params.add(new Pair("keyword", message));
                params.add(new Pair("x", lat));
                params.add(new Pair("y", lon));

                String url = "http://78.87.89.2:8080/Contam/api/search?";

                JSONObject json = jParser.makeHttpRequest(url, "GET", params);
                System.out.println(json);

                listItems.clear();

                Type type = new TypeToken<List<Business>>(){}.getType();
                List<Business> inpList = new Gson().fromJson(json.toString(), type);
                for (int i=0;i<inpList.size();i++) {
                    Business b = inpList.get(i);
                    listItems.add(b.getName() + " - " + b.getAddress());
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

}
