package com.app.trivia.helper;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.app.trivia.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper implements CommonValues {
    public static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    public static void toDisableBTN(FragmentActivity aContext, Button aBtn) {
        aBtn.setEnabled(false);
        aBtn.setBackground(aContext.getDrawable(R.drawable.btn_unselect));
    }

    public static void toEnableBTN(FragmentActivity aContext, Button aBtn) {
        aBtn.setEnabled(true);
        aBtn.setBackground(aContext.getDrawable(R.drawable.btn_selected));
    }

    /*
    Current Date and time
     */
    public static String getCurrentDateTime() {

        String aCurrentDateAndTime = new SimpleDateFormat(DATE_TIME_FORMAT).format(new Date());

        return aCurrentDateAndTime;
    }

    /**
     * Hide Keyboard
     *
     * @param aContext
     */
    public static void hideKeyboard(FragmentActivity aContext) {
        InputMethodManager imm = (InputMethodManager) aContext.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = aContext.getCurrentFocus();
        if (view == null) {
            view = new View(aContext);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
