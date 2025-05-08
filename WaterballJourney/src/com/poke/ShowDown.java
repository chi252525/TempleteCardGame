package com.poke;

import com.poke.player.Player;
import com.poke.poker.Card;

import java.util.*;
import java.util.stream.Collectors;

public class ShowDown {
    private final static int NUMBER_OF_ROUND = 13;

    private List<Player> players;

    public ShowDown(List<Player> players) {
        this.players = players;
        players.forEach(p -> p.setShowdown(this));
    }

    public void start(Deck deck) {
        nameHimself(players);
        distributeCards(players, deck);
        for (int i = 1; i <= NUMBER_OF_ROUND; i++) {
            System.out.println("第" + i + "回合");
            takeTurns(players);
        }
        showdown(players);//攤牌
        Player winner = getWinner(players);
        System.out.println("winner is =" + winner.getName() + " " + winner.getPoint());
        System.out.println("遊戲結束");
    }

    private Player getWinner(List<Player> players) {
        Player winner = players.stream().sorted().findFirst().get();
        return winner;
    }

    private void distributeCards(List<Player> players, Deck deck) {
        //洗牌
        deck.shuffle();
        //抽牌階段
        for (int i = NUMBER_OF_ROUND; i > 0; i--) {
            for (Player player : players) {
                deck.drawCards(player);
            }
        }
    }

    private void nameHimself(List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).naming(i + 1);
        }
    }

    public void takeTurns(List<Player> players) {
        for (Player player : players) {
            player.takeTurn();
        }
    }

    private void showdown(List<Player> players) {
        Map<Card, Player> showedCards = new HashMap<>();
        for (Player player : players) {
            Card showedCard = player.showCard();
            if (showedCard != null) {
                showedCards.put(showedCard, player);
                System.out.println(player.getName() + "出了" + showedCard.getRank().name() + " " + showedCard.getSuit().name());
            } else {
                System.out.println(player.getName() + "沒牌出");
            }
        }
        compareShowCardsAndGainPoint(showedCards);
    }


    private void compareShowCardsAndGainPoint(Map<Card, Player> showedCards) {
        if (showedCards.size() == 0) {
            return;
        }
        Map<Card, Player> showedCardsMap = showedCards.entrySet().stream().sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        Map.Entry<Card, Player> cardPlayerEntry = showedCardsMap.entrySet().stream().findFirst().get();
        cardPlayerEntry.getValue().gainPoint(1);
    }


    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
