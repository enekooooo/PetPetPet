package com.example.petpetpet.ui.message;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.petpetpet.R;
import com.example.petpetpet.databinding.FragmentMessageBinding;

public class MessageFragment extends Fragment {

//    private MessageViewModel mViewModel;
    private FragmentMessageBinding binding;

//    public static MessageFragment newInstance() {
//        return new MessageFragment();
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        MessageViewModel messageViewModel =
                new ViewModelProvider(this).get(MessageViewModel.class);
        binding = FragmentMessageBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        final TextView textView = binding.textMessage;
        messageViewModel.getText().observe(getViewLifecycleOwner(),textView::setText);
//        return inflater.inflate(R.layout.fragment_message, container, false);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
//        // TODO: Use the ViewModel
//    }

}