package com.poke;

import com.poke.player.AIPlayer;
import com.poke.player.HumanPlayer;
import com.poke.player.Player;
import com.poke.poker.Card;
import com.poke.poker.Rank;
import com.poke.poker.Suit;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ShowDown showDown = new ShowDown(chooseFourPlayers());
        List<Card> cards = createStandard52Cards();
        showDown.start(new Deck(cards));
    }

    private static List<Card> createStandard52Cards() {
        List<Card> cards = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                Card card = new Card(rank, suit);
                cards.add(card);
            }
        }
        return cards;
    }

    private static List<Player> chooseFourPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new HumanPlayer();
        Player player2 = new AIPlayer();
        Player player3 = new HumanPlayer();
        Player player4 = new AIPlayer();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        return players;
    }
}
