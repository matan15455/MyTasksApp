package com.example.mytasksapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mytasksapp.adapter.TaskAdapter;
import com.example.mytasksapp.database.DatabaseHelper;
import com.example.mytasksapp.database.Task;
import com.example.mytasksapp.databinding.FragmentTasksBinding;
import androidx.navigation.Navigation;

import java.util.ArrayList;

public class TasksFragment extends Fragment {

    private FragmentTasksBinding binding;
    private ArrayList<Task> taskList;
    private TaskAdapter adapter;
    private DatabaseHelper databaseHelper;

    public TasksFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTasksBinding.inflate(inflater, container, false);

        databaseHelper = new DatabaseHelper(getContext());
        taskList = databaseHelper.getAllTasks();

        adapter = new TaskAdapter(taskList, databaseHelper);

        if (taskList.isEmpty()) {
            binding.tvEmpty.setVisibility(View.VISIBLE);
            binding.rvTasks.setVisibility(View.GONE);
        } else {
            binding.tvEmpty.setVisibility(View.GONE);
            binding.rvTasks.setVisibility(View.VISIBLE);
        }

        binding.rvTasks.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvTasks.setAdapter(adapter);

        binding.btnAddTask.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_tasksFragment_to_addTaskFragment)
        );

        binding.btnStatistics.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_tasksFragment_to_statsFragment)
        );

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        taskList.clear();
        taskList.addAll(databaseHelper.getAllTasks());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}