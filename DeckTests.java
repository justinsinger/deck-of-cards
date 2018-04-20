import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DeckTests {

    @Test
    public void deckInitializedWith52Cards() {
        Deck deck = new Deck();
        assertEquals(52, deck.size());
    }

    @Test
    public void deckInitializedWithCorrectCards() {
        Deck deck = new Deck();
        List<Card> deckCards = deck.draw(52);

        List<Card> expectedCards = new ArrayList<>(52);
        for (Suit suit : Suit.values()) {
            for (int number = Card.ACE; number <= Card.KING; number++) {
                expectedCards.add(new Card(suit, number));
            }
        }

        assertEquals(expectedCards, deckCards);
    }

    @Test
    public void deckInitializedWithSetOfCards() {
        Set<Card> setOfCards = new HashSet<>();
        setOfCards.add(new Card(Suit.CLUBS, 7));
        setOfCards.add(new Card(Suit.HEARTS, Card.QUEEN));

        Deck deck = new Deck(setOfCards);

        assertEquals(2, deck.size());

        List<Card> deckCards = deck.draw(2);

        assertEquals(setOfCards, new HashSet<>(deckCards));
    }

    @Test
    public void drawReturnsAndRemovesTopCard() {
        Deck deck = new Deck();
        Card card = deck.draw();

        assertEquals(new Card(Suit.HEARTS, Card.ACE), card);
        assertEquals(51, deck.size());
    }

    @Test
    public void draw2ReturnsAndRemovesTopCards() {
        Deck deck = new Deck();
        List<Card> cards = deck.draw(2);

        assertEquals(new Card(Suit.HEARTS, Card.ACE), cards.get(0));
        assertEquals(new Card(Suit.HEARTS, 2), cards.get(1));
        assertEquals(50, deck.size());
    }

    @Test
    public void drawMoreThanDeckHasReturnsAndRemovesAllCards() {
        List<Card> listOfCards = new ArrayList<>();
        listOfCards.add(new Card(Suit.CLUBS, 7));
        listOfCards.add(new Card(Suit.HEARTS, Card.QUEEN));

        Deck deck = new Deck(listOfCards);
        List<Card> cards = deck.draw(3);

        assertEquals(2, cards.size());
        assertEquals(new Card(Suit.CLUBS, 7), cards.get(0));
        assertEquals(new Card(Suit.HEARTS, Card.QUEEN), cards.get(1));
        assertEquals(0, deck.size());
    }

    @Test
    public void insertAddsCard() {
        Deck deck = new Deck(new ArrayList<>());
        deck.insert(new Card(Suit.HEARTS, 2));

        assertEquals(1, deck.size());

        Card card = deck.draw();

        assertEquals(new Card(Suit.HEARTS, 2), card);
    }

    @Test
    public void insertAddsCardToBottom() {
        Deck deck = new Deck();
        deck.insert(new Card(Suit.HEARTS, 2));

        assertEquals(53, deck.size());

        List<Card> cards = deck.draw(53);

        assertEquals(0, deck.size());
        assertEquals(new Card(Suit.HEARTS, 2), cards.get(52));
    }

    @Test
    public void insertAllAddsCards() {
        List<Card> listOfCards = new ArrayList<>();
        listOfCards.add(new Card(Suit.CLUBS, 7));
        listOfCards.add(new Card(Suit.HEARTS, Card.QUEEN));

        Deck deck = new Deck(new ArrayList<>());
        deck.insertAll(listOfCards);

        assertEquals(2, deck.size());

        List<Card> cards = deck.draw(2);

        assertEquals(listOfCards, cards);
    }

    @Test
    public void insertAllAddsCardToBottom() {
        List<Card> listOfCards = new ArrayList<>();
        listOfCards.add(new Card(Suit.CLUBS, 7));
        listOfCards.add(new Card(Suit.HEARTS, Card.QUEEN));

        Deck deck = new Deck();
        deck.insertAll(listOfCards);

        assertEquals(54, deck.size());

        List<Card> cards = deck.draw(54);

        assertEquals(0, deck.size());
        assertEquals(listOfCards, cards.subList(52, 54));
    }

    @Test
    public void shuffleChangesOrdering() {
        Deck deck = new Deck();

        // There is a chance that, due to the randomness of Collections.shuffle, the shuffled deck will be in the same
        // order as it was before it was shuffled. However, there are 52! permutations of a standard deck, which is
        // equal to 8.06e+67. On average, once every 8.06e+67 runs, this test will fail.
        deck.shuffle();

        assertNotEquals(new Deck(), deck);
    }

    @Test
    public void shuffleWithCustomMethodChangesOrdering() {
        List<Card> listOfCards = new ArrayList<>();
        listOfCards.add(new Card(Suit.CLUBS, 7));
        listOfCards.add(new Card(Suit.HEARTS, Card.QUEEN));

        Deck deck = new Deck(listOfCards);
        deck.shuffle(cards -> {
            Card temp = cards.get(0);
            cards.set(0, cards.get(1));
            cards.set(1, temp);
        });

        assertEquals(2, deck.size());

        List<Card> cards = deck.draw(2);

        assertEquals(listOfCards.get(0), cards.get(1));
        assertEquals(listOfCards.get(1), cards.get(0));
    }
}
