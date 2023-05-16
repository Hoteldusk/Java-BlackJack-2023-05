package BlackJack;

import java.util.ArrayList;
import java.util.Collections;

import cardimageservice.CardImage;
import cardimageservice.CardImageService;
import models.Card;

import java.util.List;

public class Deck {
	private ArrayList<Card> cards;

	public Deck() {
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
		drawCardRankSet(card);
		drawCardSuitSet(card);
		cards.remove(0);
		return card;
	}

	private Card drawCardRankSet(Card card) {
		CardImageService cardService = new CardImageService();
		List<CardImage> cardImageList = cardService.getCardImageList();
		if (card.getRank().equals("잭")) {
			card.setCardImage(cardImageList.get(10).getCardImage());
		} else if (card.getRank().equals("퀸")) {
			card.setCardImage(cardImageList.get(11).getCardImage());
		} else if (card.getRank().equals("킹")) {
			card.setCardImage(cardImageList.get(12).getCardImage());
		} else {
			card.setCardImage(cardImageList.get(card.getValue() - 1).getCardImage());
		}

		return card;
	}

	private Card drawCardSuitSet(Card card) {
		String[] standardImges = card.getCardImage();
		String[] changeImges = new String[standardImges.length];

		for (int i = 0; i < standardImges.length; i++) {

			String standardImg = standardImges[i];

			if (card.getSuit().equals("클럽")) {
				changeImges[i] = standardImg.replace("&", "♣");
			} else if (card.getSuit().equals("스페이드")) {
				changeImges[i] = standardImg.replace("&", "♠");
			} else if (card.getSuit().equals("하트")) {
				changeImges[i] = standardImg.replace("&", "♥");
			} else {
				changeImges[i] = standardImg.replace("&", "◈");
			}
		}

		card.setCardImage(changeImges);

		return card;
	}

	public int getDeckSize() {
		return cards.size();
	}
}