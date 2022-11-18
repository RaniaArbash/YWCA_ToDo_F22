package com.example.ywca_todo_f22;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ToDoListBaseAdapter extends BaseAdapter {

    ArrayList<ToDo> list;
    Context context;

    public ToDoListBaseAdapter(ArrayList<ToDo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.todo_list_row,viewGroup,false);


        ImageView todoImage = v.findViewById(R.id.todoImage);
        TextView todoTask = v.findViewById(R.id.todoTask);
        TextView todoDate = v.findViewById(R.id.todoDate);

        if (list.get(i).isCompleted)
            todoImage.setImageResource(R.drawable.completed);
        else
            todoImage.setImageResource(R.drawable.incomplete);


        todoTask.setText(list.get(i).task);
        todoDate.setText(list.get(i).task_date);

        return v;
    }
}
