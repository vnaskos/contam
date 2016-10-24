package com.bugbusters.contam;

import android.content.Intent;
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

import org.json.JSONObject;

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
    private MyLocation.LocationResult locationResult;
    private double x,y;

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
                listItems.add(searchField.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        myLocation = new MyLocation();
        locationResult = new MyLocation.LocationResult() {
            @Override
            public void gotLocation(Location location) {
                x = location.getLatitude();
                y = location.getLongitude();
            }
        };
        myLocation.getLocation(getApplicationContext(), locationResult);
    }

    private void searchFor(final String message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Pair> params = new ArrayList<>();

                myLocation.getLocation(getApplicationContext(), locationResult);
                Log.d("location", x + "," + y);

                params.add(new Pair("keyword", message));
                params.add(new Pair("x", x+""));
                params.add(new Pair("y", y+""));

                String url = "http://192.168.1.53:8084/Contam/api/search";

                JSONObject json = jParser.makeHttpRequest(url, "GET", params);
                System.out.println(json);
            }
        }).start();
    }

}
