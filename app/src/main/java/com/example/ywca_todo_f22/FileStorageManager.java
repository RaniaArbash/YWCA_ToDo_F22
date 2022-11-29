package com.example.ywca_todo_f22;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileStorageManager {

    // write a todo to a file
    // read all todos from a file
    // delete all todos from a file
    // delete one todo -

    static String fileName = "ToDo.txt";


    public void writeToDo(ToDo todo, Context context){
        FileOutputStream fos;// ObjectOutputStream
        ObjectOutputStream oos;
        try {
            fos = context.openFileOutput(fileName,Context.MODE_APPEND);
//            oos = new ObjectOutputStream(fos);
//            oos.writeObject(todo);

            fos.write((todo.task+"&"+todo.task_date+"$").getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<ToDo> readAllToDos(Context context){
        ArrayList<ToDo> allTodosFromTheFile = new ArrayList<>(0);
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            int read = 0;
            StringBuffer stringBuffer = new StringBuffer();

            while (( read = inputStreamReader.read() ) != -1){
                 stringBuffer.append((char)read);
            }
            Log.d("ToDoApp",stringBuffer.toString());
            // As you lab
            // convert file content into an array list of todos

            // ascii code of the saved char
            // the end of the file will read -1


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return allTodosFromTheFile;

    }


}
