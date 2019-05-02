package com.app.trivia.adpater;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.trivia.R;
import com.app.trivia.fragmentmanager.MyFragmentManager;
import com.app.trivia.helper.CommonValues;
import com.app.trivia.model.Summary;

import java.util.ArrayList;

public class HistoryAdapter extends BaseAdapter implements CommonValues {

    private FragmentActivity myContext;

    private ArrayList<Summary> myMenuList;

    private MyFragmentManager myFragmentManger;

    public HistoryAdapter(FragmentActivity aContext) {
        this.myContext = aContext;

    }

    public HistoryAdapter(FragmentActivity aContext, ArrayList<Summary> aList) {
        this.myContext = aContext;
        this.myMenuList = aList;
        myFragmentManger = new MyFragmentManager(myContext);
    }

    @Override
    public int getCount() {
        return myMenuList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            final ViewHolder holder;

            LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {

                holder = new ViewHolder();

                convertView = inflater.inflate(R.layout.inflate_history, null);

                holder.myDateTimeTXT = (TextView) convertView.findViewById(R.id.date_time_txt);

                holder.myUserNameTXT = (TextView) convertView.findViewById(R.id.name_txt);

                holder.mySecondAnsTXT = (TextView) convertView.findViewById(R.id.second_ans_item_txt);

                holder.myThirdAnsTXT = (TextView) convertView.findViewById(R.id.third_ans_item_txt);


            } else {

                holder = (ViewHolder) convertView.getTag();
            }


            final Summary aSummary = myMenuList.get(position);

            holder.myDateTimeTXT.setText(myContext.getResources().getString(R.string.lbl_game) + aSummary.getDateTime());

            holder.myUserNameTXT.setText(myContext.getResources().getString(R.string.lbl_game) + aSummary.getUserName());

            holder.mySecondAnsTXT.setText(aSummary.getSecondAns());

            holder.myThirdAnsTXT.setText(aSummary.getThirdAns());


        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }

    private class ViewHolder {


        TextView myDateTimeTXT, myUserNameTXT, mySecondAnsTXT, myThirdAnsTXT;


    }


}
