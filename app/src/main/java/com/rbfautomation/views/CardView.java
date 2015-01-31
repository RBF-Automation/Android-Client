package com.rbfautomation.views;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rbfautomation.R;


public abstract class CardView extends LinearLayout implements OnClickListener, PopupMenu.OnMenuItemClickListener {

	private LinearLayout mRootView;
	private FrameLayout mContentBody;
	private Context mContext;
	private LayoutInflater mInflater;
    private TextView mHeaderText;

	public CardView(Context context) {
		super(context);
		setupView(context);
	}
	
	public CardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setupView(context);
	}
	
	public CardView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setupView(context);
	}
	
	public void setupView(Context context) {
		mInflater = LayoutInflater.from(context);
		mContext = context;
		mRootView = (LinearLayout) mInflater.inflate(R.layout.view_card, this);
		
		mRootView.findViewById(R.id.context_menu).setOnClickListener(this);
		
        mHeaderText = (TextView) mRootView.findViewById(R.id.card_header);
		mContentBody = (FrameLayout) mRootView.findViewById(R.id.content_body);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.context_menu:
				popContextMenu(v);
				break;
	
			default:
				break;
		}
		
	}

    public void setHeader(String header) {
        mHeaderText.setText(header);
    }
	
	public void popContextMenu(View v) {
		PopupMenu popup = new PopupMenu(mContext, v);
        popup.getMenuInflater().inflate(getContextMenuResource(), popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(this);
	}
	
	public abstract int getContextMenuResource();
	
	@Override
	public abstract boolean onMenuItemClick(MenuItem arg0);
	
	
	public View inflateBody(int layout) {
		return mInflater.inflate(layout, mContentBody);
	}
	
	
	
}
