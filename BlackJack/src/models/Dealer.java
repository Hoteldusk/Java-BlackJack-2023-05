package models;

import java.util.ArrayList;
import java.util.List;

import utils.AnsiConsole;

public class Dealer implements Gameplayer{
	private List<Card> cardList;
//	private int cardCount;
	private int score;
	private String name;
	
	public Dealer() {
		cardList = new ArrayList<>();
//		cardCount = 0;
		score = 0;
		name = AnsiConsole.PURPLE("딜러");
	}
	
	// 카드 드로
	public void drawPlayerCard(Card card) {
		cardList.add(card);
		// 점수추가
		score += card.getValue();
	}
	
	// 카드 리스트 내보내기
	public List<Card> getPlayerCardList() {
		return cardList;
	}

	// 점수 내보내기
	public int getPlayerScore() {
		return score;
	}
	
	public String getName() {
		return name;
	}
}
