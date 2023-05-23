package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.taskmaster.activities.AddTaskActivity;
import com.example.taskmaster.activities.AllTaskActivity;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Steps for adding functionality to JS UI elements
        // 1: Get UI element by ID
        // 2: Add event listener to that element
        // 3: Attach a callback function with an onClick() method
        // 4: Do stuff in the callback (onClick())

        // Adding functionality to a UI element in Java (Android)
        // Step 1: grab element using findByID()
        // grabbing a view, assigning to a variable, and setting the value in 2 lines
        Button submitButton = (Button) findViewById(R.id.activityAddTaskButton);
        submitButton.setText("button"); // <-- "button" cannont be translated
        // grabbing a view and setting its values in one line (using string resoources)
        ((Button) findViewById(R.id.activityAddTaskButton)).setText(R.string.activity_add_task_button);

        // Step 2/3: add onClickListener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Step 4: Do stuff in the callback (onClick())
                System.out.println("Submit button pressed!");

                // A better way to log
                Log.v(TAG, "I'm a VERBOSE log");
                Log.d(TAG, "I'm a DEBUG log");
                Log.i(TAG, "I'm an INFO log");
                Log.w(TAG, "I'm  a WARNING log");
                Log.e(TAG, "I'm an ERROR log");
                Log.wtf(TAG, "WHAT A TERRIBLE FAILURE");

                // Logging an exception
                // Log.e(TAG, "insert your exception message here", new RuntimeException());
                try {
                    // try some stuff
                } catch (RuntimeException re) {
                    Log.e(TAG, "we caught a runtime exception", re);
                }
                
                ((TextView) findViewById(R.id.addTaskActivitySubmittedTextView)).setText(R.string.submitted_confirmation);


            }
        });


        // Let's create and trigger an Intent!
        // Grab the button that will trigger the Intent
        Button goToAddTask = (Button) findViewById(R.id.mainActivityAddTaskButton);
        // Add onClickListener
        goToAddTask.setOnClickListener(v -> { // <-- shorthand for new View.OnClickListner
            // Create an intent... Tell Intent where you're coming from and where you're going
            Intent goToAddTaskIntent = new Intent(MainActivity.this, AddTaskActivity.class);
            // Start Intent!
            startActivity(goToAddTaskIntent);
            // May also be written as MainActivity.this.startActivity(goToOrderFormIntent)
            // The "this" is only needed if starting the Intent from a different activity
        });

        // Let's create and trigger an Intent!
        // Grab the button that will trigger the Intent
        Button goToAllTask = (Button) findViewById(R.id.mainActivityAllTaskButton);
        // Add onClickListener
        goToAllTask.setOnClickListener(v -> { // <-- shorthand for new View.OnClickListner
            // Create an intent... Tell Intent where you're coming from and where you're going
            Intent goToAllTaskIntent = new Intent(MainActivity.this, AllTaskActivity.class);
            // Start Intent!
            startActivity(goToAllTaskIntent);
            // May also be written as MainActivity.this.startActivity(goToOrderFormIntent)
            // The "this" is only needed if starting the Intent from a different activity
        });



    }
}
