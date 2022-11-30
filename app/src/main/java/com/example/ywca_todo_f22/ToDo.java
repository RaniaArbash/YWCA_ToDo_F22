package com.example.ywca_todo_f22;

import android.os.Parcel;
import android.os.Parcelable;

public class ToDo implements Parcelable {

    String task;
    String task_date;
    Boolean isCompleted;

    public ToDo(String task, String task_date, Boolean isCompleted) {
        this.task = task;
        this.task_date = task_date;
        this.isCompleted = isCompleted;
    }

    protected ToDo(Parcel in) {
        task = in.readString();
        task_date = in.readString();
        byte tmpIsCompleted = in.readByte();
        isCompleted = tmpIsCompleted == 0 ? null : tmpIsCompleted == 1;
    }

    public static final Creator<ToDo> CREATOR = new Creator<ToDo>() {
        @Override
        public ToDo createFromParcel(Parcel in) {
            return new ToDo(in);
        }

        @Override
        public ToDo[] newArray(int size) {
            return new ToDo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(task);
        parcel.writeString(task_date);
        parcel.writeByte((byte) (isCompleted == null ? 0 : isCompleted ? 1 : 2));
    }

    public static ToDo fromStringToTodo(String todostring){
        //Assignment 3&13/12/2022
        ToDo newTodo= null;
        char[] todoCharArray = todostring.toCharArray();
        for (int i = 0 ;i<todoCharArray.length;i++){
            if (todoCharArray[i] == '&'){
                String task = todostring.substring(0,i);
                String date = todostring.substring(i+1, todoCharArray.length);
                newTodo = new ToDo(task,date, false);
                break;
            }
        }
        return newTodo;

    }
}
