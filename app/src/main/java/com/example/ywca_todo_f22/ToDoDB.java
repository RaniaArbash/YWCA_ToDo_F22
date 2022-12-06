package com.example.ywca_todo_f22;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ToDo.class}, version = 1)
public abstract class ToDoDB extends RoomDatabase {
    public abstract ToDoDAO getToDoDao();
}

