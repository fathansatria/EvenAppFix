package com.example.eventapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventapp.DetailActivity;
import com.example.eventapp.MainActivity;
import com.example.eventapp.Model.EventModel;
import com.example.eventapp.R;

import java.util.ArrayList;
import java.util.List;

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.EventViewHolder> {

    private ArrayList<EventModel> dataList;
    private String eventName, eventDesc;

    public EventRecyclerAdapter(ArrayList<EventModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public EventRecyclerAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);


    }

    @Override
    public void onBindViewHolder(EventRecyclerAdapter.EventViewHolder holder, int position) {

        holder.tv_event_name.setText(dataList.get(position).getEventName());
        holder.tv_event_desc.setText(dataList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }


    public static class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_event_name, tv_event_desc;

        //private CustomItemClickListener mListener;


        public EventViewHolder(View itemView) {
            super(itemView);
            tv_event_name = (TextView) itemView.findViewById(R.id.tv_eventName);
            tv_event_desc = (TextView) itemView.findViewById(R.id.tv_eventDesc);



        }


        @Override
        public void onClick(View v) {

            int pos = getLayoutPosition();

        }
    }



}
