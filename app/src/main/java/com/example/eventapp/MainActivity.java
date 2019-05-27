package com.example.eventapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eventapp.Adapter.EventListAdapter;
import com.example.eventapp.Model.EventModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView eventList;
    private List<EventModel> events = new ArrayList<EventModel>();
    private EventListAdapter eventListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EventModel e1  = new EventModel();
        e1.setEventName("Jalan - Jalan Ke Jepang");
        e1.setDescription("Wajib, Biaya nya gratis ");

        events.add(e1);

        eventList = findViewById(R.id.list_events);
        eventListAdapter = new EventListAdapter(this, events);
        eventList.setAdapter(eventListAdapter);

        eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String eventName = events.get(i).getEventName();
                String eventDesc = events.get(i).getDescription();
                Toast.makeText(getApplicationContext(), eventName, Toast.LENGTH_LONG).show();
                goToDetail(eventName, eventDesc);

            }
        });


    }

    void goToDetail(String eventName, String eventDesc){

        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra("eventName", eventName);
        intent.putExtra("eventDesc", eventDesc);
        startActivity(intent);
    }
}
