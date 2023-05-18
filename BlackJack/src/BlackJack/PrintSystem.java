package BlackJack;

import java.util.List;

import models.Gameplayer;
import utils.AnsiConsole;
import utils.Line;

public class PrintSystem {

	// 진행 과정(로그) 출력 메서드
	public void printGameLog() {
		
		GameLogService logService = new GameLogService();
		List<String> logList = logService.loadProgress();
		
		for (String string : logList) {
			System.out.println(string);
		}
	}

	// 테이블 상황 출력 메서드
	public void printTable(Gameplayer player, Gameplayer dealer) {
		
		System.out.println(AnsiConsole.BLACK(Line.dLine(60)));
		System.out.println(AnsiConsole.PURPLE("\t\t\t     <딜러 테이블>  " + dealer.getPlayerScore()+"점"));
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < dealer.getPlayerCardList().size(); j++) {
				System.out.print(dealer.getPlayerCardList().get(j).getCardImage()[i] + "   ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println(AnsiConsole.BLACK(Line.sLine(60)));
		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < player.getPlayerCardList().size(); j++) {
				System.out.print(player.getPlayerCardList().get(j).getCardImage()[i] + "   ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println(AnsiConsole.YELLOW("\t\t\t   <플레이어 테이블>  " + player.getPlayerScore()+"점"));
		System.out.println(AnsiConsole.BLACK(Line.dLine(60)));
	}

	// 결과 출력 메서드
	public void printPlayResult(Gameplayer player, Gameplayer dealer) {
		if (player.getPlayerScore() > 21) {
			this.printTable(player, dealer);
			System.out.println();
			System.out.println(AnsiConsole.YELLOW("\t\t     플레이어 점수 : " + player.getPlayerScore()) + 
					AnsiConsole.PURPLE("\t 딜러 점수 : " + dealer.getPlayerScore()));
			System.out.println();
			System.out.println(AnsiConsole.RED("\t\t\t       플레이어 버스트"));
			System.out.println();
			System.out.println(AnsiConsole.RED("\t\t\t\t    패배"));
		} else if (dealer.getPlayerScore() > 21) {
			System.out.println();
			System.out.println(AnsiConsole.YELLOW("\t\t     플레이어 점수 : " + player.getPlayerScore()) + 
					AnsiConsole.PURPLE("\t 딜러 점수 : " + dealer.getPlayerScore()));
			System.out.println("\t\t\t       딜러 버스트!");
			System.out.println();
			System.out.println(AnsiConsole.YELLOW("\t\t\t\t    승리!"));
		} else if (player.getPlayerScore() > dealer.getPlayerScore()) {
			this.printTable(player, dealer);
			System.out.println();
			System.out.println(AnsiConsole.YELLOW("\t\t     플레이어 점수 : "+ player.getPlayerScore()) + 
					AnsiConsole.PURPLE("\t 딜러 점수 : " + dealer.getPlayerScore()));
			System.out.println();
			System.out.println(AnsiConsole.YELLOW("\t\t\t\t    승리"));
		} else if (player.getPlayerScore() == dealer.getPlayerScore()) {
			this.printTable(player, dealer);
			System.out.println();
			System.out.println(AnsiConsole.YELLOW("\t\t     플레이어 점수 : " + player.getPlayerScore()) + 
					AnsiConsole.PURPLE("\t 딜러 점수 : " + dealer.getPlayerScore()));
			System.out.println();
			System.out.println(AnsiConsole.BLACK("\t\t\t\t    무승부"));
		} else if (player.getPlayerScore() < dealer.getPlayerScore()) {
			this.printTable(player, dealer);
			System.out.println();
			System.out.println(AnsiConsole.YELLOW("\t\t     플레이어 점수 : " + player.getPlayerScore()) + 
					AnsiConsole.PURPLE("\t 딜러 점수 : " + dealer.getPlayerScore()));
			System.out.println();
			System.out.println(AnsiConsole.RED("\t\t\t\t    패배"));
		}
	}
	
}
