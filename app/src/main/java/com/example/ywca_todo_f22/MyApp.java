package com.example.ywca_todo_f22;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {

    static int SaveCode = 1;
    static int CancelCode = 0;

    FileStorageManager fileStorageManager = new FileStorageManager();
    private ArrayList<ToDo> listOfToDos;


    public ArrayList<ToDo> getList(){
        if (listOfToDos == null) {
            listOfToDos = new ArrayList<>(0);
        }
        return listOfToDos;
    }
    public void setList(ArrayList<ToDo> list){
        listOfToDos = list;
    }

    public void addNewToDO(ToDo toadd){
        listOfToDos.add(toadd);
    }

}
