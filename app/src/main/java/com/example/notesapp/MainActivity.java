package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edt_search;
    private ImageView img_search;
    private FloatingActionButton btn_add;
    private ListView listView;

    private List<Notes> notesList;
    private NotesDatabaseHelper noteDB;
    private NotesAdapter notesAdapter;
    private NotesDao notesDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        // Khởi tạo dữ liệu
        notesList = new ArrayList<Notes>();
        notesDao = new NotesDao(MainActivity.this);
        notesList = notesDao.ReadNotes();
        notesAdapter = new NotesAdapter(getApplicationContext(), notesList);
        listView.setAdapter(notesAdapter);
        onResume();
        registerForContextMenu(listView);
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notesList = (List<Notes>) notesDao.SearchNotes(edt_search.getText().toString());
                notesAdapter = new NotesAdapter(getApplicationContext(), notesList);
                listView.setAdapter(notesAdapter);
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    private void anhXa() {
        edt_search = findViewById(R.id.edt_search);
        img_search = findViewById(R.id.img_search);
        btn_add = findViewById(R.id.btn_add);
        listView = findViewById(R.id.listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int vitri = info.position;
        Notes notes = notesList.get(vitri);
        switch (item.getItemId()) {
            case R.id.mnuSua: {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("noteDL", notes);
                startActivity(intent);
                break;
            }
            case R.id.mnuXoa: {
                notesDao.DeleteNotes(notes.getId());
                notesAdapter.notifyDataSetChanged();
                onResume();
                break;
            }
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        notesList.clear();
        notesList.addAll(notesDao.ReadNotes());
        notesAdapter.notifyDataSetChanged();
    }
}