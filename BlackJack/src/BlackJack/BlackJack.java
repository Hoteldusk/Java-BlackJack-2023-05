package BlackJack;

import java.util.Scanner;

import models.Dealer;
import models.Gameplayer;
import models.Player;
import utils.AnsiConsole;
import utils.Line;

public class BlackJack {
	private Deck deck;
	private Scanner scanner;
	private PrintSystem print;
	private GameLogService log;

	public BlackJack() {
		deck = new Deck();
		scanner = new Scanner(System.in);
		print = new PrintSystem();
	}

	public void play() {

		while (true) {
			System.out.println("블랙잭 게임");
			System.out.println("1. 블랙잭 시작" + "\n" + "2. 블랙잭 규칙" + "\n" + "3. 종료");
			System.out.print("번호 선택 >>>> ");
			Scanner scan = new Scanner(System.in);
			String select = scan.nextLine();
			System.out.println();

			try {
				if (!select.equals("1") && !select.equals("2") && !select.equals("3")) {
					System.out.println("1 ~ 3 까지 번호만 입력 하세요 !!!!!!!");
					continue;
				}
				if (select.equals("1")) {
					break;

				} else if (select.equals("2")) {
					System.out.println("블랙잭 게임규칙");
					System.out.println("1. 게임시작과 함께 카드 2장을 받습니다");
					System.out.println("2. 추가로 카드를 받을 수 있습니다");
					System.out.println("3. 카드의 합이 21에 가까우면 승리, 21을 초과하면 패배");
					System.out.println();
					continue;
				} else if (select.equals("3")) {
					System.out.println("종료합니다.");
					System.out.println();
					return;
				}
			} catch (Exception e) {
			}
		}

		while (true) {

			System.out.println(AnsiConsole.BLACK(Line.dLine(60)));
			System.out.println(AnsiConsole.CYAN(Line.dollar(18) + "블랙잭 게임을 시작합니다" + Line.dollar(17)));
			System.out.println(AnsiConsole.BLACK(Line.dLine(60)));

			if (deck.getDeckSize() <= 10) {
				System.out.println("덱의 매수가 10장 이하이므로 새로운 덱이 생성됩니다");
				deck = new Deck();
			}

			log = new GameLogService();
			Gameplayer player = new Player();
			Gameplayer dealer = new Dealer();

			log.resetLog();
			// 플레이어 드로우
			player.drawPlayerCard(deck.drawCard());
			log.saveProgress(player);

			// 플레이어 드로우
			player.drawPlayerCard(deck.drawCard());
			log.saveProgress(player);

			// 딜러 드로우
			dealer.drawPlayerCard(deck.drawCard());
			log.saveProgress(dealer);

			// 플레이어가 카드를 더 뽑을지 묻는다.
			while (player.getPlayerScore() < 21) {
				print.printTable(player, dealer);
				System.out.println();
				System.out.println(AnsiConsole.BLACK(Line.dLine(60)));
				System.out.print(AnsiConsole.BLUE("\t\t\t카드를 더 뽑으시겠습니까? (y/n) >> "));
				String answer = scanner.nextLine();
				System.out.println(AnsiConsole.BLACK(Line.dLine(60)));
				for (int i = 0; i < 10; i++) {
					System.out.println("");
				}
				try {
					if (!answer.equals("y") && !answer.equals("n")) {
						System.out.println(AnsiConsole.RED("\t\t\t y 또는 n 만 입력하세요"));
						System.out.println();
						continue;
					} else if (answer.equals("y")) {
						player.drawPlayerCard(deck.drawCard());
						log.saveProgress(player);
					} else if (answer.equals("n")) {
						break;
					}
				} catch (Exception e) {
					System.out.println(AnsiConsole.RED("\t\t\t y 또는 n 만 입력하세요"));
				}

			}

			// 딜러가 카드를 더 뽑는다.
			while (dealer.getPlayerScore() < 17) {
				dealer.drawPlayerCard(deck.drawCard());
				log.saveProgress(dealer);
			}

			// 결과를 비교한다.
			print.printPlayResult(player, dealer);

			// 다시 게임을 할지 묻는다.
			while (true) {
				System.out.println();
				System.out.print(AnsiConsole.BLUE("\t\t게임을 다시 하겠습니까? (y/n) 로그출력(l) >> "));
				String answer = scanner.nextLine();
				System.out.println();
				try {
					if (!answer.equals("y") && !answer.equals("n") && !answer.equals("l")) {
						System.out.println(AnsiConsole.RED("\t\t\t     'y' 또는 'n' 또는 'l'만 입력하세요"));
						continue;
					} else if (answer.equals("n")) {
						System.out.println(AnsiConsole.BLUE("\t\t\t      게임을 종료합니다"));
						return;
					} else if (answer.equals("l")){
						print.printGameLog();
					} else {
						break;
					}
				} catch (Exception e) {
					System.out.println(AnsiConsole.RED("\t\t\t     'y' 또는 'n' 또는 'l' 만 입력하세요"));
					continue;
				}
			}
		}
	}
}
