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
            fos.write((todo.task+"&"+todo.task_date+"$").getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deleteAllToDos(Context context){
        FileOutputStream fos;// ObjectOutputStream
        ObjectOutputStream oos;
        try {
            fos = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            fos.write((" ").getBytes());

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
            allTodosFromTheFile = fromStringToTodoLists(stringBuffer.toString());
            // As you lab
            // convert file content into an array list of todos
            // substring


            // ascii code of the saved char
            // the end of the file will read -1


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return allTodosFromTheFile;

    }

    private ArrayList<ToDo> fromStringToTodoLists(String fileContent){
        //Assignment 3&13/12/2022$Walk The dog &29/11/2022$Task3&8/12/2022$
        //[Assignment 3, 13/12/2022]
        // [Walk The dog ,29/11/2022]
        //[Task3,8/12/2022]
        int firstIndex = 0;
        ArrayList<ToDo> list = new ArrayList<>(0);
        char[] charArray = fileContent.toCharArray();
        for (int i = 0 ;i< charArray.length;i++){
            if (charArray[i] == '$'){
                // substring (firstindex , i-1);
                String task = fileContent.substring(firstIndex,i);
                list.add(ToDo.fromStringToTodo(task));
                Log.d("todo app" , task);
                firstIndex = i + 1;
            }
        }
    return list;
    }




    //
}
