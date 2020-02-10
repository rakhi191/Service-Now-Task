package com.servicenowtask.app.ui.main.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.servicenowtask.app.R;
import com.servicenowtask.app.ui.main.model.Photo;
import com.squareup.picasso.Picasso;


public class FlickerDetailsFragment extends Fragment {

    private TextView tv_details;
    private ImageView iv_photo;
    private Photo photo;

    public static FlickerDetailsFragment newInstance() {
        return new FlickerDetailsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            photo = getArguments().getParcelable("PHOTO_DETAILS");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.flicker_details_fragment, container, false);
        tv_details = view.findViewById(R.id.tv_details);
        iv_photo = view.findViewById(R.id.iv_photo);
        tv_details.setText(photo.title);
        String url = "https://farm"+photo.farm+".static.flickr.com/"+photo.server+"/"+photo.id+"_"+photo.secret+".jpg";
        Picasso.get().load(url).into(iv_photo);
        return view;
    }
}
