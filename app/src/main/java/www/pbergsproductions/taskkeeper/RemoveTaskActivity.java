package www.pbergsproductions.taskkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class RemoveTaskActivity extends MainActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "RemoveTaskActivity";
    List<Task> myTasks = new ArrayList<>();
    //RecyclerView recyclerView;
    TaskAdapter myAdapter;
    public static final int TEXT_REQUEST = 1;
    public String name;
    public Task target;
    public int id;
    MyDBHandler myDBHandler;
    public static final String EXTRA_ID =
            "com.example.android.twoactivities.extra.ID";
    public static final String EXTRA_NAME =
            "com.example.android.twoactivities.extra.NAME";
    public static final String EXTRA_DATE=
            "com.example.android.twoactivities.extra.DATE";
    public static final String EXTRA_PRIORITY =
            "com.example.android.twoactivities.extra.PRIORITY";
    public static final String EXTRA_DESC =
            "com.example.android.twoactivities.extra.DESC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_removetask);

        MyDBHandler myDBHandler = new MyDBHandler(this);
        myTasks = myDBHandler.getAllTasks();

        List<Task> taskList = new ArrayList<>();
        for(Task t : myTasks) {
            Log.d(TAG, "onCreate: List: " + t.toString());
            taskList.add(t);
        }

        final Spinner removespinner = findViewById(R.id.removespinner);
        removespinner.setOnItemSelectedListener(this);

        ArrayAdapter<Task> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, taskList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        removespinner.setAdapter(dataAdapter);

        /*
        myTasks = myDBHandler.getAllTasks();
        myAdapter = new TaskAdapter(myTasks);
        /* */
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        target = (Task) adapterView.getItemAtPosition(i);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void removeTask(View view) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_ID, target.getId());
        replyIntent.putExtra(EXTRA_NAME, target.getName());
        replyIntent.putExtra(EXTRA_DATE, target.getDueDate());
        replyIntent.putExtra(EXTRA_PRIORITY, target.getPriority());
        replyIntent.putExtra(EXTRA_DESC, target.getDescription());

        setResult(RESULT_OK,replyIntent);
        finish();

    }
}
