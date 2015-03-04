package com.rbfautomation.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rbfautomation.R;
import com.rbfautomation.data.UserTrackerItem;

/**
 * Created by brian on 2/5/15.
 */
public class UserTrackerItemView extends LinearLayout{



    private LinearLayout mRootView;
    private UserTrackerItem mUserTrackerItem;
    private LayoutInflater mInflater;
    private Context mContext;

    public UserTrackerItemView(Context context, UserTrackerItem logEvent) {
        super(context);
        mUserTrackerItem = logEvent;
        setupView(context);
    }

    public UserTrackerItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView(context);
    }

    public UserTrackerItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView(context);
    }

    public void setupView(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mRootView = (LinearLayout) mInflater.inflate(R.layout.user_tracker_item_view, this);
        ((TextView) mRootView.findViewById(R.id.user_tracker_user)).setText(mUserTrackerItem.getUser());
    }

}