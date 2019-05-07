package www.pbergsproductions.taskkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    List<Task> myTasks = new ArrayList<>();
    RecyclerView recyclerView;
    TaskAdapter myAdapter;
    public static final int ADD_REQUEST = 1;
    public static final int DELETE_REQUEST = 2;
    public String name;
    public String date;
    public int priority;
    public String desc;
    public int id;
    public Task toDelete;
    MyDBHandler myDBHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDBHandler myDBHandler = new MyDBHandler(this);
        myTasks = myDBHandler.getAllTasks();

        for(Task t : myTasks) {
            Log.d(TAG, "onCreate: List: " + t.toString());
        }
        myTasks = myDBHandler.getAllTasks();
        recyclerView = findViewById(R.id.recycler_view);
        myAdapter = new TaskAdapter(myTasks);

        RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager(this.getApplicationContext());

        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    public void newTask(View view) {
        Intent intent = new Intent(this, NewTaskActivity.class);
        startActivityForResult(intent, ADD_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_REQUEST) {
            if (resultCode == RESULT_OK) {
                name = data.getStringExtra(NewTaskActivity.EXTRA_NAME);
                date = data.getStringExtra(NewTaskActivity.EXTRA_DATE);
                priority = data.getIntExtra(NewTaskActivity.EXTRA_PRIORITY, 1);
                desc = data.getStringExtra(NewTaskActivity.EXTRA_DESC);
                MyDBHandler myDBHandler = new MyDBHandler(this);
                myDBHandler.addTask(new Task(name, date, priority, desc));
            }
        }
        if (requestCode == DELETE_REQUEST) {
            if (resultCode == RESULT_OK) {
                name = data.getStringExtra(RemoveTaskActivity.EXTRA_NAME);
                date = data.getStringExtra(RemoveTaskActivity.EXTRA_DATE);
                priority = data.getIntExtra(RemoveTaskActivity.EXTRA_PRIORITY, 1);
                desc = data.getStringExtra(RemoveTaskActivity.EXTRA_DESC);
                id = data.getIntExtra(RemoveTaskActivity.EXTRA_ID, 1);
                MyDBHandler myDBHandler = new MyDBHandler(this);
                myDBHandler.deleteTask(new Task(id, name, date, priority, desc));
            }
        }

        MyDBHandler myDBHandler = new MyDBHandler(this);
        myTasks = myDBHandler.getAllTasks();
        recyclerView = findViewById(R.id.recycler_view);
        myAdapter = new TaskAdapter(myTasks);

        RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager(this.getApplicationContext());

        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void removeTask(View view) {
        Intent intent = new Intent(this, RemoveTaskActivity.class);
        startActivityForResult(intent, DELETE_REQUEST);
    }
}
