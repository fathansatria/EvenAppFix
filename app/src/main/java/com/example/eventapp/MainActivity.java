package com.example.eventapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eventapp.Adapter.CustomItemClickListener;
import com.example.eventapp.Adapter.EventListAdapter;
import com.example.eventapp.Adapter.EventRecyclerAdapter;
import com.example.eventapp.Adapter.RecyclerViewTouchListener;
import com.example.eventapp.Model.EventModel;
import com.example.eventapp.Model.Item;
import com.example.eventapp.Model.apiInterface;
import com.example.eventapp.Service.apiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private ArrayList<EventModel> events = new ArrayList<>();
    private EventListAdapter eventListAdapter;
    RecyclerView recyclerView;
    private EventRecyclerAdapter eventAdapter;
    private apiService apiInit;
    apiInterface apiI;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInit = new apiService();


        EventModel e1  = new EventModel();
        e1.setEventName("Jalan - Jalan Ke Jepang");
        e1.setDescription("Wajib, Biaya nya gratis ");
        e1.setEventId(1);

        EventModel e2  = new EventModel();
        e2.setEventName("Jalan - Jalan Ke Amerika");
        e2.setDescription("Wajib, Biaya nya gratis ");
        e2.setEventId(2);

        events.add(e1);
        events.add(e2);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(eventAdapter);
        RecyclerView.Adapter adapter = new EventRecyclerAdapter(events);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerView, new CustomItemClickListener() {

            @Override
                public void onClick(View view, int position) {

                    String eventName = events.get(position).getEventName();
                    String eventDesc = events.get(position).getDescription();
                    int eventID = events.get(position).getEventId();

                goToDetail(eventName, eventDesc, eventID);

                }

                @Override
                public void onLongClick(View view, int position) {
                    String eventName = events.get(position).getEventName();
                    String eventDesc = events.get(position).getDescription();
                    int eventID = events.get(position).getEventId();

                    goToDetail(eventName, eventDesc, eventID);
                }
        }));

    }


    void goToDetail(String eventName, String eventDesc, int eventId){

        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra("eventName", eventName);
        intent.putExtra("eventDesc", eventDesc);
        intent.putExtra("eventId", eventId);

        startActivity(intent);
    }

    public void doSearch(String keyword, String type) {
        Call<ArrayList<Item>> Itemmm;
        if("".equals(keyword)) {
            Itemmm = apiI.getJenis(type,0);
        } else {
            Itemmm = apiI.getSearch(type,keyword,0);
        }
        Integer tot = (type == "epaper") ? 1 : 2;
        GridLayoutManager grid = new GridLayoutManager(getActivity(), tot);
        rvPhoto.setLayoutManager(grid);

        Itemmm.enqueue(new Callback<ArrayList<Item>>() {
            @Override
            public void onResponse(Call<ArrayList<Item>> call, Response<ArrayList<Item>> response) {

                if(response.body() == null) {
                    empty_view.setVisibility(View.VISIBLE);
                    rvPhoto.setVisibility(View.GONE);

                }else {
                    afoto = new aItemAdapter(new ArrayList<>(response.body()), getContext());
                    afoto.notifyDataSetChanged();
                    rvPhoto.setAdapter(afoto);
                    empty_view.setVisibility(View.GONE);
                    rvPhoto.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Item>> call, Throwable t) {
                empty_view.setVisibility(View.VISIBLE);
                rvPhoto.setVisibility(View.GONE);
            }
        });
    }
}
