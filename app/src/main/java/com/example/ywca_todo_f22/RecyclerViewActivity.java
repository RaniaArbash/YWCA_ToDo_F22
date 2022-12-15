package com.example.ywca_todo_f22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity
    implements ToDoListRecyclerAdapter.itemCallBackInterface,
        FireStoreManager.FirestoreCallBackInterface

{
    ToDoListRecyclerAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        this.setTitle(getString(R.string.title));
        recyclerView =  findViewById(R.id.recyclerList);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager()
        ((MyApp)getApplication()).fireStorageManager.listener = this;

        adapter = new ToDoListRecyclerAdapter(
                ((MyApp)getApplication()).getList(),
                this);
        adapter.listener = this;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // show a toast when one row clicked
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RecyclerViewActivity.this,"Table clicked",Toast.LENGTH_SHORT).show();
            }
        });

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                Toast.makeText(RecyclerViewActivity.this, "on Swiped ", Toast.LENGTH_SHORT).show();
                //Remove swiped item from list and notify the RecyclerView
                int position = viewHolder.getAdapterPosition();

                //((MyApp)getApplication()).getList().remove(position);
                ((MyApp)getApplication()).fireStorageManager.deleteOneToDo(((MyApp)getApplication()).db,
                        ((MyApp)getApplication()).getList().remove(position)
                        );


            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }





    @Override
    public void onItemClicked(int pos) {
       ToDo td =  ((MyApp)getApplication()).getList().get(pos);
        Toast.makeText(this,td.task + "-" + td.task_date ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void newToDoInserted() {

    }

    @Override
    public void getAllToDosCompleted(ArrayList<ToDo> list) {
        ((MyApp)getApplication()).setList(list);
        adapter.list = list;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void todoUpdatedInFireStore() {

    }

    @Override
    public void todoDeletedInFireStore() {
        ((MyApp)getApplication()).fireStorageManager.getAllToDoFromFireStore( ((MyApp)getApplication()).db);

    }
}