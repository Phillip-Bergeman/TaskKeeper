package www.pbergsproductions.taskkeeper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewTaskActivity extends MainActivity implements AdapterView.OnItemSelectedListener {

    String name;
    String dueDate;
    int priority;
    String description;
    MyDBHandler myDBHandler = new MyDBHandler(this);


    protected void onCreate(Bundle savedInstanceStace) {
        super.onCreate(savedInstanceStace);
        setContentView(R.layout.activity_newtask);

        CalendarView calendarView = new CalendarView(this);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int y, int m, int d) {
                dueDate = d+"-"+m+"-"+y;
            }
        });

        final Spinner spinnerpriority = findViewById(R.id.spinnerpriority);
        spinnerpriority.setOnItemSelectedListener(this);

        List<String> options = new ArrayList<>();
        for(int i = 1; i < 11; i++){
            options.add("" + i);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, options);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerpriority.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        priority = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void submitTask(View view) {
        myDBHandler.addTask(new Task(name, dueDate, priority, description));
    }
}
