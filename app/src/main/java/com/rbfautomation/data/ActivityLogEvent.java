package com.rbfautomation.data;

/**
 * Created by brian on 2/4/15.
 */
public class ActivityLogEvent {

    private final String mUser, mMessage, mTime;

    public ActivityLogEvent(String user, String message, String time) {
        mUser = user;
        mMessage = message;
        mTime = time;
    }

    public String getUser() {
        return mUser;
    }

    public String getMessage() {
        return mMessage;
    }

    public String getTime() {
        return mTime;
    }
}
