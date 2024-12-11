package model;

public class StatLogic {
	
	//filed
	
	//constructor
	
	//property
	
	//method
	public void correctPlus(Stat stat) {
		// 正答数を増加
		stat.setCorrect(stat.getCorrect()+1);
	}
	
	public void wrongPlus(Stat stat) {
		// 誤答数を増加
		stat.setWrong(stat.getWrong()+1);
	}

}
