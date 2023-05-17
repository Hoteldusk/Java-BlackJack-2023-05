package models;

public class Card {
    private String suit;
    private String rank;
    private int value;
    private String[] cardImage;

    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
        this.cardImage = null;
    }

    public String getSuit() {
        return suit;
    }
    public String getRank() {
        return rank;
    }
    public int getValue() {
        return value;
    }

    public void setCardImage(String[] cardImage) {
        this.cardImage = cardImage;
    }
    public String[] getCardImage() {
        return cardImage;
    }
}