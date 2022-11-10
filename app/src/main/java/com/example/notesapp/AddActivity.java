package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.sql.Date;

public class AddActivity extends AppCompatActivity {
    private String id, name, description, date;
    private TextView tv_huy, tv_save;
    private EditText edt_title, edt_description, edt_date;
    private NotesDao notesDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        anhXa();
        notesDao = new NotesDao(AddActivity.this);

        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = edt_title.getText().toString();
                description = edt_description.getText().toString();
                date = edt_date.getText().toString();
                Notes notes = new Notes(name, description, date);
                notesDao.AddNotes(notes);
//                edt_title.setText("");
//                edt_description.setText("");
//                edt_date.setText("");
                Toast.makeText(AddActivity.this, "Added Successfully.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        tv_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void anhXa() {
        edt_title = findViewById(R.id.edt_title);
        edt_description = findViewById(R.id.edt_description);
        edt_date = findViewById(R.id.edt_date);
        tv_huy = findViewById(R.id.tv_huy);
        tv_save = findViewById(R.id.tv_save);
    }
}