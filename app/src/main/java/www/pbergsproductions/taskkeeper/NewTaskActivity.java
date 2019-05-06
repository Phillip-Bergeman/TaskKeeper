package www.pbergsproductions.taskkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewTaskActivity extends MainActivity implements AdapterView.OnItemSelectedListener {

    private EditText mName;
    private EditText mDesc;
    private CalendarView mCalendarView;
    String name;
    String dueDate;
    int priority;
    String description;
    public static final String EXTRA_NAME =
            "com.example.android.twoactivities.extra.NAME";
    public static final String EXTRA_DATE=
            "com.example.android.twoactivities.extra.DATE";
    public static final String EXTRA_PRIORITY =
            "com.example.android.twoactivities.extra.PRIORITY";
    public static final String EXTRA_DESC =
            "com.example.android.twoactivities.extra.DESC";
    MyDBHandler myDBHandler = new MyDBHandler(this);


    protected void onCreate(Bundle savedInstanceStace) {
        super.onCreate(savedInstanceStace);
        setContentView(R.layout.activity_newtask);

        Intent intent = getIntent();

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int y, int m, int d) {
                dueDate = d+"-"+m+"-"+y;
            }
        });

        final Spinner spinnerPriority = findViewById(R.id.spinnerPriority);
        spinnerPriority.setOnItemSelectedListener(this);

        List<String> options = new ArrayList<>();
        for(int i = 1; i < 11; i++){
            options.add("" + i);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, options);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerPriority.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        priority = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void submitTask(View view) {
        mName = findViewById(R.id.name);
        mDesc = findViewById(R.id.description);
        name = mName.getText().toString();
        description = mDesc.getText().toString();

        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_NAME, name);
        replyIntent.putExtra(EXTRA_DATE, dueDate);
        replyIntent.putExtra(EXTRA_PRIORITY, priority);
        replyIntent.putExtra(EXTRA_DESC, description);

        setResult(RESULT_OK,replyIntent);
        finish();
    }
}
