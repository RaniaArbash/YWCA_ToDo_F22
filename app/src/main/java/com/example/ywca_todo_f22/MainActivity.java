package com.example.ywca_todo_f22;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addToDo;
    ActivityResultLauncher<Intent> secondActivityLauncher;
    ToDoListBaseAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView todoList = findViewById(R.id.todoList);
        this.setTitle("ToDo App");
        addToDo = findViewById(R.id.addButton);
         adapter = new ToDoListBaseAdapter(
                ((MyApp)getApplication()).getList(),
                this);
        todoList.setAdapter(adapter);


        addToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent toAddToDo = new Intent(MainActivity.this,AddToDoActivity.class);
                secondActivityLauncher.launch(toAddToDo);
            }
        });
        secondActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == MyApp.SaveCode){
                            Intent resultIntent = result.getData();
                            ToDo newToDo =  resultIntent.getExtras().getParcelable("newtodo");
                            ((MyApp)getApplication()).addNewToDO(newToDo);
                            adapter.list = ((MyApp)getApplication()).getList();
                            adapter.notifyDataSetChanged();

                        }
                    }
                }

        );



        todoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showTheAlert(((MyApp)getApplication()).getList().get(i));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            super.onCreateOptionsMenu(menu);
            MenuInflater mi = new MenuInflater(MainActivity.this);
            mi.inflate(R.menu.todo_menu,menu);
         return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.toRecyclerView:
                Intent recyclerViewIntent = new Intent(MainActivity.this,RecyclerViewActivity.class);
                startActivity(recyclerViewIntent);

                break;


        }

        return true;
    }

    void showTheAlert(ToDo td){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.alert_msg);

        builder.setNegativeButton(R.string.alert_no,null);

        builder.setPositiveButton(R.string.alert_yes,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                td.isCompleted = true;
                adapter.list = ((MyApp)getApplication()).getList();
                adapter.notifyDataSetChanged();
            }
        });
        builder.create().show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();

    }
}