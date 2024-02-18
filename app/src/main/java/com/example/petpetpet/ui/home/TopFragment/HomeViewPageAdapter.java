package com.example.petpetpet.ui.home.TopFragment;


import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public final class HomeViewPageAdapter extends PagerAdapter {


    private List<View> mViews;
    private List<String> mTitles;

    public HomeViewPageAdapter(List<View> views) {
        this(views, null);
    }


    public HomeViewPageAdapter(List<View> views, List<String> titles) {
        this.mViews = views;
        this.mTitles = titles;
        if (mTitles == null) {
            mTitles = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mViews.get(position));
        return mViews.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViews.get(position));
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.size() > 0 ? mTitles.get(position) : "";
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view == obj;
    }
}