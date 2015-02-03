package com.rbfautomation.data;


import android.os.Parcel;
import android.os.Parcelable;

public class SwitchCardItem extends CardItem {
    
    public static final int TYPE = 0;

    private String mOnButtonText, mOffButtonText;
    
    public SwitchCardItem(int remoteId, String name, String onButtonText, String offButtonText) {
        super(remoteId, name);
        mOnButtonText = onButtonText;
        mOffButtonText = offButtonText;
    }

    public SwitchCardItem(Parcel parcel) {
        super(parcel);
        mOnButtonText = parcel.readString();
        mOffButtonText = parcel.readString();
    }

    public String getOnButtonText() {
        return mOnButtonText;
    }

    public String getOffButtonText() {
        return mOffButtonText;
    }
    
    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        super.writeToParcel(parcel, flags);
        parcel.writeString(mOnButtonText);
        parcel.writeString(mOffButtonText);
    }

    @Override
    public int describeContents() {
        return TYPE;
    }

    public static final Parcelable.Creator<SwitchCardItem> CREATOR = new Parcelable.Creator<SwitchCardItem>() {
        public SwitchCardItem createFromParcel(Parcel in) {
            return new SwitchCardItem(in);
        }

        public SwitchCardItem[] newArray(int size) {
            return new SwitchCardItem[size];
        }
    };
    

}
