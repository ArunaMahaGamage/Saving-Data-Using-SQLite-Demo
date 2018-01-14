package com.example.savingdatausingsqlite.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.savingdatausingsqlite.R;
import com.example.savingdatausingsqlite.bean.ReadData;

import java.util.List;

/**
 * Created by aruna on 1/14/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ReadData> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public TextView mtv_id, mtv_title, mtv_subtitle;

        public ViewHolder(View v) {
            super(v);
            mtv_id = v.findViewById(R.id.tv_id);
            mtv_title = v.findViewById(R.id.tv_title);
            mtv_subtitle = v.findViewById(R.id.tv_subtitle);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List myDataset) {
        mDataset = myDataset;
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.mtv_id.setText(mDataset.get(position).getId());
        holder.mtv_title.setText(mDataset.get(position).getTitle());
        holder.mtv_subtitle.setText(mDataset.get(position).getSubTitle());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
