package templete;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Hand<Card> implements Iterable<Card> {

  private final List<Card> cards = new ArrayList<>();

  public void addCard(Card card) {
    cards.add(card);
  }

  public Card get(int index) {
    return cards.get(index);
  }

  public Card play(int index) {
    return cards.remove(index);
  }

  public int size() {
    return cards.size();
  }

  @Override
  public Iterator<Card> iterator() {
    return cards.iterator();
  }

  public List<Card> getCards() {
    return cards;
  }
}
