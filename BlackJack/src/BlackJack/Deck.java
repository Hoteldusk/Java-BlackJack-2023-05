package BlackJack;

import java.util.ArrayList;
import java.util.Collections;

import cardimageservice.CardImageService;
import models.Card;


public class Deck {
	private ArrayList<Card> cards;

	private CardImageService cardImgeService;

	public Deck() {
		cardImgeService = new CardImageService();

		String[] suits = { "클럽", "다이아", "하트", "스페이드" };
		String[] ranks = { "에이스", "2", "3", "4", "5", "6", "7", "8", "9", "10", "잭", "퀸", "킹" };

		cards = new ArrayList<Card>();
		for (String suit : suits) {
			for (int i = 0; i < ranks.length; i++) {
				int value = i + 1;
				if (value > 10) {
					value = 10;
				}
				cards.add(new Card(suit, ranks[i], value));
			}
		}
		shuffle();
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card drawCard() {
		Card card = cards.get(0);
		cardImgeService.drawCardRankSet(card);
		cardImgeService.drawCardSuitSet(card);
		cards.remove(0);
		return card;
	}

	public int getDeckSize() {
		return cards.size();
	}
}