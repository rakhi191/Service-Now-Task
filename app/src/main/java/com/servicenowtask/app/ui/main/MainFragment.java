package com.servicenowtask.app.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.servicenowtask.app.R;
import com.servicenowtask.app.ui.main.details.FlickerDetailsFragment;
import com.servicenowtask.app.ui.main.listener.OnItemClickListener;
import com.servicenowtask.app.ui.main.listener.PaginationListener;
import com.servicenowtask.app.ui.main.model.Photo;
import com.servicenowtask.app.ui.main.network.FlickerAPI;
import com.servicenowtask.app.ui.main.network.RestApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MainFragment extends Fragment implements OnItemClickListener {

    private MainViewModel mViewModel;
    private RecyclerView recyclerView;
    private EditText ev_Category;
    private FlickerAdapter flickerAdapter;
    private ArrayList<Photo> flickerArrayList = new ArrayList<>();
    private  GridLayoutManager gridLayoutManager;
    private int pageNumber = 1;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.main_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        ev_Category =  view.findViewById(R.id.ev_search);
        setUpRecyclerView();
        setUpEditText();
        return view;
    }

    private void setUpEditText() {
        ev_Category.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                flickerArrayList.clear();
                pageNumber = 1;
                getFlickerData(pageNumber);
                recyclerView.scrollToPosition(0);
                return true;
            }
            return false;
        });
    }

    private void setUpRecyclerView() {
        if (flickerAdapter == null) {
            flickerAdapter = new FlickerAdapter(getContext(), flickerArrayList,this);
            gridLayoutManager = new GridLayoutManager(getContext(),3);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(flickerAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            flickerAdapter.notifyDataSetChanged();
        }

        /**
         * add scroll listener while user reach in bottom load more will call
         */
        recyclerView.addOnScrollListener(new PaginationListener(gridLayoutManager) {
            @Override
            protected void loadMoreItems() {
                getFlickerData(++pageNumber);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        getFlickerData(pageNumber);
    }

    private void getFlickerData(int pageNumber){
        mViewModel.getPhotoLiveData(ev_Category.getText().toString(),pageNumber).observe(this, new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> photos) {
                flickerArrayList.addAll(photos);
                flickerAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClick(Photo photo) {
        Fragment fragment = FlickerDetailsFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putParcelable("PHOTO_DETAILS",photo);
        fragment.setArguments(bundle);
        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment)
                .addToBackStack("frag2")
                .commit();
    }
}
