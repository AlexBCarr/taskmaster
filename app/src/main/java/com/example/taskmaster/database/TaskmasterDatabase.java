package com.example.taskmaster.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.taskmaster.dao.TaskDao;
import com.example.taskmaster.models.Task;


@Database(entities = {Task.class}, version = 1)
public abstract class TaskmasterDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}