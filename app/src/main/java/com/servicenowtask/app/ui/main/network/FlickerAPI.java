package com.servicenowtask.app.ui.main.network;

import com.servicenowtask.app.ui.main.model.Flicker;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickerAPI {
    @GET("rest/?method=flickr.photos.search&api_key=632e28242010d58e91f3ed8ae9916238&format=json&safe_search=1")
    Call<Flicker> getFlickerData(@Query("text") String category,@Query("nojsoncallback") int pageno);

   /* https://api.flickr.com/services/rest/?method=flickr.photos.search
    // &api_key=632e28242010d58e91f3ed8ae9916238&format=json&nojsoncallback=1&safe_search=1&text=cat*/
}

