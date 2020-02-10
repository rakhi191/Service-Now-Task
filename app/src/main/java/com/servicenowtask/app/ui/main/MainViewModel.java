package com.servicenowtask.app.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.servicenowtask.app.ui.main.model.Photo;

import java.util.List;

public class MainViewModel extends ViewModel {
    private FlickerRepository repository = FlickerRepository.getInstance();

    private LiveData<List<Photo>> photoLiveData;

    public MainViewModel() {
        super();

    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }


    public LiveData<List<Photo>> getPhotoLiveData(String category, int pageNo) {
        return repository.getFlickerPhotos(category,pageNo);
    }
}
