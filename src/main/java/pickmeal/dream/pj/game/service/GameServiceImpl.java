package pickmeal.dream.pj.game.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;
import pickmeal.dream.pj.game.repository.GameDao;
import pickmeal.dream.pj.restaurant.domain.Restaurant;
import pickmeal.dream.pj.web.util.PresentTime;

@Service("GameService")
@Log
public class GameServiceImpl implements GameService{

	@Autowired
	GameDao gd;
	
	@Autowired
	PresentTime pt;

	public void insertLastGameRecord(long memberId, long resId) {
		// TODO Auto-generated method stub
		Timestamp now = pt.bringPresentTime();
		log.info("현재시간 to timestamp : " + now);
		System.out.println("insertLastGameRecord by ServiceImpl");
		gd.insertLastGameRecord(memberId, resId);
		
	}
	
	public int checkLastGameRecord(long memberId) {
		int diffOfDate = 0;
		
		diffOfDate = gd.checkLastGameRecord(memberId);
		
		return diffOfDate;
	}

	@Override
	public String[][] makeLadder(List<Restaurant> resList) {
		int numOfHorizonLines = 0; // 가로줄
		int numOfVerticalLines = resList.size(); // 세로줄
		int finalDepth = 0;
		
		System.out.println("가로줄 갯수 : " + numOfHorizonLines);
		
		
		if(numOfVerticalLines == 0) {
			System.out.println("no result : error"); // 0이면 안넘기기 때문에 출력이 되면 안됨. 
		} else if(numOfVerticalLines == 1) {
			numOfHorizonLines = 0;
		} else if(numOfVerticalLines == 2) { // 리스트가 2개인 경우
			numOfHorizonLines = (int)Math.floor(Math.random() * 3); // 가로줄은 0, 1, 2개 가능
		} else {
			int numForLines = (numOfVerticalLines * 2);
			numOfHorizonLines = (int)Math.floor(Math.random() * numForLines); // 가로줄은 0개 ~ 2n-1개 가능
		}
		// 여기까지 사다리 세로줄과 가로줄 세팅.
		finalDepth = numOfHorizonLines + 2;
		
		String[][] ladder = new String[finalDepth][numOfVerticalLines]; 
		
		for(int i=0; i<ladder.length; i++) {
			Arrays.fill(ladder[i], "0");
		} // 사다리 배열을 0으로 초기화
		
		
		//랜덤으로 가로줄 위치 세팅 가로줄의 왼쪽은 1, 오른쪽은 2로.
		for(int j=1; j<finalDepth-1; j++) {
			int horPosition;
			
			horPosition = (int)Math.floor(Math.random() * (numOfVerticalLines-1));
			
			ladder[j][horPosition] = "1";
			ladder[j][horPosition+1] = "2";
		}
		
		makePositionOfOX(ladder);
		makePositionOfResList(ladder, resList);

		// 사다리 출력 
		for(int i = 0; i<ladder.length; i++) {
			String[] inArr = ladder[i];
			for(int j = 0; j<inArr.length; j++) {
				System.out.print(inArr[j] + "    ");
			}
			System.out.println();
		}
		
		return ladder;
		
	}

	@Override
	public void makePositionOfOX(String[][] ladderList) {
		List<String> OXList = new ArrayList<String>();
		int colLength = ladderList[0].length; // 세로줄 갯수
		int positionOfAns = (int)Math.floor(Math.random() * colLength); // O 위치를 표시해주는 숫자.
		
		for(int i = 0; i<colLength; i++) {
			OXList.add("X");
		}// 전부 X를 넣고 
		OXList.set(positionOfAns, "O"); //정답 인덱스에 O 넣어준다. 
	
		// ** O와 X는 이미지로 넣어야 할 수도 있어서 위치만 잡아줄 수도 있음. 추후 체크
		
		for(int j = 0; j<colLength; j++) {
			ladderList[ladderList.length-1][j] = OXList.get(j); // OX를 사다리 제일 아래에 세팅(사다리 배열에 추가하는 것)
		}
	}
	
	public void makePositionOfResList(String[][] ladderList, List<Restaurant> resList) {
		int aNumberOfVerticalLines = ladderList[0].length;
		
		for(int i=0; i<aNumberOfVerticalLines; i++) {
			ladderList[0][i] = resList.get(i).getRName();
		}
		
	}
	
	public void makeRoute(String[][] ladder, Map<Integer, List<String>> setOfRoute) {
		String horSwitch = "0"; // 가로 이동을 구분해주는 스위치. 0 이면 가로 이동 가능.
		
		String[] inArr = ladder[0];
		int i = 0;
		int j = 0;
		
		
		int tempNum;
		for(j=0;j<inArr.length;j++) {
			List<String> sList = new ArrayList<String>();
			tempNum=j;
			i=0;
			while(i < ladder.length - 1) {
				if(ladder[i][j] == "1") {
					if(horSwitch == "0") {
	//					sList.add(Integer.toString(ladder[i][choice])+ Integer.toString(horSwitch));
						sList.add(ladder[i][j] + horSwitch);
						j = j + 1;
						horSwitch = "1";
						continue;
					} else {
						sList.add(ladder[i][j] + horSwitch);
						i++;
						horSwitch = "0";
						continue;
					}
				} else if(ladder[i][j] == "2") {
					if(horSwitch == "0") {
						sList.add(ladder[i][j] + horSwitch);
						j = j - 1;
						horSwitch = "1";
						continue;
					} else {
						sList.add(ladder[i][j] + horSwitch);
						i++;
						horSwitch = "0";
						continue;
					}
				} else {
					sList.add(ladder[i][j] + horSwitch);
					horSwitch = "0";
					if(i == ladder.length -1) {
						
					} else {
						i++;
					}
				}
			}
			sList.add(ladder[ladder.length-1][j] + horSwitch); // 마지막에 OX 처리해주기 위함.
			j = tempNum; // 이렇게 하면 안되나?
			setOfRoute.put(j, sList);
		}
		
		 // 컨트롤러에서 맵 객체를 생성하여 인자로 넣는거면 void로 해도 됨.
	}
	
	
}
