package com.example.notesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class NotesAdapter extends BaseAdapter {
    private Context context;
    private List<Notes> NotesList;

    public NotesAdapter(Context context, List<Notes> notesList) {
        this.context = context;
        this.NotesList = notesList;
    }

    @Override
    public int getCount() {
        return NotesList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_view, null);
            viewHolder.tv_id = view.findViewById(R.id.tv_id);
            viewHolder.tv_name = view.findViewById(R.id.tv_name);
            viewHolder.tv_description = view.findViewById(R.id.tv_description);
            viewHolder.tv_date = view.findViewById(R.id.tv_date);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //Thiết lập giá trị
        Notes notes =NotesList.get(i);
        viewHolder.tv_id.setText("ID: "+notes.getId());
        viewHolder.tv_name.setText("Name: "+notes.getName());
        viewHolder.tv_description.setText("Description: "+notes.getDescription());
        viewHolder.tv_date.setText(notes.getDate());
        return view;
    }
    class ViewHolder{
        TextView tv_id, tv_name, tv_description, tv_date;
    }
}
