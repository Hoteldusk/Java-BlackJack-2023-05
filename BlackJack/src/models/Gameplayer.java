package models;

import java.util.List;

public interface Gameplayer {
	
	public void drawPlayerCard(Card card);
	
	public List<Card> getPlayerCardList();
	
	public int getPlayerScore();
	
	public String getName();
}
