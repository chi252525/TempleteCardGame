package com.poke.player;

import com.poke.ShowDown;
import com.poke.poker.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public abstract class Player implements Comparable<Player> {
    protected ShowDown showdown;
    private List<Card> cardInHand = new ArrayList<>();
    private String name;
    private int point = 0;
    private ExchangeRelationShip relationship = new ExchangeRelationShip();

    public void receiveCard(Card card) {
        cardInHand.add(card);
    }

    public abstract void naming(int order);

    public void exchangePlayerHandCards(Player exchangePlayer) {
        List<Card> exchangePlayerCards = exchangePlayer.getCardInHand();
        List<Card> thisPlayerCards = this.cardInHand;
        this.cardInHand = exchangePlayerCards;
        exchangePlayer.setCardInHand(thisPlayerCards);
    }

    public Card showCard() {
        if (cardInHand.size() != 0) {
            Card card = cardInHand.get(0);
            cardInHand.remove(card);
            return card;
        }
        return null;
    }

    public void takeTurn() {
        makeExchangeHandDecision();
    }

    private void makeExchangeHandDecision() {
        if (this.relationship.isExchangeUsed() && this.relationship.getRoundCountDown() < 3) {
            //記錄已交換的次數

            relationship.setRoundCountDown(relationship.getRoundCountDown() + 1);
            System.out.println(this.name + "和" + this.getRelationship().getExchangePlayer().getName() + "交換手牌中 第" + this.relationship.getRoundCountDown() + "輪");

        } else if (this.relationship.getRoundCountDown() == 3) {
            //換回手牌
            Player exchangedPlayer = this.relationship.getExchangePlayer();
            System.out.println(this.name + "和" + exchangedPlayer.getName() + "換回手牌");
            exchangedPlayer.exchangePlayerHandCards(this);
            //解除關係
            this.setRelationship(new ExchangeRelationShip());
            exchangedPlayer.setRelationship(new ExchangeRelationShip());
        } else {
            //隨機決定要不要交換牌
            Random random = new Random();
            boolean isDecideToExchange = random.nextBoolean();
            if (isDecideToExchange) {
                Player exchangePlayer = chooseExchangePlayer(this.getShowdown().getPlayers(), this);
                if (exchangePlayer != null) {
                    System.out.println(this.name + "和" + exchangePlayer.getName() + "交換手牌");
                    //交換手牌
                    this.exchangePlayerHandCards(exchangePlayer);
                    this.relationship.setExchangeUsed(true);
                    this.relationship.setRoundCountDown(this.relationship.getRoundCountDown() + 1);
                }
            }
        }
    }

    public void gainPoint(int point) {
        this.point = this.point + point;
    }

    public List<Card> getCardInHand() {
        return cardInHand;
    }

    public void setCardInHand(List<Card> cardInHand) {
        this.cardInHand = cardInHand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public ExchangeRelationShip getRelationship() {
        return relationship;
    }

    public void setRelationship(ExchangeRelationShip relationship) {
        this.relationship = relationship;
    }

    @Override
    public int compareTo(Player player) {
        return this.point <= player.point ? 1 : -1;
    }

    public ShowDown getShowdown() {
        return showdown;
    }

    public void setShowdown(ShowDown showdown) {
        this.showdown = showdown;
    }

    private Player chooseExchangePlayer(List<Player> players, Player player) {
        if (player.getRelationship().getExchangePlayer() != null) {
            return null;
        }
        List<Player> chooseList = new ArrayList<>();
        chooseList.addAll(players);
        chooseList.remove(player);
        //如果是已經有建立關係的不選
        List<Player> hasNotAlreadyExchanged = chooseList.stream().filter(e -> e.getRelationship().getExchangePlayer() == null).collect(Collectors.toList());
        if (hasNotAlreadyExchanged.size() == 0) {
            return null;
        }

        Collections.shuffle(hasNotAlreadyExchanged);
        System.out.print(player.getName() + "選擇可以交換的玩家");
        hasNotAlreadyExchanged.forEach(e -> System.out.print(e.getName() + " "));
        System.out.println(" ");

        Player exchangePlayer = hasNotAlreadyExchanged.get(0);
        //設定關係
        player.getRelationship().setExchangePlayer(exchangePlayer);
        exchangePlayer.getRelationship().setExchangePlayer(player);
        return exchangePlayer;
    }
}
