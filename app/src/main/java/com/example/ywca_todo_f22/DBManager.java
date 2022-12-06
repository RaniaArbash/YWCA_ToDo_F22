package com.example.ywca_todo_f22;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

public class DBManager {

    interface DataBaseListener{
        void insertingToDoCompleted();
        void gettingToDosCompleted(ToDo[] list);
    }

    DataBaseListener listener;

    ToDoDB toDoDB;
    Handler dbHandler = new Handler(Looper.getMainLooper());

    ToDoDB getDB(Context context){
        if (toDoDB == null)
            toDoDB = Room.databaseBuilder(context,ToDoDB.class,"todo_db").build();

        return toDoDB;
    }

    void insertNewToDOAsync(ToDo t){

        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                toDoDB.getToDoDao().insertNewToDo(t);
                dbHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // notify main thread
                        listener.insertingToDoCompleted();
                    }
                });
            }
        });
    }

    void getAllToDo(){
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                ToDo[] list = toDoDB.getToDoDao().getAllToDo();
                dbHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // notify main thread
                        listener.gettingToDosCompleted(list);
                    }
                });
            }
        });
    }

    void updateToDo(ToDo toDo){
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                toDoDB.getToDoDao().updateToDo(toDo);
            }
        });
    }







}
