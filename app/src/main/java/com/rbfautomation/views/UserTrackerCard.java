package com.rbfautomation.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.rbfautomation.R;
import com.rbfautomation.data.UserTrackerCardData;
import com.rbfautomation.data.UserTrackerItem;
import com.rbfautomation.network.NetworkManager;
import com.rbfautomation.network.requests.GetUserTrackerDataRequest;
import com.rbfautomation.network.responses.GetUserTrackerResponse;
import com.rbfautomation.network.responses.Response;

import java.util.ArrayList;

/**
 * Created by brian on 2/4/15.
 */
public class UserTrackerCard extends CardView implements NetworkManager.NetworkEventListener {

    private static final int NUM_RESULTS = 5;

    private Context mContext;
    private View mBody;
    private ProgressBar mProgressBar;
    private LinearLayout mPreviewDataContainer;
    private Button mRetryButton;

    private UserTrackerCardData mCardItem;

    private NetworkManager mNetworkManager;

    public UserTrackerCard(Context context, UserTrackerCardData cardItem, CardViewEventHandler eventHandler) {
        super(context, cardItem, eventHandler);
    }

    public UserTrackerCard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserTrackerCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setupView(Context context) {
        super.setupView(context);
        mContext = context;

        mCardItem = (UserTrackerCardData) getCardItem();
        mBody = inflateBody(R.layout.user_tracker_card_body);
        mProgressBar = (ProgressBar) mBody.findViewById(R.id.user_data_loading);
        mPreviewDataContainer = (LinearLayout) mBody.findViewById(R.id.user_data_content);
        mRetryButton = (Button) mBody.findViewById(R.id.user_tracker_retry);
        mRetryButton.setOnClickListener(this);

        setHeader(mCardItem.getName());

        mNetworkManager = new NetworkManager(this, context, getEventHandler().getSessionContext());
        refresh();

        useMenu(true);
    }

    public void refresh() {
        mRetryButton.setVisibility(GONE);
        mPreviewDataContainer.removeAllViews();
        mPreviewDataContainer.setVisibility(GONE);
        mProgressBar.setVisibility(VISIBLE);
        mNetworkManager.request(new GetUserTrackerDataRequest(mCardItem.getRemoteId()));
    }

    @Override
    public int getContextMenuResource() {
        return R.menu.refresh_button_menu;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        super.onMenuItemClick(item);

        switch (item.getItemId()) {
            case R.id.refresh:
                refresh();
                return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case R.id.user_tracker_retry:
                refresh();
                break;

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
                case GetUserTrackerDataRequest.TYPE:
                    showUserTrakerData(((GetUserTrackerResponse)response).getUserTrackerData());
                    break;
            }
        }
    }

    private void showUserTrakerData(ArrayList<UserTrackerItem> items) {
        mPreviewDataContainer.setVisibility(VISIBLE);
        mProgressBar.setVisibility(GONE);

        for (UserTrackerItem item : items) {
            mPreviewDataContainer.addView(new UserTrackerItemView(mContext, item));
        }
    }


    private void handleResponseError(Response response) {
        mRetryButton.setVisibility(VISIBLE);
        mProgressBar.setVisibility(GONE);
        getEventHandler().onCardNetworkError(response.getErrorCode(), response.getErrorMessage());
    }
}