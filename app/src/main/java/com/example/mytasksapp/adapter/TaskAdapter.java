package com.example.mytasksapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytasksapp.R;
import com.example.mytasksapp.database.DatabaseHelper;
import com.example.mytasksapp.database.Task;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private final ArrayList<Task> taskList;
    private final DatabaseHelper databaseHelper;

    public TaskAdapter(ArrayList<Task> taskList, DatabaseHelper databaseHelper) {
        this.taskList = taskList;
        this.databaseHelper = databaseHelper;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);

        holder.tvTaskTitle.setText(task.getTitle());

        holder.tvTaskDescription.setText(task.getDescription());

        holder.cbCompleted.setOnCheckedChangeListener(null);
        holder.cbCompleted.setChecked(task.isCompleted());

        holder.cbCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setCompleted(isChecked);
            databaseHelper.updateTask(task);
        });

        holder.btnDeleteTask.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();

            if (currentPosition != RecyclerView.NO_POSITION) {
                Task taskToDelete = taskList.get(currentPosition);

                new AlertDialog.Builder(v.getContext())
                        .setTitle("Delete Task")
                        .setMessage("Are you sure you want to delete this task?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            databaseHelper.deleteTask(taskToDelete.getId());
                            taskList.remove(currentPosition);
                            notifyItemRemoved(currentPosition);
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {

        CheckBox cbCompleted;
        TextView tvTaskTitle;
        TextView tvTaskDescription;
        Button btnDeleteTask;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            cbCompleted = itemView.findViewById(R.id.cbCompleted);
            tvTaskTitle = itemView.findViewById(R.id.tvTaskTitle);
            btnDeleteTask = itemView.findViewById(R.id.btnDeleteTask);
            tvTaskDescription = itemView.findViewById(R.id.tvTaskDescription);
        }
    }
}