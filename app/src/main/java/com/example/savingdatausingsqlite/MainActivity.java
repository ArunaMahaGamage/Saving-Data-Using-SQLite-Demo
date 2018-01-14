package com.example.savingdatausingsqlite;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.savingdatausingsqlite.sqlite.CRUD;
import com.example.savingdatausingsqlite.sqlite.FeedReaderDbHelper;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    public EditText et_insert_title, et_insert_subtitle;
    public EditText et_update_id, et_update_title, et_update_subtitle;
    public EditText et_delete_id;

    FeedReaderDbHelper mDbHelper;
    List itemIds;

    TextView tv_view;

    RecyclerView recyclerView;

    CRUD crud;
    Context context;
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        crud = new CRUD();

        uiInit();

        mDbHelper = new FeedReaderDbHelper(getApplicationContext());



    }
    public void uiInit() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_view);

        et_insert_title = (EditText) findViewById(R.id.et_insert_title);
        et_insert_subtitle = (EditText) findViewById(R.id.et_insert_subtitle);

        et_update_id = (EditText) findViewById(R.id.et_update_id);
        et_update_title = (EditText) findViewById(R.id.et_update_title);
        et_update_subtitle = (EditText) findViewById(R.id.et_update_subtitle);

        et_delete_id = (EditText) findViewById(R.id.et_delete_id);
    }

    public void insertData(View view) {
        String title = et_insert_title.getText().toString();
        String subTitle = et_insert_subtitle.getText().toString();
        crud.insert(context,title,subTitle);
    }

    public void readData(View view) {
        crud.read(context);

        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(crud.adapter);
    }

    public void update(View view) {
        String id = et_update_id.getText().toString();
        String title = et_update_title.getText().toString();
        String subtitle = et_update_subtitle.getText().toString();

        crud.update(context,id,title,subtitle);
    }

    public void delete(View view) {
        String id = et_delete_id.getText().toString();
        crud.delete(context, id);
    }

    @Override
    protected void onDestroy() {
        crud.closeConnection(context);
        super.onDestroy();
    }
}
