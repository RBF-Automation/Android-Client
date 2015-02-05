package com.rbfautomation.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.rbfautomation.R;
import com.rbfautomation.data.ActivityLogEvent;
import com.rbfautomation.data.ActivityLogPreviewCardData;
import com.rbfautomation.network.NetworkManager;
import com.rbfautomation.network.requests.GetActivityLogRequest;
import com.rbfautomation.network.responses.ErrorCodes;
import com.rbfautomation.network.responses.GetActivtyListResponse;
import com.rbfautomation.network.responses.Response;

import java.util.ArrayList;

/**
 * Created by brian on 2/4/15.
 */
public class ActivityLogPreviewCard extends CardView implements NetworkManager.NetworkEventListener {

    private Context mContext;
    private View mBody;
    private ProgressBar mProgressBar;
    private LinearLayout mPreviewDataContainer;

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
        mProgressBar = (ProgressBar) mBody.findViewById(R.id.log_loading);
        mPreviewDataContainer = (LinearLayout) mBody.findViewById(R.id.log_content);

        setHeader(mCardItem.getName());

        mNetworkManager = new NetworkManager(this, context);
        mNetworkManager.request(new GetActivityLogRequest(5));
    }

    @Override
    public int getContextMenuResource() {
        return CardView.NO_MENU;
    }

    @Override
    public boolean onMenuItemClick(MenuItem arg0) {
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
        } else {
            switch (response.getType()) {
                case GetActivityLogRequest.TYPE:
                    showLogData(((GetActivtyListResponse)response).getActivityEventList());
                    break;
            }
        }
    }

    private void showLogData(ArrayList<ActivityLogEvent> events) {
        mProgressBar.setVisibility(View.GONE);
        for (int i = 0; i < events.size(); i++) {
            mPreviewDataContainer.addView(new ActivityLogPreviewItemView(mContext, events.get(i)));
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
            default:
                Log.e("ERROR_OTHER", response.getErrorMessage());
                break;
        }
    }
}