package com.rbfautomation.data;

public abstract class CardItem {

    private String            mName;
    private long mId;

    public CardItem(String name) {
        mName = name;
    }

    public abstract int getType();
    
    protected void setId(long id) {
        mId = id;
    }
    
    public long getId() {
        return mId;
    }
    
    public String getName() {
        return mName;
    }


}
