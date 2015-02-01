package com.rbfautomation.data;

public abstract class CardItem {

    private String mName;
    private int mRemoteId;

    public CardItem(int remoteId, String name) {
        mRemoteId = remoteId;
        mName = name;
    }

    public abstract int getType();
    
    public int getRemoteId() {
        return mRemoteId;
    }
    
    public String getName() {
        return mName;
    }


}
