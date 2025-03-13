package templete;

import java.util.List;

public abstract class AbstractPlayer<Card> {

  protected Hand<Card> hand = new Hand<>();
  private String name;

  public String getName() {
    return name;
  }

  public List<Card> getCardsInHand() {
    return hand.getCards();
  }

  public void setCardsInHand(List<Card> cardsInHand) {
    hand.getCards().clear();
    hand.getCards().addAll(cardsInHand);
  }

  public void nameHimself(String name) {
    this.name = name;
    System.out.println(" name :" + name);
  }

  public void addHandCard(Card card) {
    this.hand.addCard(card);
  }

  public void showCardsInHand() {
    System.out.println(this.getName() + "手上的牌:");
    for (Card card : this.getCardsInHand()) {
      System.out.println(card.toString());
    }
  }

  public void gainCard(Card card) {
    hand.addCard(card);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof AbstractPlayer) {
      AbstractPlayer player = (AbstractPlayer) obj;
      return this.getName().equals(player.getName());
    }
    return false;
  }
}
