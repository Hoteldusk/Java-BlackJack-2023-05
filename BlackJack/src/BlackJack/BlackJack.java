package BlackJack;

import java.util.Scanner;

import models.Dealer;
import models.Gameplayer;
import models.Player;

public class BlackJack {
	private Deck deck;
	private Scanner scanner;

	public BlackJack() {
		deck = new Deck();
		scanner = new Scanner(System.in);
	}

	public void play() {
		while (true) {
			System.out.println("블랙잭 게임을 시작합니다!");
			
			if(deck.getDeckSize() <= 10) {
				System.out.println("덱의 매수가 10장 이하이므로 새로운 덱이 생성됩니다");
				deck = new Deck();
			}
			
			// 플레이어가 카드를 뽑는다.
			Gameplayer player = new Player();
			Gameplayer dealer = new Dealer();
			PrintSystem print = new PrintSystem();

			player.drawPlayerCard(deck.drawCard());
			print.printProgress(player);

			player.drawPlayerCard(deck.drawCard());
			print.printProgress(player);

			dealer.drawPlayerCard(deck.drawCard());
			print.printProgress(dealer);

			// 플레이어가 카드를 더 뽑을지 묻는다.
			while (player.getPlayerScore() < 21) {
				print.printTable(player, dealer);
				System.out.print("카드를 더 뽑으시겠습니까? (y/n) ");
				String answer = scanner.nextLine();
				if (answer.equals("y")) {
					player.drawPlayerCard(deck.drawCard());
					print.printProgress(player);
					print.printTable(player, dealer);
				}
			}

			// 딜러가 카드를 더 뽑는다.
			while (dealer.getPlayerScore() < 17) {
				dealer.drawPlayerCard(deck.drawCard());
				print.printProgress(dealer);
			}

			// 결과를 비교한다.
			if (player.getPlayerScore() > 21) {
				print.printTable(player, dealer);
				System.out.println("플레이어 버스트!");
				System.out.println("패배!");
			} else if (dealer.getPlayerScore() > 21) {
				print.printTable(player, dealer);
				System.out.println("딜러 버스트!");
				System.out.println("승리!");
			} else if (player.getPlayerScore() > dealer.getPlayerScore()) {
				print.printTable(player, dealer);
				System.out.println("플레이어 점수 : " + player.getPlayerScore() + "딜러 점수 : " + dealer.getPlayerScore());
				System.out.println("승리!");
			} else if (player.getPlayerScore() == dealer.getPlayerScore()) {
				print.printTable(player, dealer);
				System.out.println("플레이어 점수 : " + player.getPlayerScore() + "딜러 점수 : " + dealer.getPlayerScore());
				System.out.println("무승부");
			} else if (player.getPlayerScore() < dealer.getPlayerScore()) {
				print.printTable(player, dealer);
				System.out.println("플레이어 점수 : " + player.getPlayerScore() + "딜러 점수 : " + dealer.getPlayerScore());
				System.out.println("패배!");
			}

			// 다시 게임을 할지 묻는다.
			while (true) {
				System.out.print("게임을 다시 하겠습니까? (y/n) ");
				String answer = scanner.nextLine();
				try {
					if (!answer.equals("y") && !answer.equals("n")) {
						System.out.println("y 또는 n 만 입력하세요");
						continue;
					} else if (answer.equals("n")) {
						System.out.println("게임을 종료합니다");
						return;
					} else {
						break;
					}
				} catch (Exception e) {
					System.out.println("y 또는 n 만 입력하세요");
					continue;
				}
			}
		}
	}
}