package BlackJack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Card;
import models.Gameplayer;

public class GameLogService {

	private OutputStream os = null;
	private PrintWriter out = null;
	private List<String> log = null;
	
	public GameLogService() {
		log = new ArrayList<>();
	}
	
	public void resetLog() {
		try {
			os = new FileOutputStream("src/data/recentGameLog.txt");
			out = new PrintWriter(os);
			
			
		} catch (FileNotFoundException e) {
			System.out.println("저장할 파일을 찾을수 없습니다");
		}
		
		out.close();
	}
	
//	out.close();
	
	public void saveProgress(Gameplayer player) {

		try {
			os = new FileOutputStream("src/data/recentGameLog.txt", true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out = new PrintWriter(os);

		
		out.print(player.getName() + "이(가) 뽑은 카드 ");

		Card drawCard = player.getPlayerCardList().get(player.getPlayerCardList().size() - 1);

		out.println(drawCard.getSuit() + drawCard.getRank());

		// 뽑은 카드이미지 출력
		for (String image : drawCard.getCardImage()) {
			out.println(image);
		}

		// 2장 이상 일경우
		if (player.getPlayerCardList().size() > 1) {
			out.println(player.getName() + "이(가) 현재까지 뽑은 카드 ");
			// 현재 총 뽑은카드
			for (Card card : player.getPlayerCardList()) {
				out.print(card.getSuit() + card.getRank() + "\t");
			}
			out.println();
			// 카드 이미지 출력
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < player.getPlayerCardList().size(); j++) {
					out.print(player.getPlayerCardList().get(j).getCardImage()[i] + "   ");
				}
				out.println();
			}
		}
		// 점수 출력
		out.println(player.getName() + "의 현재 점수 " + player.getPlayerScore() + " 점.");
		out.println();
		
		out.close();
	}
	
	public List<String> loadProgress() {
		
		String logFile = "src/data/recentGameLog.txt";
		FileInputStream is = null;
		Scanner scan = null;
		
		try {
			is = new FileInputStream(logFile);
		} catch (FileNotFoundException e) {
			System.out.println("파일 없음");
			e.printStackTrace();
		}
		
		scan = new Scanner(is);
		
		while(scan.hasNext()) {
			String strLog = scan.nextLine();
			log.add(strLog);
		}
		
		scan.close();
		
		return log;
	}
}
