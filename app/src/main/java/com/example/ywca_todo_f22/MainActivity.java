package com.example.ywca_todo_f22;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    void showTheAlert(ToDo td){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Did You Finish " + td.task +" ??");

        builder.setNegativeButton("No",null);

        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                td.isCompleted = true;
                adapter.list = ((MyApp)getApplication()).getList();
                adapter.notifyDataSetChanged();
            }
        });
        builder.create().show();
    }



}