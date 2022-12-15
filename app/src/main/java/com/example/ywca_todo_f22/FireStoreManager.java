package com.example.ywca_todo_f22;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FireStoreManager {

    interface FirestoreCallBackInterface {
        void newToDoInserted();
        void getAllToDosCompleted(ArrayList<ToDo> list);
        void todoUpdatedInFireStore();
        void todoDeletedInFireStore();
    }

    FirestoreCallBackInterface listener;

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
                        listener.newToDoInserted();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("firebase", "Error adding document", e);
                    }
                });
    }


    void getAllToDoFromFireStore(FirebaseFirestore db){

        db.collection("Tasks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<ToDo> listFromCDB = new ArrayList<>(0);
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("doc", document.getId() + " => " + document.get("description"));
                                listFromCDB.add(new ToDo(
                                        document.getId()
                                        ,(String)document.get("description"),
                                        (String)document.get("task_date"),
                                       (Boolean) document.get("isCompleted")));
                            }
                            listener.getAllToDosCompleted(listFromCDB);
                        } else {
                            Log.d("error", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    void updateToDoWithCompletion(FirebaseFirestore db, ToDo toupdate){
        DocumentReference docRef = db.collection("Tasks").document(toupdate.firestoreDocID);

// Set the "isCapital" field of the city 'DC'
        docRef
                .update("isCompleted", true)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("update", "DocumentSnapshot successfully updated!");
                        listener.todoUpdatedInFireStore();
                   }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("error", "Error updating document", e);
                    }
                });

    }

    void deleteOneToDo(FirebaseFirestore db, ToDo todelete){
        db.collection("Tasks").document(todelete.firestoreDocID)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("delete", "DocumentSnapshot successfully deleted!");
                        listener.todoDeletedInFireStore();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("delete", "Error deleting document", e);
                    }
                });

    }


    void getAllNotCompletedTasks(FirebaseFirestore db){
        db.collection("Tasks")
                .whereEqualTo("isCompleted", false)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Incompleted", document.getId() + " => " + document.get("description"));
                            }
                        } else {
                            Log.d("Error", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

}
