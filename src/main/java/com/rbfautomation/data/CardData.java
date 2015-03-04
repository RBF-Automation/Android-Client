package com.rbfautomation.data;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class CardData implements Parcelable {

    private String mName;
    private int mRemoteId;

    public CardData(int remoteId, String name) {
        mRemoteId = remoteId;
        mName = name;
    }

    public CardData(Parcel parcel) {
        mName = parcel.readString();
        mRemoteId = parcel.readInt();
    }

    public abstract int getType();
    
    public int getRemoteId() {
        return mRemoteId;
    }

    public String getName() {
        return mName;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(mName);
        parcel.writeInt(mRemoteId);
    }


}
