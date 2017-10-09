package com.example.newshub.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.newshub.R;
import com.example.newshub.manager.RssReader;

import java.util.Locale;


@SuppressWarnings("unused")
public class DFragment extends Fragment {

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    boolean isFirstRun = false;
    RssReader rssReader;

    public DFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static DFragment newInstance() {
        DFragment fragment = new DFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_nation, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        rssReader = new RssReader(getActivity(), getContext(),
                recyclerView, isFirstRun, 3);
        switch (Locale.getDefault().getDisplayLanguage()) {
            case "English":
                rssReader.setWebSite("http://www.un.org/apps/news/rss/rss_top.asp");
                break;
            case "ไทย":
                rssReader.setWebSite("http://www.nationtv.tv/main/rss/newsstand/content/mostview");
                break;
            case "Tiếng Việt":
                rssReader.setWebSite("http://www.baoyenbai.com.vn/rss/11.rss");
                break;
            default:
                rssReader.setWebSite("http://www.nationtv.tv/main/rss/newsstand/content/mostview");
        }
        rssReader.execute();
        isFirstRun = true;

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void reloadData() {
        RssReader reloadThree = new RssReader(getActivity(), getContext(),
                recyclerView, isFirstRun, 3);
        switch (Locale.getDefault().getDisplayLanguage()) {
            case "English":
                reloadThree.setWebSite("http://www.un.org/apps/news/rss/rss_top.asp");
                break;
            case "ไทย":
                reloadThree.setWebSite("http://www.nationtv.tv/main/rss/newsstand/content/mostview");
                break;
            case "Tiếng Việt":
                reloadThree.setWebSite("http://www.baoyenbai.com.vn/rss/11.rss");
                break;
            default:
                reloadThree.setWebSite("http://www.nationtv.tv/main/rss/newsstand/content/mostview");
        }
        reloadThree.execute();
        Toast.makeText(getContext(), R.string.noti_reload, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

}
