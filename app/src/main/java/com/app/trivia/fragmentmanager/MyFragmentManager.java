package com.app.trivia.fragmentmanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.app.trivia.R;

public class MyFragmentManager {

    private FragmentActivity myContext;

    /**
     * Constructor to Initiate fragment manager
     *
     * @param aContext
     */
    public MyFragmentManager(FragmentActivity aContext) {
        myContext = aContext;
    }

    /**
     * Update the Current Fragment by passing the below parameters
     *
     * @param aFragment
     * @param tag
     * @param aBundle
     */
    public void updateContent(Fragment aFragment, String tag, Bundle aBundle) {
        try {

            Log.e("TAG Screen name", tag);

            // Initialise Fragment Manager
            final FragmentManager aFragmentManager = myContext
                    .getSupportFragmentManager();

            // Initialise Fragment Transaction
            final FragmentTransaction aTransaction = aFragmentManager
                    .beginTransaction();

            if (aBundle != null) {
                aFragment.setArguments(aBundle);
            }

            aTransaction.add(R.id.main_container, aFragment, tag);

            // add the tag to the backstack
            aTransaction.addToBackStack(tag);

            // Commit the Fragment transaction
            aTransaction.commit();

        } catch (Exception aError) {
            aError.printStackTrace();
        }
    }


    /**
     * Clear All Fragments
     */
    public void clearAllFragments() {

        try {
            FragmentManager aFragmentManager = myContext
                    .getSupportFragmentManager();

            aFragmentManager.popBackStack(null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void backPress() {

        FragmentManager aFragmentManager = myContext
                .getSupportFragmentManager();

        if (aFragmentManager.getBackStackEntryCount() > 1) {
            aFragmentManager.popBackStack();
            aFragmentManager.executePendingTransactions();

            Fragment aFragment = aFragmentManager.getFragments().get(aFragmentManager.getBackStackEntryCount() - 1);
            aFragment.onResume();

        }
    }

    public int getBackstackCount() {

        FragmentManager aFragmentManager = myContext
                .getSupportFragmentManager();

        return aFragmentManager.getBackStackEntryCount();
    }

}
