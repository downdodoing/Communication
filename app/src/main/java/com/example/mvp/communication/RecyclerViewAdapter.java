package com.example.mvp.communication;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mvp.communication.entity.Msg;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHoder> {

    private List<Msg> mData;

    public RecyclerViewAdapter(List<Msg> mData) {
        this.mData = mData;
    }

    @Override
    public void onBindViewHolder(ViewHoder holder, int position) {
        Msg msg = mData.get(position);
        if (Msg.TYPE_RECEIVED == msg.getType()) {
            holder.left_ll.setVisibility(View.VISIBLE);
            holder.right_ll.setVisibility(View.GONE);
            holder.left_txt.setText(msg.getContent());
        } else if (Msg.TYPE_SENT == msg.getType()) {
            holder.left_ll.setVisibility(View.GONE);
            holder.right_ll.setVisibility(View.VISIBLE);
            holder.right_txt.setText(msg.getContent());
        }

        Log.d("MainActivity", "onBindViewHolder: " + msg.getContent());
    }

    @Override
    public ViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        Log.i("msg.getContent()", "onCreateViewHolder: ");
        return new ViewHoder(view);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHoder extends RecyclerView.ViewHolder {
        TextView left_txt;
        TextView right_txt;

        LinearLayout left_ll;
        LinearLayout right_ll;

        public ViewHoder(View view) {
            super(view);
            left_ll = (LinearLayout) view.findViewById(R.id.left_layout);
            right_ll = (LinearLayout) view.findViewById(R.id.right_layout);

            left_txt = (TextView) view.findViewById(R.id.left_txt);
            right_txt = (TextView) view.findViewById(R.id.right_txt);
        }
    }
}
