package uno;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public abstract class Player extends base.Player<Card> {

    public abstract TurnMove takeTurn(Card topCard);

}
