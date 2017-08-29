package com.example.mvp.communication.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mvp.communication.R;


public class MyRefreshView extends SwipeRefreshLayout {
    public MyRefreshView(Context context) {
        this(context, null);
    }

    public MyRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.list_item, null);
        Log.i("MyRefreshView", "MyRefreshView: " + view.getHeight() + " " + view.getWidth());

    }
}
