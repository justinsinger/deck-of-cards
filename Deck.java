import com.sun.org.apache.xpath.internal.operations.Equals;

import java.util.*;
import java.util.function.Consumer;

public class Deck {
    // Head of list represents top of deck, tail represents bottom
    LinkedList<Card> cards;

    /**
     * Initialize a standard 52-card Deck
     */
    public Deck() {
        cards = new LinkedList<>();
        for (Suit suit : Suit.values()) {
            for (int number = Card.ACE; number <= Card.KING; number++) {
                cards.add(new Card(suit, number));
            }
        }
    }

    /**
     * Initialize a Deck with the cards in the Collection setOfCards
     */
    public Deck(Collection<? extends Card> setOfCards) {
        cards = new LinkedList<>(setOfCards);
    }

    /**
     * Returns number of cards in deck
     */
    public int size() {
        return cards.size();
    }

    /**
     * Returns and removes the top card from the deck
     */
    public Card draw() {
        return cards.remove();
    }

    /**
     * Returns and removes the top n cards from the deck. If number of cards left in deck is fewer than n, all cards
     * will be returned and removed
     */
    public List<Card> draw(int n) {
        int cardsToDraw = Math.min(n, cards.size());
        List<Card> drawn = new ArrayList<>(cardsToDraw);
        for (int i = 0; i < cardsToDraw; i++) {
            drawn.add(cards.remove());
        }
        return drawn;
    }

    /**
     * Inserts card onto the bottom of the deck
     */
    public void insert(Card card) {
        cards.add(card);
    }

    /**
     * Inserts colection of cards onto the bottom of the deck
     */
    public void insertAll(Collection<? extends Card> setOfCards) {
        cards.addAll(setOfCards);
    }

    /**
     * Shuffle the deck
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Shuffle the deck using specified Consumer block
     */
    public void shuffle(Consumer<LinkedList<Card>> block) {
        block.accept(cards);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deck deck = (Deck) o;

        return cards != null ? cards.equals(deck.cards) : deck.cards == null;
    }

    @Override
    public int hashCode() {
        return cards != null ? cards.hashCode() : 0;
    }
}
