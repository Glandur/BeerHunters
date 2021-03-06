package ca.etsmtl.beerhunters.util;

import android.support.v7.widget.LinearLayoutManager;

import ca.etsmtl.beerhunters.adapter.PagingAdapter;
import ca.etsmtl.beerhunters.model.BeerContainerResponse;
import rx.functions.Func1;

/**
 * Allows to filter {@link RecyclerViewScrollEvent} {@link rx.Observable} that would fire a page changing
 * Created by biche on 07/02/2016.
 */
public class ScrollFilter implements Func1<RecyclerViewScrollEvent, Boolean> {
    public static final int LOADING_THRESHOLD = 15;
    private final LinearLayoutManager mLayoutManager;
    private boolean mForceReload = false;

    public ScrollFilter(LinearLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    /**
     * Method that shunts the item loading logic and forces the filter to answer true
     */
    public void forceReload() {
        this.mForceReload = true;
    }

    @Override
    public Boolean call(RecyclerViewScrollEvent scrollEvent) {
        if(scrollEvent.view().getAdapter() instanceof PagingAdapter){
            PagingAdapter adapter = (PagingAdapter)scrollEvent.view().getAdapter();
            if (mForceReload){
                mForceReload =false;
                adapter.setLoading(true);
                return true;
            }
            boolean mustLoad = mustBeLoaded(scrollEvent.dx(),
                    scrollEvent.dy(),
                    scrollEvent.view().getAdapter().getItemCount(),
                    adapter.isLoading(),
                    adapter.getCurrentPage());
            if (mustLoad) adapter.setLoading(true);
            return mustLoad;
        }else{
            return false;
        }
    }

    public boolean mustBeLoaded(int dx, int dy, int itemCount, boolean loading, int currentPage){
        boolean mustBeLoaded = false;
        if (dx==0&&dy==0) {
            mustBeLoaded = true;
        }else{
            if (dy>0){
                int lastVisiblePosition = mLayoutManager.findLastVisibleItemPosition();
                if (Math.min(lastVisiblePosition + LOADING_THRESHOLD, itemCount) > BeerContainerResponse.RESULTS_COUNT_PER_PAGE*(currentPage)){
                    mustBeLoaded = true;
                }
            }
            if (dy<0){
                int firstVisiblePosition = mLayoutManager.findFirstVisibleItemPosition();
                if (Math.max(firstVisiblePosition - LOADING_THRESHOLD,0) < BeerContainerResponse.RESULTS_COUNT_PER_PAGE*(currentPage-1)){
                    mustBeLoaded = true;
                }
            }
        }
        return mustBeLoaded&&!loading;
    }
}
