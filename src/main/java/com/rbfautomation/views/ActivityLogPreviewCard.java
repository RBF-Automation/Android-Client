package com.rbfautomation.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.rbfautomation.R;
import com.rbfautomation.data.ActivityLogEvent;
import com.rbfautomation.data.ActivityLogPreviewCardData;
import com.rbfautomation.network.NetworkManager;
import com.rbfautomation.network.requests.GetActivityLogRequest;
import com.rbfautomation.network.responses.GetActivtyListResponse;
import com.rbfautomation.network.responses.Response;

import java.util.ArrayList;

/**
 * Created by brian on 2/4/15.
 */
public class ActivityLogPreviewCard extends CardView implements NetworkManager.NetworkEventListener {

    private static final int NUM_RESULTS = 5;

    private Context mContext;
    private View mBody;
    private ProgressBar mProgressBar;
    private LinearLayout mPreviewDataContainer;
    private Button mRetryButton;

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
        mRetryButton = (Button) mBody.findViewById(R.id.retry_button_activity_preview);
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
        mNetworkManager.request(new GetActivityLogRequest(NUM_RESULTS));
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
            case R.id.retry_button_activity_preview:
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
                case GetActivityLogRequest.TYPE:
                    showLogData(((GetActivtyListResponse)response).getActivityEventList());
                    break;
            }
        }
    }

    private void showLogData(ArrayList<ActivityLogEvent> events) {
        mPreviewDataContainer.setVisibility(VISIBLE);
        mProgressBar.setVisibility(GONE);

        for (ActivityLogEvent event : events) {
            mPreviewDataContainer.addView(new ActivityLogPreviewItemView(mContext, event));
        }
    }


    private void handleResponseError(Response response) {
        mRetryButton.setVisibility(VISIBLE);
        mProgressBar.setVisibility(GONE);
        getEventHandler().onCardNetworkError(response.getErrorCode(), response.getErrorMessage());
    }
}