package com.example.ywca_todo_f22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class AddToDoActivity
        extends AppCompatActivity
    implements DBManager.DataBaseListener{

    SwitchCompat switchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtodo);
        switchCompat = findViewById(R.id.isurgent);
        TextView todoTask = findViewById(R.id.add_task);
        DatePicker todoDate = findViewById(R.id.add_date);
        Button save = findViewById(R.id.add_save);
        Button cancel = findViewById(R.id.add_cancel);
        this.setTitle("Add New Task");
        // db is ready
        ((MyApp)getApplication()).dbManager.getDB(getApplicationContext());
        ((MyApp)getApplication()).dbManager.listener = this;

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!todoTask.getText().toString().isEmpty()){
                    // 12/11/2022
                    ToDo newTodo;
                    String taskDate = todoDate.getDayOfMonth() + "/"+ (todoDate.getMonth() + 1) +"/" + todoDate.getYear();
                    if (switchCompat.isChecked())
                        newTodo  = new ToDo(todoTask.getText().toString(), taskDate, false,1);
                    else
                        newTodo = new ToDo(todoTask.getText().toString(), taskDate, false,0);

                    // in main thread - no access to db
                    ((MyApp)getApplication()).dbManager.insertNewToDOAsync(newTodo);



                 //   Intent resultIntent = new Intent();
                   // resultIntent.putExtra("newtodo",newTodo);
                    //setResult(MyApp.SaveCode,resultIntent);
                    finish();// dismiss

                }else
                    Toast.makeText(AddToDoActivity.this,"All Fields are Requiered ", Toast.LENGTH_LONG).show();
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                setResult(MyApp.CancelCode,resultIntent);
                finish();// dismiss
            }
        });

    }


    @Override
    public void insertingToDoCompleted() {
        finish();
    }

    @Override
    public void gettingToDosCompleted(ToDo[] list) {

    }
}