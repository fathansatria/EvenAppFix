package com.example.eventapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.eventapp.Model.EventModel;
import com.example.eventapp.Model.PesertaModel;
import com.example.eventapp.R;

import java.util.List;

public class PesertaListAdapter extends BaseAdapter {

    private List<PesertaModel> pesertaData;
    private Activity activity;
    private static LayoutInflater inflater=null;

    public PesertaListAdapter(Activity a, List<PesertaModel> pesertaData) {
        activity = a;
        this.pesertaData = pesertaData;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return pesertaData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi =convertView;

        if(convertView==null)

            vi = inflater.inflate(R.layout.people_item,null);


        TextView tv_name= (TextView)vi.findViewById(R.id.et_eventName);
        TextView tv_desc = (TextView)vi.findViewById(R.id.et_eventDesc);


        PesertaModel peserta = pesertaData.get(position);

        tv_name.setText(peserta.getNamaPeserta());
        tv_desc.setText(peserta.getKeterangan());

        return vi;
    }
}
