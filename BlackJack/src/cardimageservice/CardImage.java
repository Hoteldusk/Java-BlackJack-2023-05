package cardimageservice;

public class CardImage {
    private String[] cardImage = null;

    public CardImage() {
        this.cardImage = new String[6];
    }

    public void setCardImage(String[] cardImage) {
        this.cardImage = cardImage;
    }

    public String[] getCardImage() {
        return cardImage;
    }
}
