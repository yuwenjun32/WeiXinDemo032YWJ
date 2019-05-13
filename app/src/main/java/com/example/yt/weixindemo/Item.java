package com.example.yt.weixindemo;

public class Item {
    private int iconId;
    private String iconName;

    public Item() {
        super();
    }

    public Item(int iconId, String iconName) {
        super();
        this.iconId = iconId;
        this.iconName = iconName;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
}
