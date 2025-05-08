package com.poke.poker;

public enum Suit {
    CLUB(1), DIAMOND(2), HEART(3), SPADE(4);
    private int order;

    Suit(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
