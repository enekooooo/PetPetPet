package com.example.petpetpet.ui.rescue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.petpetpet.R;
import com.example.petpetpet.databinding.FragmentRescueBinding;
import com.example.petpetpet.ui.home.TopFragment.HomeViewPageAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class RescueFragment extends Fragment {

    private FragmentRescueBinding binding;

    //小模块
    ViewPager viewPager;
    TabLayout tabLayout;
    List<View> views;
    List<String> titles;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RescueViewModel rescueViewModel =
                new ViewModelProvider(this).get(RescueViewModel.class);

        binding = FragmentRescueBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.rescue_viewpager);
        tabLayout = view.findViewById(R.id.rescue_tab_layout);

        View viewOne = LayoutInflater.from(view.getContext()).inflate(R.layout.fragment_rescue_viewpager1, null);
        View viewTwo = LayoutInflater.from(view.getContext()).inflate(R.layout.fragment_rescue_viewpager2, null);
        View viewThree = LayoutInflater.from(view.getContext()).inflate(R.layout.fragment_rescue_viewpager3, null);
        View viewFour = LayoutInflater.from(view.getContext()).inflate(R.layout.fragment_rescue_viewpager4, null);

        views = new ArrayList<>();
        views.add(viewOne);
        views.add(viewTwo);
        views.add(viewThree);
        views.add(viewFour);
        titles = new ArrayList<>();
        titles.add("所有");
        titles.add("喂食");
        titles.add("TNR");
        titles.add("其他");

        RescueViewPageAdapter adapter = new RescueViewPageAdapter(views, titles);

        for (String title : titles) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}