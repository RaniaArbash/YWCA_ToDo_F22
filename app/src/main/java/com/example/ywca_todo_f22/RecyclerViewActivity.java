package com.example.ywca_todo_f22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        this.setTitle("ToDo App Recycler List");

        recyclerView =  findViewById(R.id.recyclerList);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager()

        ToDoListRecyclerAdapter adapter = new ToDoListRecyclerAdapter(
                ((MyApp)getApplication()).getList(),
                this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}