package com.example.notesapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NotesDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "NOTESDB.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "NOTE";
    private static final String COLUMN_ID = "NOTE_ID";
    private static final String COLUMN_NAME = "NOTE_NAME";
    private static final String COLUMN_DESCRIPTION = "NOTE_DESCRIPTION";
    private static final String COLUMN_DATE = "NOTE_DATE";
    private Context context;

    public NotesDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT , " +
                COLUMN_DESCRIPTION + " TEXT , " +
                COLUMN_DATE + " TEXT );";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
