package com.alexbc.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.alexbc.taskmaster.MainActivity;
import com.alexbc.taskmaster.R;

import com.alexbc.taskmaster.utils.TaskStatusUtility;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskStatus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class TaskDetailActivity extends AppCompatActivity {

    public final String TAG = "TaskDetailActivity";
    private MediaPlayer mp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        mp = new MediaPlayer();

        Intent callingIntent = getIntent();
        String taskNameString = null;
        String taskDescriptionString = null;
        String taskStatusString = null;
        TaskStatus status = null;
        setUpSpeakButton();

        if (callingIntent != null) {
            taskNameString = callingIntent.getStringExtra(MainActivity.TASK_NAME_EXTRAS_TAG);

            TextView taskNameTextView = findViewById(R.id.text_view_task_detail_activity_task_title);

            if (taskNameString != null) {
                taskNameTextView.setText(taskNameString);
            }

            taskStatusString = callingIntent.getStringExtra(MainActivity.TASK_STATUS_EXTRAS_TAG);
            status = TaskStatus.valueOf(taskStatusString);

            TextView taskStatusTextView = findViewById(R.id.taskStatusTextView);

            if (taskStatusString != null) {
                taskStatusTextView.setText(TaskStatusUtility.taskStatusToString(status));
            }

            taskDescriptionString = callingIntent.getStringExtra(MainActivity.TASK_DESCRIPTION_EXTRAS_TAG);

            TextView taskDetailsTextView = findViewById(R.id.taskDetailTextView);

            if (taskDescriptionString != null) {
                taskDetailsTextView.setText(taskDescriptionString);
            }

        }
    }
    public void setUpSpeakButton() {
        Button speakbutton = findViewById(R.id.taskDetailActivitySpeakButton);
        speakbutton.setOnClickListener(v -> {
            String taskName;
            taskName = ((TextView)findViewById(R.id.taskStatusTextView)).getText().toString();

            Amplify.Predictions.convertTextToSpeech(
                    taskName,
                    success -> playAudio(success.getAudioData(), taskName),
                    failure -> Log.e(TAG, "Audio conversion of product, " + taskName + ", failed", failure)
            );
        });
    }

    private void playAudio(InputStream data, String textToSpeak) {
        File mp3File = new File(getCacheDir(), "audio.mp3");

        try(OutputStream out = new FileOutputStream(mp3File)) {
            byte[] buffer = new byte[8 * 1024];
            int bytesRead;

            while((bytesRead = data.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            Log.i(TAG, "audio file finished reading");

            mp.reset();
            mp.setOnPreparedListener(MediaPlayer::start);
            mp.setDataSource(new FileInputStream(mp3File).getFD());
            mp.prepareAsync();

            Log.i(TAG, "Audio played");
            Log.i(TAG, "text to speak: " + textToSpeak);
        } catch(IOException error) {
            Log.e(TAG, "Error writing audio file");
        }
    }

}