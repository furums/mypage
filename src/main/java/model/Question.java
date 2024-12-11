package model;

import java.io.Serializable;

public class Question implements Serializable{
	
	//field
	private int correctAns; // 正しい計算の応え
	private String userAns; // 入力者の回答
	private boolean isCorrect; // 正誤判定
	private String strQuestion; // 計算問題(表示用)
	
	//constructor
	public Question() {}
	
	//property
	public int getCorrectAns() {
		return this.correctAns;
	}
	public void setCorrectAns(int correctAns) {
		this.correctAns = correctAns;
	}
	
	public String getUserAns() {
		return this.userAns;
	}
	public void setUserAns(String userAns) {
		this.userAns = userAns;
	}
	
	public boolean isCorrect() {
		return this.isCorrect;
	}
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	public String getStrQuestion() {
		return this.strQuestion;
	}
	public void setStrQuestion(String strQuestion) {
		this.strQuestion = strQuestion;
	}
	
	//method

}
