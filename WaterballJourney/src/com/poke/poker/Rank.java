package com.poke.poker;

public enum Rank {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NIGHT(9), TEN(10), J(11), Q(12), K(13);
    private int order;

    Rank(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
