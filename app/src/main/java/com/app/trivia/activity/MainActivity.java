package com.app.trivia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.app.trivia.R;
import com.app.trivia.fragment.FirstFragment;
import com.app.trivia.fragmentmanager.MyFragmentManager;

public class MainActivity extends AppCompatActivity {

    private MyFragmentManager myManager;

    private FragmentActivity myContext;

    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        myContext = MainActivity.this;
        myManager = new MyFragmentManager(myContext);
        defaultLoadFragment();
    }

    private void defaultLoadFragment() {

        myManager.updateContent(new FirstFragment(), FirstFragment.TAG, null);
    }

    @Override
    public void onBackPressed() {
        if (myManager.getBackstackCount() > 1) {
            myManager.backPress();
        } else {
            if (back_pressed + 2000 > System.currentTimeMillis()) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                System.exit(0);

            } else {

                Toast.makeText(getBaseContext(),
                        "Press once again to exit!", Toast.LENGTH_SHORT)
                        .show();

            }

            back_pressed = System.currentTimeMillis();

        }
    }
}