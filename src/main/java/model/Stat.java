package model;

import java.io.Serializable;

public class Stat implements Serializable{
	
	//field
	private int correct; // 正答数
	private int wrong; // 誤答数
	
	//constructor
	public Stat() {
		correct=0;
		wrong=0;
	}
	
	//property
	public int getCorrect() {
		return this.correct;
	}
	public void setCorrect(int correct) {
		this.correct = correct;
	}
	
	public int getWrong() {
		return this.wrong;
	}
	public void setWrong(int wrong) {
		this.wrong = wrong;
	}
	
	//method

}
