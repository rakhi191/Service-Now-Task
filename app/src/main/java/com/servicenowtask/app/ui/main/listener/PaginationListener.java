package com.servicenowtask.app.ui.main.listener;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public abstract class PaginationListener extends RecyclerView.OnScrollListener {

    public static final int PAGE_START = 1;
    @NonNull
    private GridLayoutManager layoutManager;

    private static final int PAGE_SIZE = 10;

    public PaginationListener(@NonNull GridLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= PAGE_SIZE) {
                loadMoreItems();
            }
    }

    protected abstract void loadMoreItems();
}
