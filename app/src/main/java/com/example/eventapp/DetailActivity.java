package com.example.eventapp;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.eventapp.Adapter.PesertaListAdapter;
import com.example.eventapp.Database.DatabaseHelper;
import com.example.eventapp.Model.PesertaModel;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private Button btnJoin;
    private Dialog dialog;
    private Button btnDaftar;
    private Button btnCancel;
    private String newName, newTelp, newEmail;
    private EditText et_nama, et_telp, et_email;
    private DatabaseHelper db;
    private List<PesertaModel> pesertaModels;
    private ListView lv_peserta;
    private PesertaListAdapter listAdapter;
    private int event_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = new DatabaseHelper(this);
        btnJoin = findViewById(R.id.btn_join);
        lv_peserta = findViewById(R.id.list_perserta);

        Bundle extras = getIntent().getExtras();
        if(extras != null){

            event_id = extras.getInt("eventId");

        }
        else{
            event_id = 0;
        }

        dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.add_peserta_popup);

        btnCancel = (Button)dialog.findViewById(R.id.btn_cancel);
        btnDaftar = (Button)dialog.findViewById(R.id.btn_daftar);
        et_nama = (EditText)dialog.findViewById(R.id.et_nama);
        et_telp = (EditText)dialog.findViewById(R.id.et_telepon);
        et_email = (EditText)dialog.findViewById(R.id.et_email);


        //inisialisasi list

        pesertaModels = db.getAllPesertaByEventId(event_id);
        listAdapter = new PesertaListAdapter(this, pesertaModels);
        lv_peserta.setAdapter(listAdapter);


        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp();
            }
        });




        db.closeDB();

    }

    public void showPopUp(){


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newName = et_nama.getText().toString();
                newTelp = et_telp.getText().toString();
                newEmail = et_email.getText().toString();

                PesertaModel c1 = new PesertaModel();
                c1.setNamaPeserta(newName);
                c1.setEmail(newEmail);
                c1.setPhone(newTelp);
                c1.setId_event(event_id);

                db.daftarPeserta(c1);
                dialog.dismiss();
                db.closeDB();

                recreate();
            }
        });

        dialog.show();


    }

}
