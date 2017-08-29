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

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private List<Msg> mData;

    public final static int LOADING_MORE = 0;//上拉加载更多
    public final static int LOADING = 1;//正在加载
    public final static int LOADING_FINISH = 2;//暂无数据

    private int state = LOADING_MORE;//加载数据状态

    public RecyclerViewAdapter(List<Msg> mData) {
        this.mData = mData;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i("RecyclerViewAdapter", "onBindViewHolder: " + position);
        if (holder instanceof ViewHoder) {
            Msg msg = mData.get(position);
            if (Msg.TYPE_RECEIVED == msg.getType()) {
                ((ViewHoder) holder).left_ll.setVisibility(View.VISIBLE);
                ((ViewHoder) holder).right_ll.setVisibility(View.GONE);
                ((ViewHoder) holder).left_txt.setText(msg.getContent());
            } else if (Msg.TYPE_SENT == msg.getType()) {
                ((ViewHoder) holder).left_ll.setVisibility(View.GONE);
                ((ViewHoder) holder).right_ll.setVisibility(View.VISIBLE);
                ((ViewHoder) holder).right_txt.setText(msg.getContent());
            }
        } else if (holder instanceof FooterViewHoder) {
            String state_str = "";
            switch (state) {
                case LOADING_MORE:
                    state_str = "上拉加载更多";
                    break;
                case LOADING:
                    state_str = "正在加载";
                    break;
                case LOADING_FINISH:
                    state_str = "暂无数据";
                    break;
            }
            ((FooterViewHoder) holder).textView.setText(state_str);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (1 == viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new FooterViewHoder(view);
        } else if (0 == viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
            return new ViewHoder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return 1;//显示底部
        } else {
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }

    //改变状态
    public void setState(int state) {
        this.state = state;
        notifyItemChanged(mData.size());
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

    static class FooterViewHoder extends RecyclerView.ViewHolder {
        TextView textView;

        public FooterViewHoder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
