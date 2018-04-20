
public class Card {
    public static final int ACE = 1;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;

    private Suit suit;
    private int number;

    public Card(Suit suit, int number) {
        if (suit == null) {
            throw new IllegalArgumentException("Suit of a Card object must not be null.");
        }
        else if (number < ACE || number > KING) {
            throw new IllegalArgumentException("Number of a Card object must be between 1 and 13. Recieved: " + number);
        }
        this.suit = suit;
        this.number = number;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        String numberName;
        switch (number) {
            case ACE:
                numberName = "Ace";
                break;
            case JACK:
                numberName = "Jack";
                break;
            case QUEEN:
                numberName = "Queen";
                break;
            case KING:
                numberName = "King";
                break;
            default:
                numberName = String.valueOf(number);
                break;
        }
        return numberName + " of " + suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (number != card.number) return false;
        return suit == card.suit;
    }

    @Override
    public int hashCode() {
        int result = suit.hashCode();
        result = 31 * result + number;
        return result;
    }
}
