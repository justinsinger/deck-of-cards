public enum Suit {
    HEARTS ("Hearts"),
    DIAMONDS ("Diamonds"),
    CLUBS ("Clubs"),
    SPADES ("Spades");

    private final String NAME;
    Suit(String name) {
        NAME = name;
    }

    @Override
    public String toString() {
        return NAME;
    }
}
