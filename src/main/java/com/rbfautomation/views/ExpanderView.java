package com.rbfautomation.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.rbfautomation.R;


public class ExpanderView extends LinearLayout implements OnClickListener {

    private LinearLayout mRootView, mContentView;
    private FrameLayout mExpandButton;
    private LayoutInflater mInflater;
    private OnExpandListener mListener;
    
    public ExpanderView(Context context) {
        super(context);
        setupView(context);
    }
    
    public ExpanderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView(context);
    }
    
    public ExpanderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView(context);
    }
    
    public void setupView(Context context) {
        mInflater = LayoutInflater.from(context);
        mRootView = (LinearLayout) mInflater.inflate(R.layout.expander_view, this);
        
        mExpandButton = (FrameLayout) mRootView.findViewById(R.id.expand_button);
        mExpandButton.setOnClickListener(this);
        
        mContentView = (LinearLayout) mRootView.findViewById(R.id.content);
        
    }
    
    public void expandView(boolean expand) {
        if (expand) {
            mContentView.setVisibility(View.VISIBLE);
            mListener.onExpand(true);
        } else {
            mContentView.setVisibility(View.GONE);
            mListener.onExpand(false);
        }
    }
    
    public boolean getExpanded() {
        return mContentView.getVisibility() == View.VISIBLE;
    }
    
    public void setContent(View view) {
        mContentView.removeAllViews();
        mContentView.addView(view);
    }
    
    public void setExpandButton(View view) {
        mExpandButton.removeAllViews();
        mExpandButton.addView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.expand_button:
                expandView(!getExpanded());
                break;
    
            default:
                break;
        }
        
    }
    
    public void setOnExpandListener(OnExpandListener listener) {
        mListener = listener;
    }
    
    public interface OnExpandListener {
        void onExpand(boolean expanded);
    }
    
}
