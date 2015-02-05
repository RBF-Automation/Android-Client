package com.rbfautomation.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rbfautomation.R;
import com.rbfautomation.data.ActivityLogEvent;

/**
 * Created by brian on 2/5/15.
 */
public class ActivityLogPreviewItemView extends LinearLayout{



    private LinearLayout mRootView;
    private ActivityLogEvent mLogEvent;
    private LayoutInflater mInflater;
    private Context mContext;

    public ActivityLogPreviewItemView(Context context, ActivityLogEvent logEvent) {
        super(context);
        mLogEvent = logEvent;
        setupView(context);
    }

    public ActivityLogPreviewItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView(context);
    }

    public ActivityLogPreviewItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView(context);
    }

    public void setupView(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mRootView = (LinearLayout) mInflater.inflate(R.layout.activity_log_preview_item_view, this);

        ((TextView) mRootView.findViewById(R.id.log_data_user)).setText(mLogEvent.getUser());
        ((TextView) mRootView.findViewById(R.id.log_data_body)).setText(mLogEvent.getMessage());
        ((TextView) mRootView.findViewById(R.id.log_data_time)).setText(mLogEvent.getTime());

    }

}