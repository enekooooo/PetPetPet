package com.example.petpetpet.ui.adopt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.petpetpet.databinding.FragmentAdoptBinding;

public class AdoptFragment extends Fragment {

    private FragmentAdoptBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AdoptViewModel adoptViewModel =
                new ViewModelProvider(this).get(AdoptViewModel.class);

        binding = FragmentAdoptBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAdopt;
        adoptViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}