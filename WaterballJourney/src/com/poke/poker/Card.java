package com.poke.poker;

public class Card implements Comparable<Card> {
    Rank rank;
    Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    @Override
    public int compareTo(Card card) {
        if (this.getRank().getOrder() == card.getRank().getOrder()) {
            return this.getSuit().getOrder() <= card.getSuit().getOrder() ? 1 : -1;
        }
        return this.getRank().getOrder() <= card.getRank().getOrder() ? 1 : -1;
    }
}
