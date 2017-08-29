package com.example.mvp.communication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mvp.communication.entity.Msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private RecyclerView mRecyclerView;
    private Button mSendBnt;
    private EditText mEditText;

    private List<Msg> mData;
    private RecyclerViewAdapter mAdapter;

    private String content;
    Random mRandom;
    boolean isClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecyclerViewAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
        addScrollLisener();
    }

    private void addScrollLisener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }
        });
    }

    public void click(View view) {
        if (view.getId() == R.id.send) {
            content = mEditText.getText().toString();
            int type = mRandom.nextInt(2);
            if (!TextUtils.isEmpty(content)) {
                mData.add(new Msg(content, type));
                mAdapter.notifyItemInserted(mData.size() - 1);//插入到最后一行
                // mRecyclerView.scrollToPosition(mData.size() - 1);//定位到最后一行
                mEditText.setText("");
            }
        } else if (view.getId() == R.id.remove) {
            if (!isClick) {
                mRecyclerView.getChildAt(mData.size()).setVisibility(View.VISIBLE);
                isClick = true;
            } else {
                mRecyclerView.getChildAt(mData.size()).setVisibility(View.GONE);
                isClick = false;
            }
        }
    }

    private void init() {

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mSendBnt = (Button) findViewById(R.id.send);
        mEditText = (EditText) findViewById(R.id.edit_txt);

        mData = new ArrayList<>();
        mRandom = new Random();
    }

}
