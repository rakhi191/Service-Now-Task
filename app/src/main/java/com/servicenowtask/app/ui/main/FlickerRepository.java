package com.servicenowtask.app.ui.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.servicenowtask.app.ui.main.model.Flicker;
import com.servicenowtask.app.ui.main.model.Photo;
import com.servicenowtask.app.ui.main.network.FlickerAPI;
import com.servicenowtask.app.ui.main.network.RestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


class FlickerRepository {
    private static final FlickerRepository ourInstance = new FlickerRepository();
    private FlickerAPI flickerAPI;
    private MutableLiveData<List<Photo>> photoLiveData = new MutableLiveData<>();

    static FlickerRepository getInstance() {
        return ourInstance;
    }
    private FlickerRepository(){
        flickerAPI = RestApi.cteateService(FlickerAPI.class);
    }

    LiveData<List<Photo>> getFlickerPhotos(String category, int pageNo) {

        flickerAPI.getFlickerData(category,pageNo).enqueue(new Callback<Flicker>() {
            @Override
            public void onResponse(Call<Flicker> call,
                                   Response<Flicker> response) {
                if (response.body() != null) {
                    photoLiveData.setValue(response.body().photos.photo);
                }
            }

            @Override
            public void onFailure(Call<Flicker> call, Throwable t) {
                Log.d("failed resp",""+t.getMessage());
            }
        });
        return photoLiveData;
    }
}
