package BlackJack;

import models.Card;
import models.Gameplayer;

public class PrintSystem {

//	public void printCard(Card card) {
//		String cardSuit = card.getSuit();
//		String suit;
//		if (cardSuit.equals("Spades")) {//스페이드
//			suit = "♠";
//		} else if (cardSuit.equals("Hearts")) {//하트
//			
//		} else if (cardSuit.equals("Diamonds")) {//다이아
//			
//		} else {//클로버
//			
//		}
//	}
	public void printProgress(Gameplayer player) {
		// 진행상황 출력
		System.out.print(player.getName() + "이(가) 뽑은 카드 ");
		Card drawCard = player.getPlayerCardList().get(player.getPlayerCardList().size() - 1);
		System.out.println(drawCard.getSuit() + drawCard.getRank());

		// 뽑은 카드이미지 출력
		for (String image : drawCard.getCardImage()) {
			System.out.println(image);
		}

		// 2장 이상 일경우
		if (player.getPlayerCardList().size() > 1) {
			System.out.println(player.getName() + "이(가) 현재까지 뽑은 카드 ");
			// 현재 총 뽑은카드
			for (Card card : player.getPlayerCardList()) {
				System.out.print(card.getSuit() + card.getRank() + "\t");
			}
			System.out.println();
			// 카드 이미지 출력
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < player.getPlayerCardList().size(); j++) {
					System.out.print(player.getPlayerCardList().get(j).getCardImage()[i] + "   ");
				}
				System.out.println();
			}
		}
		// 점수 출력
		System.out.println(player.getName() + "의 현재 점수 " + player.getPlayerScore() + " 점.");
		System.out.println();
	}

	public void printTable(Gameplayer player, Gameplayer dealer) {
		System.out.println("=".repeat(100));

		System.out.println("<딜러 테이블>");
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < dealer.getPlayerCardList().size(); j++) {
				System.out.print(dealer.getPlayerCardList().get(j).getCardImage()[i] + "   ");
			}
			System.out.println();
		}
		System.out.println("<플레이어 테이블>");
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < player.getPlayerCardList().size(); j++) {
				System.out.print(player.getPlayerCardList().get(j).getCardImage()[i] + "   ");
			}
			System.out.println();
		}
		System.out.println("=".repeat(100));
	}
}
