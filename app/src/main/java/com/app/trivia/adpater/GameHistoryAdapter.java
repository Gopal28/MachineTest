package com.app.trivia.adpater;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.trivia.R;
import com.app.trivia.model.Summary;

import java.util.ArrayList;

public class GameHistoryAdapter extends RecyclerView.Adapter<GameHistoryAdapter.MyViewHolder> {

    private ArrayList<Summary> SummaryList;

    private FragmentActivity myContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView myDateTimeTXT, myUserNameTXT, mySecondAnsTXT, myThirdAnsTXT;

        public MyViewHolder(View view) {
            super(view);
            myDateTimeTXT = (TextView) view.findViewById(R.id.date_time_txt);

            myUserNameTXT = (TextView) view.findViewById(R.id.name_txt);

            mySecondAnsTXT = (TextView) view.findViewById(R.id.second_ans_item_txt);

            myThirdAnsTXT = (TextView) view.findViewById(R.id.third_ans_item_txt);
        }
    }


    public GameHistoryAdapter(FragmentActivity aContext, ArrayList<Summary> SummarysList) {
        this.SummaryList = SummarysList;
        myContext = aContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inflate_history, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Summary aSummary = SummaryList.get(position);

        holder.myDateTimeTXT.setText(myContext.getResources().getString(R.string.lbl_game) + " " + (position + 1) + " :" + " " + aSummary.getDateTime());

        holder.myUserNameTXT.setText(myContext.getResources().getString(R.string.lbl_name) + " " + aSummary.getUserName());

        holder.mySecondAnsTXT.setText(aSummary.getSecondAns());

        holder.myThirdAnsTXT.setText(aSummary.getThirdAns());

    }

    @Override
    public int getItemCount() {
        return SummaryList.size();
    }
}
