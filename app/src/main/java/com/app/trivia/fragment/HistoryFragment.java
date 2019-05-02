package com.app.trivia.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.trivia.R;
import com.app.trivia.adpater.GameHistoryAdapter;
import com.app.trivia.helper.DBHelper;
import com.app.trivia.model.Summary;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    public static final String TAG = HistoryFragment.class.getSimpleName().toString();

    private FragmentActivity myContext;

    private RecyclerView myHistoryRC;

    private GameHistoryAdapter myAdapter;

    private DBHelper myDBHelper;

    private ArrayList<Summary> myHistoryList;

    private ImageView myBackIM;

    @Override

    public View onCreateView(LayoutInflater aInflater, ViewGroup aContainer, Bundle savedInstanceState) {
        View aView = null;

        try {
            myContext = getActivity();

            // Inflate the layout for this fragment
            aView = aInflater.inflate(R.layout.fragment_history,
                    aContainer, false);

            classInitialization(aView);

        } catch (Throwable e) {
            e.printStackTrace();
        }
        // return the view
        return aView;
    }

    private void classInitialization(View aView) {

        myDBHelper = new DBHelper(myContext);

        myHistoryList = new ArrayList<>();

        myHistoryRC = (RecyclerView) aView.findViewById(R.id.history_rc);

        myBackIM = (ImageView) aView.findViewById(R.id.back_im);

        toLoadHistory();

        myBackIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myContext.onBackPressed();
            }
        });
    }

    private void toLoadHistory() {

        myHistoryList = myDBHelper.getGameHistory();

        if (myHistoryList != null) {

            myAdapter = new GameHistoryAdapter(myContext, myHistoryList);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(myContext);

            myHistoryRC.setLayoutManager(mLayoutManager);

            myHistoryRC.setItemAnimator(new DefaultItemAnimator());

            myHistoryRC.setAdapter(myAdapter);
        }


    }
}