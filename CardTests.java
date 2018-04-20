import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CardTests {

    @Test
    public void initializeCard() {
        Card card = new Card(Suit.DIAMONDS, 5);

        assertEquals(Suit.DIAMONDS, card.getSuit());
        assertEquals(5, card.getNumber());
    }

    @Test
    public void initializeCardWithNullSuit() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Card card = new Card(null, 5);
        });
        assertEquals("Suit of a Card object must not be null.", exception.getMessage());
    }

    @Test
    public void initializeCardWithAce() {
        Card card = new Card(Suit.HEARTS, Card.ACE);

        assertEquals(Suit.HEARTS, card.getSuit());
        assertEquals(1, card.getNumber());
    }

    @Test
    public void initializeCardWithNumberLowerThanAce() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Card card = new Card(Suit.CLUBS, 0);
        });
        assertEquals("Number of a Card object must be between 1 and 13. Recieved: 0", exception.getMessage());
    }

    @Test
    public void initializeCardWithKing() {
        Card card = new Card(Suit.SPADES, Card.KING);

        assertEquals(Suit.SPADES, card.getSuit());
        assertEquals(13, card.getNumber());
    }

    @Test
    public void initializeCardWithNumberHigherThanKing() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Card card = new Card(Suit.DIAMONDS, 14);
        });
        assertEquals("Number of a Card object must be between 1 and 13. Recieved: 14", exception.getMessage());
    }

    @Test
    public void initializeCardWithNumberHigherThanKingAndNullSuit() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Card card = new Card(null, 14);
        });
        assertEquals("Suit of a Card object must not be null.", exception.getMessage());
    }

    @Test
    public void printOutCard() {
        Card card = new Card(Suit.SPADES, Card.ACE);

        assertEquals("Ace of Spades", card.toString());
    }
}
