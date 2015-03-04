package com.rbfautomation.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by brian on 2/4/15.
 */
public class ActivityLogPreviewCardData extends CardData {

    public static final int TYPE = 1;

    public ActivityLogPreviewCardData(int remoteId, String name) {
        super(remoteId, name);
    }

    public ActivityLogPreviewCardData(Parcel parcel) {
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

    public static final Parcelable.Creator<ActivityLogPreviewCardData> CREATOR = new Parcelable.Creator<ActivityLogPreviewCardData>() {
        public ActivityLogPreviewCardData createFromParcel(Parcel in) {
            return new ActivityLogPreviewCardData(in);
        }

        public ActivityLogPreviewCardData[] newArray(int size) {
            return new ActivityLogPreviewCardData[size];
        }
    };


}
