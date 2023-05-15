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
		System.out.println("블랙잭 게임을 시작합니다!");
		while (true) {
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
			while (true) {
				print.printTable(player, dealer);
				System.out.print("카드를 더 뽑으시겠습니까? (y/n) ");
				String answer = scanner.nextLine();
				if (answer.equals("y")) {
					player.drawPlayerCard(deck.drawCard());
					print.printProgress(player);

					if (player.getPlayerScore() > 21) {
						print.printTable(player, dealer);
						System.out.println("플레이어 버스트!");
						System.out.println("패배!");
						return;
					}
				} else {
					break;
				}
			}

			// 딜러가 카드를 더 뽑는다.
			while (dealer.getPlayerScore() < 17) {
				dealer.drawPlayerCard(deck.drawCard());
				print.printProgress(dealer);
			}

			// 결과를 비교한다.
			if (dealer.getPlayerScore() > 21) {
				print.printTable(player, dealer);
				System.out.println("딜러 버스트!");
				System.out.println("승리!");
			} else if (player.getPlayerScore() > dealer.getPlayerScore()) {
				print.printTable(player, dealer);
				System.out.println("플레이어 점수 : " + player.getPlayerScore() + "\t딜러 점수 : " + dealer.getPlayerScore());
				System.out.println("승리!");
			} else if (player.getPlayerScore() == dealer.getPlayerScore()) {
				print.printTable(player, dealer);
				System.out.println("플레이어 점수 : " + player.getPlayerScore() + "\t딜러 점수 : " + dealer.getPlayerScore());
				System.out.println("무승부");
			} else {
				print.printTable(player, dealer);
				System.out.println("플레이어 점수 : " + player.getPlayerScore() + "\t딜러 점수 : " + dealer.getPlayerScore());
				System.out.println("패배!");
			}

			// 다시 게임을 할지 묻는다.
			System.out.print("게임을 다시 하겠습니까? (y/n) ");
			String answer = scanner.nextLine();
			if (!answer.equals("y")) {
				break;
			}

		}
	}
}