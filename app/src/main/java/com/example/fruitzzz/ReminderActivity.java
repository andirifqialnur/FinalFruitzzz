package com.example.fruitzzz;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ReminderActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    ListView reminderListView;
    Toolbar toolbar;
    ProgressDialog prgDialog;
    ArrayList<String> arr_title, arr_date, arr_time;
    private Context context;

    private static final int VEHICLE_LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        toolbar = findViewById(R.id.toolbar);
        reminderListView = findViewById(R.id.list);

        toolbar.setTitle("Alarm Reminder");

        reminderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(ReminderActivity.this, ReminderAddActivity.class);
                startActivity(intent);
            }
        });

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener((v) -> {
            Intent intent = new Intent(v.getContext(), ReminderAddActivity.class);
            startActivity(intent);
        });

    }

}