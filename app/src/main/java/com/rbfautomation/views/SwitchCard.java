package com.rbfautomation.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rbfautomation.R;


public class SwitchCard extends CardView {

	private Context mContext;
	private View mBody;
	private TextView mBodyText;
    private Button mOnButton, mOffButton;

	public SwitchCard(Context context) {
		super(context);
	}
	
	public SwitchCard(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public SwitchCard(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	
	@Override
	public void setupView(Context context) {
		super.setupView(context);
		mContext = context;
		
		mBody = inflateBody(R.layout.switch_card_body);

        mOnButton = (Button) mBody.findViewById(R.id.on_button);
        mOffButton = (Button) mBody.findViewById(R.id.off_button);

        mOnButton.setOnClickListener(this);
        mOffButton.setOnClickListener(this);
		
	}

    public void setOnButtonText(String text) {
        mOnButton.setText(text);
    }

    public void setOffButtonText(String text) {
        mOffButton.setText(text);
    }
	
	@Override
	public int getContextMenuResource() {
		return R.menu.test_card;
	}

	@Override
	public boolean onMenuItemClick(MenuItem arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		
		switch (v.getId()) {
			//case R.id.switch_body_text:
			//	//TODO: navigation intent.
			//	break;
	
			default:
				break;
		}
	}

}
