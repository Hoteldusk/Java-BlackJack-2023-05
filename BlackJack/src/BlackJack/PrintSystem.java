package BlackJack;

import models.Card;
import models.Gameplayer;

public class PrintSystem {

	// 진행 과정(로그) 출력 메서드
	public void printProgress(Gameplayer player) {
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

	// 테이블 상황 출력 메서드
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

	// 결과 출력 메서드
	public void printPlayResult(Gameplayer player, Gameplayer dealer) {
		if (player.getPlayerScore() > 21) {
			this.printTable(player, dealer);
			System.out.println("플레이어 버스트!");
			System.out.println("패배!");
		} else if (dealer.getPlayerScore() > 21) {
			this.printTable(player, dealer);
			System.out.println("딜러 버스트!");
			System.out.println("승리!");
		} else if (player.getPlayerScore() > dealer.getPlayerScore()) {
			this.printTable(player, dealer);
			System.out.println("플레이어 점수 : " + player.getPlayerScore() + "딜러 점수 : " + dealer.getPlayerScore());
			System.out.println("승리!");
		} else if (player.getPlayerScore() == dealer.getPlayerScore()) {
			this.printTable(player, dealer);
			System.out.println("플레이어 점수 : " + player.getPlayerScore() + "딜러 점수 : " + dealer.getPlayerScore());
			System.out.println("무승부");
		} else if (player.getPlayerScore() < dealer.getPlayerScore()) {
			this.printTable(player, dealer);
			System.out.println("플레이어 점수 : " + player.getPlayerScore() + "딜러 점수 : " + dealer.getPlayerScore());
			System.out.println("패배!");
		}
	}
}
