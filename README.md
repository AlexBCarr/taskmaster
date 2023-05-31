# TaskMaster

TaskMaster is a task manager app for Android.

## Changelog

### Lab 31

- Added three Espresso tests to the current configuration.

### Lab 29

- Added a local SQLite database with Android Room.
    - New tasks are saved to the database.
    - On resume the main page pulls the most recent task data from the database.
- Add task activity is updated to include a spinner for task status.
    - A toast appears after adding a task to let the user know the task was saved.
    - Fields are cleared and focus it put on the title edit text for a new task to be input.

### Lab 28

Implements basic activities for adding a task, viewing all tasks, and the main app screen.
- Added a model for tasks with an enum for possible task status.
- Replaced static task buttons on the homepage with a RecyclerView list of task buttons.
    - Tasks are currently reading for a hard-coded list of tasks.
- Updated the task details activity to include task status and description for the specific task.

### Lab 27

- Re-worked the homepage to include a list of 3 static tasks.
- Added a task details activity.
    - Each task on the homepage links to a details activity view that grabs the name of the task from the button clicked on the main activity.
    - Each task's details page also includes the same bit of lorem ipsum for now.
- Added a settings activity.
    - The user can input a username and save this username via SharedPreferences.
    - The saved username is displayed on the main activity in the format "{username}'s tasks" and loads every time the main activity is resumed.
    - The value in the textedit field also reflects the updated change when the user returns.
    - If the user hasn't saved a username yet or changes the value to an empty string, the main activity displays "My Tasks".

### Lab 26

- Added a homepage that has a placeholder image and two buttons, one that links to the add a task activity and another that links to the all tasks activity.
- Added a task view that takes user input for a task name and description.
    - Submitting the task displays an animated "submitted" message as well as increments the task count.
    - These features are superficial at the moment and will reset each time the view is loaded.
- Added an all tasks view that will someday be home to a list of current tasks. Currently there is just a placeholder image and the title.

## Wireframes 
![Home Page](app/images/Home-Page-Wireframe.jpeg)

![Home Page](app/images/Home-Page-Wireframe-update.jpeg)

![Add Task](app/images/Add-Task-Wireframe.jpeg)

![All Task](app/images/All-Task-Wireframe.jpeg)

![Task Detail](app/images/Task-Detail-Wireframe.jpeg)

![Settings](app/images/Settings-Wireframe.jpeg)
