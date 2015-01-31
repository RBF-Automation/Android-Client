package com.rbfautomation.data;


public class SwitchCardItem extends CardItem {
    
    public static final int TYPE = 0;

    private String mOnButtonText, mOffButtonText;
    
    public SwitchCardItem(String name, String onButtonText, String offButtonText) {
        super(name);
        mOnButtonText = onButtonText;
        mOffButtonText = offButtonText;
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
    

}
