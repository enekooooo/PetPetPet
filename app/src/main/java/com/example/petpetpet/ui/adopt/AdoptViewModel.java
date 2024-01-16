package com.example.petpetpet.ui.adopt;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdoptViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AdoptViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is adopttttttttttt fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}