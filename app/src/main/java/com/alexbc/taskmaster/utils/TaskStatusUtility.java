package com.alexbc.taskmaster.utils;


import static com.alexbc.taskmaster.models.TaskStatus.ASSIGNED;
import static com.alexbc.taskmaster.models.TaskStatus.COMPLETE;
import static com.alexbc.taskmaster.models.TaskStatus.IN_PROGRESS;

import com.alexbc.taskmaster.models.TaskStatus;


import java.util.ArrayList;

public class TaskStatusUtility {
    public static TaskStatus taskStatusFromString(String taskString) {
        switch (taskString) {
            case "Complete":
                return TaskStatus.COMPLETE;
            case "Assigned":
                return TaskStatus.ASSIGNED;
            case "In Progress":
                return TaskStatus.IN_PROGRESS;
            default:
                return TaskStatus.NEW;
        }
    }

    public static String taskStatusToString(TaskStatus task) {
        switch (task) {
            case COMPLETE:
                return "Complete";
            case ASSIGNED:
                return "Assigned";
            case IN_PROGRESS:
                return "In Progress";
            default:
                return "New";
        }
    }

    public static ArrayList<String> getTaskStatusList() {
        ArrayList<String> list = new ArrayList<>();
        for (TaskStatus task : TaskStatus.values()) {
            list.add(taskStatusToString(task));
        }

        return list;
    }
}