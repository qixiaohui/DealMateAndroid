package com.dealmate.xiaohui.dealmate.config;

public enum CategoryEnum {
    COMPUTER("computers"),
    GAMING("gaming"),
    ELECTRONICS("electronics"),
    TABLET("tablets-phones"),
    LIFESTYLE("lifestyle-home");

    private final String text;

    private CategoryEnum(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}