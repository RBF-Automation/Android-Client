package com.rbfautomation.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.rbfautomation.R;
import com.rbfautomation.data.ActivityLogPreviewCardData;
import com.rbfautomation.network.NetworkManager;
import com.rbfautomation.network.responses.ErrorCodes;
import com.rbfautomation.network.responses.Response;

/**
 * Created by brian on 2/4/15.
 */
public class ActivityLogPreviewCard extends CardView implements NetworkManager.NetworkEventListener {

    private Context mContext;
    private View mBody;

    private ActivityLogPreviewCardData mCardItem;

    private NetworkManager mNetworkManager;

    public ActivityLogPreviewCard(Context context, ActivityLogPreviewCardData cardItem, CardViewEventHandler eventHandler) {
        super(context, cardItem, eventHandler);
    }

    public ActivityLogPreviewCard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityLogPreviewCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setupView(Context context) {
        super.setupView(context);
        mContext = context;

        mCardItem = (ActivityLogPreviewCardData) getCardItem();

        mBody = inflateBody(R.layout.activity_log_preview_card_body);

        setHeader(mCardItem.getName());

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
                getEventHandler().onCardNetworkError(ErrorCodes.NOT_LOGGED_IN, response.getErrorMessage());
                break;
            case ErrorCodes.JSON_PARSE_ERROR:
                Log.e("JSON", response.getErrorMessage());
                break;
        }
    }
}