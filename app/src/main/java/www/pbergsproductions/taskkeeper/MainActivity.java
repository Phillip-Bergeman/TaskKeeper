package www.pbergsproductions.taskkeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    List<Task> myTasks = new ArrayList<>();
    RecyclerView recyclerView;
    TaskAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDBHandler myDBHandler = new MyDBHandler(this);
        myTasks = myDBHandler.getAllTasks();
        while (!myTasks.isEmpty()) {
            myDBHandler.deleteTask(myTasks.get(0));
            myTasks = myDBHandler.getAllTasks();
        }
        Task testTask = new Task("Dishes", "Now", 1, "Carefully");
        myDBHandler.addTask(new Task("Laundry", "Soon", 1, "Wash clothes"));
        myDBHandler.addTask(new Task(testTask.getName(), testTask.getDueDate(), testTask.getPriority(), testTask.getDescription()));
        myTasks = myDBHandler.getAllTasks();
        recyclerView = findViewById(R.id.recycler_view);
        myAdapter = new TaskAdapter(myTasks);

        RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager(this.getApplicationContext());

        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //The below collapsed code was used for testing the database.  use with caution
        /*Task testTask = new Task("Dishes", "Now", 1, "Carefully");
        int count;
        myTasks = myDBHandler.getAllTasks();
        while (!myTasks.isEmpty()) {
            myDBHandler.deleteTask(myTasks.get(0));
            myTasks = myDBHandler.getAllTasks();
        }

        Log.d(TAG, "onCreate: Building table");

        myDBHandler.addTask(new Task("Laundry", "Soon", 1, "Wash clothes"));
        myDBHandler.addTask(new Task(testTask.getName(), testTask.getDueDate(), testTask.getPriority(), testTask.getDescription()));

        myTasks = myDBHandler.getAllTasks();

        for(Task t : myTasks) {
            Log.d(TAG, "onCreate: List: " + t.toString());
        }

        testTask = myTasks.get(1);
        testTask.setDescription("Even sooner");
        myDBHandler.updateTask(testTask);

        myTasks = myDBHandler.getAllTasks();

        for(Task t : myTasks) {
            Log.d(TAG, "onCreate: List: " + t.toString());
        }

        count = myDBHandler.getTaskCount();

        Log.d(TAG, "onCreate: Task Count: " + count);

        testTask = myTasks.get(1);
        myDBHandler.deleteTask(testTask);

        count = myDBHandler.getTaskCount();

        Log.d(TAG, "onCreate: Task Count: " + count);

        myTasks = myDBHandler.getAllTasks();

        for(Task t : myTasks) {
            Log.d(TAG, "onCreate: List: " + t.toString());
        }

        testTask = myTasks.get(0);

        myDBHandler.deleteTask(testTask);

        count = myDBHandler.getTaskCount();

        Log.d(TAG, "onCreate: Task Count: " + count);*/

    }

}
