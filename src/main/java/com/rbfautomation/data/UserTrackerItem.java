package com.rbfautomation.data;

/**
 * Created by brian on 2/4/15.
 */
public class UserTrackerItem {

    private final String mUser, mStatus;
    private final boolean mIsHome;

    public UserTrackerItem(String user, boolean isHome, String status) {
        mUser = user;
        mIsHome = isHome;
        mStatus = status;
    }

    public String getUser() {
        return mUser;
    }

    public boolean getIsHome() {
        return mIsHome;
    }

    public String getStatus() {
        return mStatus;
    }

}
