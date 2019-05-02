package com.app.trivia.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.trivia.R;
import com.app.trivia.fragmentmanager.MyFragmentManager;
import com.app.trivia.helper.CommonValues;
import com.app.trivia.helper.Helper;

public class SecondFragment extends Fragment implements CommonValues {

    public static final String TAG = SecondFragment.class.getSimpleName().toString();

    private FragmentActivity myContext;

    private Button myNextBTN;

    private MyFragmentManager myManager;

    private RadioGroup myRadiGroup;

    private String myUserNameSTR, mySelectSTR;

    @Override

    public View onCreateView(LayoutInflater aInflater, ViewGroup aContainer, Bundle savedInstanceState) {
        View aView = null;

        try {
            myContext = getActivity();

            // Inflate the layout for this fragment
            aView = aInflater.inflate(R.layout.fragment_second,
                    aContainer, false);

            classInitialization(aView);

        } catch (Throwable e) {
            e.printStackTrace();
        }
        // return the view
        return aView;
    }

    private void classInitialization(View aView) {

        Helper.hideKeyboard(myContext);

        getBundleValue();

        myManager = new MyFragmentManager(myContext);

        myNextBTN = (Button) aView.findViewById(R.id.next_btn);

        myRadiGroup = (RadioGroup) aView.findViewById(R.id.radio_selection);

        Helper.toDisableBTN(myContext, myNextBTN);

        clickListener();


    }

    private void clickListener() {

        myRadiGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                Helper.toEnableBTN(myContext, myNextBTN);
                RadioButton aRadioBtn = (RadioButton) radioGroup.findViewById(radioButtonID);
                mySelectSTR = (String) aRadioBtn.getText();

            }
        });

        myNextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle aBundle = new Bundle();

                aBundle.putString(KEY1, myUserNameSTR);

                aBundle.putString(KEY2, mySelectSTR);

                myManager.updateContent(new ThirdFragment(), ThirdFragment.TAG, aBundle);
            }
        });
    }

    private void getBundleValue() {

        Bundle aBundle = getArguments();

        if (aBundle != null) {

            myUserNameSTR = aBundle.getString(KEY1);
        }
    }


}