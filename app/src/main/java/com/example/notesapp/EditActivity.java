package com.example.notesapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    private String id, name, description, date;
    private TextView tv_huy, tv_save;
    private EditText edt_title, edt_description, edt_date;
    private NotesDao notesDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        anhXa();
        notesDao = new NotesDao(EditActivity.this);
        Intent intent = getIntent();
        final Notes notes = (Notes) intent.getSerializableExtra("noteDL");
        // Lấy dữ liệu
        edt_title.setText(notes.getName());
        edt_description.setText(notes.getDescription());
        edt_date.setText(notes.getDate());
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                builder.setTitle("Lưu thay đổi ?");
                builder.setMessage("Bạn có muốn lưu thay dổi không ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        notes.setName(edt_title.getText().toString());
                        notes.setDescription(edt_description.getText().toString());
                        notes.setDate(edt_date.getText().toString());
                        notesDao.UpdateNotes(notes);
                        Toast.makeText(EditActivity.this, "Edited Successfully.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();

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