package com.example.mytasksapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mytasksapp.adapter.TaskAdapter;
import com.example.mytasksapp.database.Task;
import com.example.mytasksapp.databinding.FragmentTasksBinding;

import java.util.ArrayList;
import java.util.List;

public class TasksFragment extends Fragment {

    private FragmentTasksBinding binding;
    private List<Task> taskList;
    private TaskAdapter adapter;

    public TasksFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTasksBinding.inflate(inflater, container, false);

        taskList = new ArrayList<>();

        taskList.add(new Task("Buy milk", "Go to the supermarket", false));
        taskList.add(new Task("Finish homework", "Android project", true));
        taskList.add(new Task("Wash the car", "Weekend", false));

        adapter = new TaskAdapter(taskList);

        binding.rvTasks.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvTasks.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}