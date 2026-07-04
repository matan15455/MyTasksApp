package com.example.mytasksapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.mytasksapp.database.DatabaseHelper;
import com.example.mytasksapp.database.Task;
import com.example.mytasksapp.databinding.FragmentAddTaskBinding;

public class AddTaskFragment extends Fragment {

    private FragmentAddTaskBinding binding;
    private DatabaseHelper databaseHelper;

    public AddTaskFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAddTaskBinding.inflate(inflater, container, false);
        databaseHelper = new DatabaseHelper(getContext());

        binding.btnSaveTask.setOnClickListener(v -> {
            String title = binding.etTitle.getText().toString().trim();
            String description = binding.etDescription.getText().toString().trim();

            if (TextUtils.isEmpty(title)) {
                Toast.makeText(getContext(), "Please enter task title", Toast.LENGTH_SHORT).show();
                return;
            }

            Task task = new Task(title, description, false);
            databaseHelper.addTask(task);

            Toast.makeText(getContext(), "Task saved", Toast.LENGTH_SHORT).show();

            Navigation.findNavController(v).navigateUp();
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}