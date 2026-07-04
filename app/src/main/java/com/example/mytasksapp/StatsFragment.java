package com.example.mytasksapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.mytasksapp.database.DatabaseHelper;
import com.example.mytasksapp.databinding.FragmentStatsBinding;

public class StatsFragment extends Fragment {

    private FragmentStatsBinding binding;

    public StatsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentStatsBinding.inflate(inflater, container, false);

        DatabaseHelper db = new DatabaseHelper(getContext());

        int total = db.getTotalTasksCount();
        int completed = db.getCompletedTasksCount();
        int remaining = total - completed;

        binding.tvTotal.setText("Total tasks: " + total);
        binding.tvCompleted.setText("Completed: " + completed);
        binding.tvRemaining.setText("Remaining: " + remaining);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}