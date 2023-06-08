package com.alexbc.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;

import com.alexbc.taskmaster.activities.AddTaskActivity;
import com.alexbc.taskmaster.adapter.TaskListRecyclerViewAdapter;
import com.example.taskmaster.R;
import com.alexbc.taskmaster.activities.AllTaskActivity;
import com.alexbc.taskmaster.activities.SettingsActivity;
import com.example.taskmaster.database.TaskmasterDatabase;
import com.alexbc.taskmaster.models.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TASK_NAME_EXTRAS_TAG = "taskName";
    public static final String TASK_STATUS_EXTRAS_TAG = "taskStatus";
    public static final String TASK_DESCRIPTION_EXTRAS_TAG = "taskDescription";
    public static final String DATABASE_NAME = "example-taskmaster";
    List<Task> tasks = new ArrayList<>();
    TaskListRecyclerViewAdapter taskListRecyclerViewAdapter;
    TaskmasterDatabase taskmasterDatabase;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupTasksFromDatabase();
        setupSettingsButton();
        setupRecyclerView();
        setupAddTaskButton();
        setupAllTasksButton();
    }

    @Override
    protected void onResume() {
        super.onResume();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String username = preferences.getString(SettingsActivity.USERNAME_TAG, "");

        if (!username.isEmpty()) {
            String myTasksTitleTextView = username + "'s Tasks";
            ((TextView) findViewById(R.id.my_tasks_title)).setText(myTasksTitleTextView);
        }

        setupTasksFromDatabase();
        taskListRecyclerViewAdapter.updateTasksData(tasks);
    }

    public void setupTasksFromDatabase() {
        taskmasterDatabase = Room.databaseBuilder(
                        getApplicationContext(),
                        TaskmasterDatabase.class,
                        DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
        tasks = taskmasterDatabase.taskDao().findAll();

    }

    public void setupSettingsButton() {
        ((FloatingActionButton) findViewById(R.id.settingsButton)).setOnClickListener(v -> {
            Intent goToSettingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(goToSettingsIntent);
        });
    }

    public void setupRecyclerView() {
        RecyclerView taskListRecyclerView = (RecyclerView) findViewById(R.id.mainActivityTaskListRecyclerView);

        RecyclerView.LayoutManager taskListLayoutManager = new LinearLayoutManager(this);
        taskListRecyclerView.setLayoutManager(taskListLayoutManager);

        taskListRecyclerViewAdapter = new TaskListRecyclerViewAdapter(tasks, this);
        taskListRecyclerView.setAdapter(taskListRecyclerViewAdapter);

    }

    public void setupAddTaskButton() {
        Button goToAddTaskFormButton = (Button) findViewById(R.id.button_main_activity_add_task);

        goToAddTaskFormButton.setOnClickListener(v -> {
            Intent goToAddTaskFormIntent = new Intent(MainActivity.this, AddTaskActivity.class);

            startActivity(goToAddTaskFormIntent);
        });
    }

    public void setupAllTasksButton() {
        Button goToAllTasksButton = (Button) findViewById(R.id.button_main_activity_all_tasks);

        goToAllTasksButton.setOnClickListener(v -> {
            Intent goToAllTasksIntent = new Intent(MainActivity.this, AllTaskActivity.class);

            startActivity(goToAllTasksIntent);
        });
    }
}