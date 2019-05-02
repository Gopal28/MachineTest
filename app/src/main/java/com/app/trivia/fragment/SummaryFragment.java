package com.app.trivia.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.app.trivia.R;
import com.app.trivia.fragmentmanager.MyFragmentManager;
import com.app.trivia.helper.CommonValues;
import com.app.trivia.helper.DBHelper;
import com.app.trivia.helper.Helper;

public class SummaryFragment extends Fragment implements CommonValues {

    public static final String TAG = SummaryFragment.class.getSimpleName().toString();

    private FragmentActivity myContext;

    private TextView myFirstAnsTXT, mySecondAnsTXT, myThirdAnsTXT;

    private String myUserNameSTR, myFirstSelectSTR, mySecondSelectSTR;

    private Button myFinishBTN, myHistoryBTN;

    private DBHelper myDBHelper;

    private MyFragmentManager myManager;


    @Override

    public View onCreateView(LayoutInflater aInflater, ViewGroup aContainer, Bundle savedInstanceState) {
        View aView = null;

        try {
            myContext = getActivity();

            // Inflate the layout for this fragment
            aView = aInflater.inflate(R.layout.fragment_summary,
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

        myManager = new MyFragmentManager(myContext);

        myFirstAnsTXT = (TextView) aView.findViewById(R.id.first_ans_txt);

        mySecondAnsTXT = (TextView) aView.findViewById(R.id.second_ans_txt);

        myThirdAnsTXT = (TextView) aView.findViewById(R.id.third_ans_txt);

        myFinishBTN = (Button) aView.findViewById(R.id.finish_btn);

        myHistoryBTN = (Button) aView.findViewById(R.id.history_btn);

        getBundleValue();

        clickListener();

        if (myDBHelper != null) {
            if (myDBHelper.getGameHistory().size() > 0) {
                Helper.toEnableBTN(myContext, myHistoryBTN);
            } else {
                Helper.toDisableBTN(myContext, myHistoryBTN);

            }
        }
    }

    private void clickListener() {

        myFinishBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDBHelper.insertGame(myUserNameSTR, myFirstSelectSTR, mySecondSelectSTR);
                myManager.clearAllFragments();
                myManager.updateContent(new FirstFragment(), FirstFragment.TAG, null);
            }
        });

        myHistoryBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myManager.updateContent(new HistoryFragment(), HistoryFragment.TAG, null);

            }
        });
    }


    private void getBundleValue() {

        Bundle aBundle = getArguments();

        if (aBundle != null) {

            myUserNameSTR = aBundle.getString(KEY1);

            myFirstSelectSTR = aBundle.getString(KEY2);

            mySecondSelectSTR = aBundle.getString(KEY3);

            myFirstAnsTXT.setText(myContext.getResources().getString(R.string.lbl_hello) + " \" " + myUserNameSTR + " \" ");

            mySecondAnsTXT.setText(myFirstSelectSTR);

            myThirdAnsTXT.setText(mySecondSelectSTR);


        }
    }
}