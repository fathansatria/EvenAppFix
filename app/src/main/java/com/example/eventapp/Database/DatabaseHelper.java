package com.example.eventapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.eventapp.Model.PesertaModel;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final static String DATABASENAME = "DbEvent";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PESERTA = "mahasiswa_table";
    private static final String ID = "id";
    private static final String NAMA = "nama";
    private static final String KETERANGAN = "Keterangan";


    private String TBL_CREATE_MHS = "create table " + TABLE_PESERTA + " (" +
            ID + " int primary key," +
            NAMA + " text," +
            KETERANGAN + " text )";

    private static final String TABLE_MATKUL = "matkul_table";
    private static final String KODEKULIAH = "kodekuliah";
    private static final String NAMAMATKUL = "namamatkul";
    private static final String SKS = "sks";
    private static final String KUIS = "kuis";
    private static final String TUGAS = "tugas";
    private static final String UTS = "uts";
    private static final String UAS = "uas";
    private static final String FINALPROJECT = "finalproject";
    private static final String PRAKTIKUM = "praktikum";
    private static final String DOSEN = "dosen";
    private static final String USER = "user";
    private static final String KUIS_PERSENT = "kuis_persent";
    private static final String TUGAS_PERSENT = "tugas_persent";
    private static final String UTS_PERSENT = "uts_persent";
    private static final String UAS_PERSENT = "uas_persent";
    private static final String FINALPROJECT_PERSENT = "finalproject_persent";
    private static final String PRAKTIKUM_PERSENT = "praktikum_persent";
    private static final String TARGET = "target";
    private static final String JUMLAH_KUIS = "jumlah_kuis";
    private static final String JUMLAH_TUGAS = "jumlah_tugas";






    public DatabaseHelper(Context context)
    {
        super(context,DATABASENAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TBL_CREATE_MHS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MATKUL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PESERTA);
        onCreate(sqLiteDatabase);
    }

    public long registerStudent(PesertaModel peserta){
        SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(NAMA, peserta.getNamaPeserta());
            values.put(KETERANGAN, peserta.getKeterangan());
            long id = db.insert(TABLE_PESERTA, null, values);

        // assigning tags to tbl create mengambil
        db.close();
        return id ;
    }

    /*
     * Creating tag
     */




    public List<PesertaModel> getAllPeserta() {

        List<PesertaModel> pesertaModels = new ArrayList<PesertaModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_PESERTA;

        Log.e(DATABASENAME, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                PesertaModel std = new PesertaModel();
                std.setNamaPeserta((c.getString(c.getColumnIndex(NAMA))));
                std.setKeterangan(c.getString(c.getColumnIndex(KETERANGAN)));

                // adding to todo list
                pesertaModels.add(std);
            } while (c.moveToNext());
        }

        return pesertaModels;
    }





    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

//    public List<Course> getAllCoursesByUsername(String username) {
//        List<Course> courses = new ArrayList<Course>();
//
//        String selectQuery = "SELECT  * FROM " + TABLE_MATKUL + " tm WHERE tm."
//                + USER + " = '" + username + "'";
//
//        Log.e(DATABASENAME, selectQuery);
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (c.moveToFirst()) {
//            do {
//
//                Course c1 = new Course();
//                c1.setCourseName(c.getString((c.getColumnIndex(NAMAMATKUL))));
//                c1.setTeacher(c.getString((c.getColumnIndex(DOSEN))));
//                c1.setSks(c.getInt((c.getColumnIndex(SKS))));
//                c1.setJumlah_quiz(c.getInt((c.getColumnIndex(JUMLAH_KUIS))));
//                c1.setJumlah_tugas(c.getInt((c.getColumnIndex(JUMLAH_TUGAS))));
//                c1.setUser(c.getString((c.getColumnIndex(USER))));
//                c1.setUts(c.getDouble((c.getColumnIndex(UTS))));
//                c1.setUas(c.getDouble((c.getColumnIndex(UAS))));
//                c1.setTugas(c.getDouble((c.getColumnIndex(TUGAS))));
//                c1.setQuiz(c.getDouble((c.getColumnIndex(KUIS))));
//                c1.setFp(c.getDouble((c.getColumnIndex(FINALPROJECT))));
//                c1.setPraktikum(c.getDouble((c.getColumnIndex(PRAKTIKUM))));
//                c1.setUtsPersent(c.getDouble((c.getColumnIndex(UTS_PERSENT))));
//                c1.setUasPersent(c.getDouble((c.getColumnIndex(UAS_PERSENT))));
//                c1.setTugasPersent(c.getDouble((c.getColumnIndex(TUGAS_PERSENT))));
//                c1.setQuizPersent(c.getDouble((c.getColumnIndex(KUIS_PERSENT))));
//                c1.setFpPersent(c.getDouble((c.getColumnIndex(FINALPROJECT_PERSENT))));
//                c1.setTarget(c.getString((c.getColumnIndex(TARGET))));
//                c1.setPraktikumPersent(c.getDouble((c.getColumnIndex(PRAKTIKUM_PERSENT))));
//
//                // adding to courses
//                courses.add(c1);
//
//            } while (c.moveToNext());
//        }
//
//        return courses;
//    }


}
