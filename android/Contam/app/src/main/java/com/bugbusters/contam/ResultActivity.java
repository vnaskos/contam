package com.bugbusters.contam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasilis on 18/10/16.
 */

public class ResultActivity extends AppCompatActivity {

    List<String> listItems=new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_list);

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
                listItems.add(searchField.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
    }

}
