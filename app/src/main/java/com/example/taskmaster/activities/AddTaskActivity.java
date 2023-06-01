package com.example.taskmaster.activities;





import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



import com.example.taskmaster.R;

import com.example.taskmaster.models.Task;
import com.example.taskmaster.models.TaskStatus;

public class AddTaskActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);



        Spinner taskStatusSpinner = findViewById(R.id.spinner_add_task_status);
        taskStatusSpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                TaskStatus.values()
        ));

        Button addTaskButton = (Button) findViewById(R.id.button_add_task_activity_add_task);

        addTaskButton.setOnClickListener(v -> {
            EditText titleEditText = findViewById(R.id.my_task_input);
            EditText descriptionEditText = findViewById(R.id.task_description_input);

            Task newTask = new Task(
                    titleEditText.getText().toString(),
                    descriptionEditText.getText().toString(),
                    TaskStatus.fromString(taskStatusSpinner.getSelectedItem().toString())
            );


            ((EditText)findViewById(R.id.my_task_input)).setText("");
            ((EditText)findViewById(R.id.task_description_input)).setText("");
            taskStatusSpinner.setSelection(0);

            titleEditText.requestFocus();

            Toast.makeText(AddTaskActivity.this, "Task saved!", Toast.LENGTH_SHORT).show();

            ((TextView) findViewById(R.id.submitted_text_view)).setText(R.string.submitted_confirmation);
        });
    }
}