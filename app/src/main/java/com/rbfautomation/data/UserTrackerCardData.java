package com.rbfautomation.data;

import android.os.Parcel;

/**
 * Created by brian on 2/4/15.
 */
public class UserTrackerCardData extends CardData {

    public static final int TYPE = 2;

    public UserTrackerCardData(int remoteId, String name) {
        super(remoteId, name);
    }

    public UserTrackerCardData(Parcel parcel) {
        super(parcel);
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        super.writeToParcel(parcel, flags);
    }

    @Override
    public int describeContents() {
        return TYPE;
    }

    public static final Creator<UserTrackerCardData> CREATOR = new Creator<UserTrackerCardData>() {
        public UserTrackerCardData createFromParcel(Parcel in) {
            return new UserTrackerCardData(in);
        }

        public UserTrackerCardData[] newArray(int size) {
            return new UserTrackerCardData[size];
        }
    };


}
