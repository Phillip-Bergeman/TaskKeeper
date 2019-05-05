package www.pbergsproductions.taskkeeper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList;

    public TaskAdapter(List<Task> list) {
        this.taskList = list;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View viewItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_list_row, parent, false);
        return new TaskViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.name.setText(taskList.get(position).getName().toString());
        holder.date.setText(taskList.get(position).getDueDate().toString());
        holder.priority.setText(Integer.toString(taskList.get(position).getPriority()).toString());
        holder.desc.setText(taskList.get(position).getDescription().toString());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView date;
        public TextView priority;
        public TextView desc;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            priority = itemView.findViewById(R.id.priority);
            desc = itemView.findViewById(R.id.desc);
        }
    }

}
