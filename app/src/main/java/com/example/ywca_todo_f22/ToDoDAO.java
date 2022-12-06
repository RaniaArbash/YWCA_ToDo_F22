package com.example.ywca_todo_f22;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ToDoDAO {

    @Query("select * from ToDo")
    ToDo[] getAllToDo();

    @Insert
    void insertNewToDo(ToDo newToDo);

    @Query("select * from ToDo where isCompleted = 1")
    ToDo[] getAllCompletedToDos();

    @Query("select * from ToDo where isNormal = 1")
    ToDo[] getAllUrgentToDos();

    @Delete
    void deleteOneToDo(ToDo tobedeleted);

    @Update
    void updateToDo(ToDo todBeUpdated);

}

