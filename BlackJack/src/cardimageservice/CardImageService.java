package cardimageservice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardImageService {
    private List<CardImage> cardImageList;

    public CardImageService() {
        cardImageList = new ArrayList<>();
    }

    public void loadCardImage() {
        String cardFile = "src/carddata/cardData.txt";
        InputStream is = null;
        Scanner scan = null;

        try {
            is = new FileInputStream(cardFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        scan = new Scanner(is);

        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] card = line.split(",");
            CardImage cardImage = new CardImage();
            cardImage.setCardImage(card);

            cardImageList.add(cardImage);
        }
        scan.close();
    }
    public List<CardImage> getCardImageList() {
        if(cardImageList.isEmpty()) {
            this.loadCardImage();
            return cardImageList;
        }
        return cardImageList;
    }
}
