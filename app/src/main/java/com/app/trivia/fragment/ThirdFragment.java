package com.app.trivia.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.app.trivia.R;
import com.app.trivia.fragmentmanager.MyFragmentManager;
import com.app.trivia.helper.CommonValues;
import com.app.trivia.helper.Helper;

public class ThirdFragment extends Fragment implements CommonValues {

    public static final String TAG = ThirdFragment.class.getSimpleName().toString();

    private FragmentActivity myContext;

    private Button myNextBTN;

    private CheckBox myFirstCH, mySecondCH, myThirdCH, myFourthCH;

    private MyFragmentManager myManager;

    private String myUserNameSTR, myFirstSelectSTR;

    @Override

    public View onCreateView(LayoutInflater aInflater, ViewGroup aContainer, Bundle savedInstanceState) {
        View aView = null;

        try {
            myContext = getActivity();

            // Inflate the layout for this fragment
            aView = aInflater.inflate(R.layout.fragment_third,
                    aContainer, false);

            classInitialization(aView);

        } catch (Throwable e) {
            e.printStackTrace();
        }
        // return the view
        return aView;
    }

    private void classInitialization(View aView) {

        getBundleValue();

        myManager = new MyFragmentManager(myContext);

        myNextBTN = (Button) aView.findViewById(R.id.next_btn);

        myFirstCH = (CheckBox) aView.findViewById(R.id.option1);

        mySecondCH = (CheckBox) aView.findViewById(R.id.option2);

        myThirdCH = (CheckBox) aView.findViewById(R.id.option3);

        myFourthCH = (CheckBox) aView.findViewById(R.id.option4);

        toCheckBTN();

        clickListener();


    }

    private void toCheckBTN() {
        if (myFirstCH.isChecked() || mySecondCH.isChecked() || myThirdCH.isChecked() || myFourthCH.isChecked()
        ) {
            Helper.toEnableBTN(myContext, myNextBTN);
        } else {
            Helper.toDisableBTN(myContext, myNextBTN);

        }
    }

    private void clickListener() {

        myNextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer result = new StringBuffer();
                if (checkOption(myFirstCH)) {
                    result.append(myFirstCH.getText().toString()).append(",");
                }
                if (checkOption(mySecondCH)) {
                    result.append(mySecondCH.getText().toString()).append(",");
                }
                if (checkOption(myThirdCH)) {
                    result.append(myThirdCH.getText().toString()).append(",");
                }
                if (checkOption(myFourthCH)) {
                    result.append(myFourthCH.getText().toString()).append(",");
                }

                Bundle aBundle = new Bundle();

                aBundle.putString(KEY1, myUserNameSTR);

                aBundle.putString(KEY2, myFirstSelectSTR);

                aBundle.putString(KEY3, Helper.removeLastChar(result.toString()));

                myManager.updateContent(new SummaryFragment(), SummaryFragment.TAG, aBundle);
            }
        });

        myFirstCH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isPressed()) {
                    toCheckBTN();
                }
            }
        });
        mySecondCH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isPressed()) {
                    toCheckBTN();
                }
            }
        });
        myThirdCH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isPressed()) {
                    toCheckBTN();
                }
            }
        });
        myFourthCH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isPressed()) {
                    toCheckBTN();
                }
            }
        });
    }

    private boolean checkOption(CheckBox aCheck) {

        if (aCheck.isChecked()) {
            return true;

        } else {
            return false;

        }
    }

    private void getBundleValue() {

        Bundle aBundle = getArguments();

        if (aBundle != null) {

            myUserNameSTR = aBundle.getString(KEY1);

            myFirstSelectSTR = aBundle.getString(KEY2);

        }
    }


}