package com.poke.player;

public class ExchangeRelationShip {
    private boolean isExchangeUsed = false;
    private Player exchangePlayer = null;
    private int roundCountDown = 0;

    public boolean isExchangeUsed() {
        return isExchangeUsed;
    }

    public void setExchangeUsed(boolean exchangeUsed) {
        isExchangeUsed = exchangeUsed;
    }

    public Player getExchangePlayer() {
        return exchangePlayer;
    }

    public void setExchangePlayer(Player exchangePlayer) {
        this.exchangePlayer = exchangePlayer;
    }

    public int getRoundCountDown() {
        return roundCountDown;
    }

    public void setRoundCountDown(int roundCountDown) {
        this.roundCountDown = roundCountDown;
    }
}
