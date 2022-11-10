package com.example.notesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class NotesDao {
    private NotesDatabaseHelper csdl;

    public NotesDao(Context context) {
        csdl = new NotesDatabaseHelper(context);
    };

    //Thêm Note
    public void AddNotes(Notes notes) {
        SQLiteDatabase sqLiteDatabase = csdl.getWritableDatabase();
        ContentValues cv = new ContentValues();
//        cv.put("NOTE_ID", notes.getId());
        cv.put("NOTE_NAME", notes.getName());
        cv.put("NOTE_DESCRIPTION", notes.getDescription());
        cv.put("NOTE_DATE", notes.getDate());
        sqLiteDatabase.insert("NOTE", null, cv);
        sqLiteDatabase.close();
    }

    public void UpdateNotes(Notes notes) {
        SQLiteDatabase sqLiteDatabase = csdl.getWritableDatabase();
        ContentValues cv = new ContentValues();
//        cv.put("NOTE_ID", notes.getId());
        cv.put("NOTE_NAME", notes.getName());
        cv.put("NOTE_DESCRIPTION", notes.getDescription());
        cv.put("NOTE_DATE", notes.getDate());
        sqLiteDatabase.update("NOTE", cv, "NOTE_ID=?", new String[]{String.valueOf(notes.getId())});
        sqLiteDatabase.close();
    }

    public int DeleteNotes(int id) {
        SQLiteDatabase sqLiteDatabase = csdl.getWritableDatabase();
        int del = sqLiteDatabase.delete("NOTE", "NOTE_ID=?", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return del;
    }
    public List<Notes> ReadNotes(){
        List<Notes> notesList = new ArrayList<Notes>();
        String sql = "SELECT * FROM NOTE";
        SQLiteDatabase sqLiteDatabase = csdl.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            String date = cursor.getString(3);
            Notes notes = new Notes(id, name, description, date);
            notesList.add(notes);
            cursor.moveToNext();
        }
        return notesList;
    }
    public List<Notes> SearchNotes(String s){
        List<Notes> notesList = new ArrayList<Notes>();
        String sql = "SELECT * FROM NOTE WHERE NOTE_ID LIKE '%"+s+"%' OR NOTE_NAME LIKE '%"+s+"%' OR NOTE_DESCRIPTION LIKE '%"+s+"%' OR NOTE_DATE LIKE '%"+s+"%'";
        SQLiteDatabase sqLiteDatabase = csdl.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            String date = cursor.getString(3);
            Notes notes = new Notes(id, name, description, date);
            notesList.add(notes);
            cursor.moveToNext();
        }
        return notesList;
    }
}
