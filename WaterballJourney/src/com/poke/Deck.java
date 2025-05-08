package com.poke;

import com.poke.player.Player;
import com.poke.poker.Card;

import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public void drawCards(Player player) {
        Card card = cards.get(0);//都先抽第一張
        cards.remove(card);
        player.receiveCard(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

}
