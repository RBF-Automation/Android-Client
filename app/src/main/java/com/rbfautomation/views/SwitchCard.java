package com.rbfautomation.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rbfautomation.R;
import com.rbfautomation.data.SwitchCardItem;
import com.rbfautomation.network.NetworkManager;
import com.rbfautomation.network.requests.SetSwitchRequest;
import com.rbfautomation.network.responses.ErrorCodes;
import com.rbfautomation.network.responses.Response;


public class SwitchCard extends CardView implements NetworkManager.NetworkEventListener {

	private Context mContext;
	private View mBody;
    private Button mOnButton, mOffButton;

    private SwitchCardItem mCardItem;

    private NetworkManager mNetworkManager;

	public SwitchCard(Context context, SwitchCardItem cardItem) {
		super(context, cardItem);
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

        mCardItem = (SwitchCardItem) getCardItem();
		
		mBody = inflateBody(R.layout.switch_card_body);

        mOnButton = (Button) mBody.findViewById(R.id.on_button);
        mOffButton = (Button) mBody.findViewById(R.id.off_button);

        mOnButton.setOnClickListener(this);
        mOffButton.setOnClickListener(this);

        setHeader(mCardItem.getName());
        mOnButton.setText(mCardItem.getOnButtonText());
        mOffButton.setText(mCardItem.getOffButtonText());

        mNetworkManager = new NetworkManager(this, context);
	}

	@Override
	public int getContextMenuResource() {
		return CardView.NO_MENU;
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
			case R.id.on_button:
                mNetworkManager.request(new SetSwitchRequest(mCardItem.getRemoteId(), 1));
                break;

            case R.id.off_button:
                mNetworkManager.request(new SetSwitchRequest(mCardItem.getRemoteId(), 0));
                break;
	
			default:
				break;
		}
	}

    @Override
    public void onCompleteRequest(Response response) {

        if (response.hasError()) {
            handleResponseError(response);
        }

    }

    private void handleResponseError(Response response) {
        switch (response.getErrorCode()) {
            case ErrorCodes.NOT_LOGGED_IN:
                Toast.makeText(getContext(), response.getErrorMessage(), Toast.LENGTH_SHORT).show();
                break;
            case ErrorCodes.JSON_PARSE_ERROR:
                Log.e("JSON", response.getErrorMessage());
                break;
        }
    }
}
