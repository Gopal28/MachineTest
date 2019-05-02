package com.app.trivia.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.trivia.R;
import com.app.trivia.fragmentmanager.MyFragmentManager;
import com.app.trivia.helper.CommonValues;
import com.app.trivia.helper.Helper;

public class FirstFragment extends Fragment implements CommonValues {

    public static final String TAG = FirstFragment.class.getSimpleName().toString();

    private FragmentActivity myContext;

    private Button myNextBTN;

    private EditText myUserNameEDT;

    private MyFragmentManager myManager;

    @Override

    public View onCreateView(LayoutInflater aInflater, ViewGroup aContainer, Bundle savedInstanceState) {
        View aView = null;

        try {
            myContext = getActivity();

            aView = aInflater.inflate(R.layout.fragment_first,
                    aContainer, false);
            classInitialization(aView);

        } catch (Throwable e) {
            e.printStackTrace();
        }
        // return the view
        return aView;
    }

    private void classInitialization(View aView) {
        myManager = new MyFragmentManager(myContext);
        myNextBTN = (Button) aView.findViewById(R.id.next_btn);
        myUserNameEDT = (EditText) aView.findViewById(R.id.username_edt);
        Helper.toDisableBTN(myContext, myNextBTN);
        myUserNameEDT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals("")) {
                    Helper.toEnableBTN(myContext, myNextBTN);

                } else {
                    Helper.toDisableBTN(myContext, myNextBTN);


                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.toString().length() == 20) {
                    Toast.makeText(myContext, myContext.getString(R.string.alert_name_validate), Toast.LENGTH_SHORT).show();

                }
            }
        });

        myNextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoNextPage();

            }
        });

        myUserNameEDT.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (!myUserNameEDT.getText().toString().equals("")) {
                        gotoNextPage();
                    } else {
                        Toast.makeText(myContext, myContext.getString(R.string.error_name_alert), Toast.LENGTH_SHORT).show();
                    }
                    // do your stuff here
                }
                return false;
            }
        });
    }

    private void gotoNextPage() {
        Bundle aBundle = new Bundle();
        aBundle.putString(KEY1, myUserNameEDT.getText().toString().trim());
        myManager.updateContent(new SecondFragment(), SecondFragment.TAG, aBundle);
    }


}