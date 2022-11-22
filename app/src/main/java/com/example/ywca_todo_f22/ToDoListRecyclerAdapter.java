package com.example.ywca_todo_f22;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDoListRecyclerAdapter extends
        RecyclerView.Adapter<ToDoListRecyclerAdapter.ToDoListViewHolder> {

    ArrayList<ToDo> list;
    Context context;

    ToDoListRecyclerAdapter(ArrayList<ToDo> l, Context c){
        // step 1
        list = l;
        context = c;
    }

    @Override
    public ToDoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // step 3
      View v = LayoutInflater.from(context).inflate(R.layout.todo_list_recycler_row,parent, false);
      return new ToDoListViewHolder(v);
    }

    @Override
    public void onBindViewHolder( ToDoListViewHolder holder, int position) {
        // step 5
        if (list.get(position).isCompleted)
            holder.imageView.setImageResource(R.drawable.completed);
        else
            holder.imageView.setImageResource(R.drawable.incomplete);

        holder.taskText.setText(list.get(position).task);
        holder.dateText.setText(list.get(position).task_date);
    }

    @Override
    public int getItemCount() {
        // step 2
        return list.size();
    }

    class ToDoListViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView taskText;
        TextView dateText;
        public ToDoListViewHolder( View itemView) {
            // step 4
            super(itemView);
             imageView =  itemView.findViewById(R.id.rtodoImage);
             taskText = itemView.findViewById(R.id.rtodoTask);
             dateText = itemView.findViewById(R.id.rtodoDate);


        }
    }



}
