package com.a7apps.tvbase.ui.movies.tabs;

import android.os.Bundle;
import android.widget.ProgressBar;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.a7apps.tvbase.R;
import com.a7apps.tvbase.adapter.AdapRV;
import com.a7apps.tvbase.data.Data;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WatchlistMoviesFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WatchlistMoviesFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private AdapRV adapRV;
    private ProgressBar progress;
    private Data data;

    public WatchlistMoviesFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WatchlistFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static WatchlistMoviesFrag newInstance(String param1, String param2) {
        WatchlistMoviesFrag fragment = new WatchlistMoviesFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_watchlist_movies,container,false);
        recyclerView = view.findViewById(R.id.rvPopSeries);
        progress = view.findViewById(R.id.pbFragmentWatchlist);
        recyclerView.setHasFixedSize(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                data = new Data(getActivity().getApplicationContext());
                adapRV = new AdapRV(getActivity().getApplicationContext(), data.getDataPopSeries());
                adapRV.notifyDataSetChanged();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAdapter(adapRV);
                        progress.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();
       return view;
    }
}