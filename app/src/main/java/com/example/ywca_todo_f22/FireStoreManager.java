package com.example.ywca_todo_f22;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FireStoreManager {


    public void insertNewToDoToFireStore( FirebaseFirestore db, ToDo newTodo){

        Map<String, Object> todo = new HashMap<>();
        todo.put("description", newTodo.task);
        todo.put("task_date", newTodo.task_date);
        todo.put("isCompleted", newTodo.isCompleted);


        db.collection("Tasks")
                .add(todo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("firebase", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("firebase", "Error adding document", e);
                    }
                });
    }


    void getAllToDoFromFireStore(){}

    void updateToDoWithCompletion(ToDo toupdate){}

    void deleteOneToDo(ToDo todelete){}
}
