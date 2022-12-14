package com.example.ywca_todo_f22;

import android.app.Application;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApp extends Application {

    static int SaveCode = 1;
    static int CancelCode = 0;
    FirebaseFirestore db;
    FireStoreManager fireStorageManager = new FireStoreManager();

    FileStorageManager fileStorageManager = new FileStorageManager();
    private ArrayList<ToDo> listOfToDos;

    DBManager dbManager = new DBManager();
    static public ExecutorService executorService = Executors.newFixedThreadPool(4);

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
