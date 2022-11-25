package com.example.ywca_todo_f22;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddNewToDoFragment extends DialogFragment {

    interface FragmentListener{
        void saveNewToDo(ToDo newtodo);
    }

    DatePicker taskDate;
    EditText taskText;
    Button saveBut;
    Button cancleBut;
    FragmentListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.addtodo_fragment, container, false);
        taskDate =  v.findViewById(R.id.fadd_date);
        taskText = v.findViewById(R.id.fadd_task);
        saveBut = v.findViewById(R.id.fadd_save);
        cancleBut = v.findViewById(R.id.fadd_cancel);

        saveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!taskText.getText().toString().isEmpty()) {
                    // 12/11/2022
                    String data = taskDate.getDayOfMonth() + "/" + (taskDate.getMonth() + 1) + "/" + taskDate.getYear();
                    ToDo newTodo = new ToDo(taskText.getText().toString(), data, false);
                    listener.saveNewToDo(newTodo);
                    dismiss();
                }
            }
        });

        cancleBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return v;
    }
}
