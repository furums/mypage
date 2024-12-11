package model;

public class QuestionLogic {
	
	//field
	
	//constructor
	
	//property
	
	//method
	public void create(Question question, int numsCount, int min, int max) {
		
		// 配列をインスタンス化
		// 計算に使う数値をランダム生成して配列に格納
		int[] nums = new int[numsCount];
		for(int i = 0; i < numsCount; i++) {
			nums[i] = new java.util.Random().nextInt(10);
		}
		
		// 計算して結果をset
		int result = nums[0];
		for(int i = 1; i < nums.length; i++) {
			result += nums[i];
		}
		question.setCorrectAns(result);
		
		// 計算問題(表示用)の文字列をset
		String strQuestion = "";
		for(int i = 0; i < nums.length; i++) {
			// 三項条件演算子
			strQuestion += nums[i] + (i == nums.length - 1? " = ":" + ");
		}
		question.setStrQuestion(strQuestion);
		
	}
	
	public void result(Question question, String userAns) {
		
		// 回答をset
		question.setUserAns(userAns);
		
		// 文字列を数値に変換
		// 答えを比較しbooleanをセット
		int ans = Integer.parseInt(userAns);
		question.setCorrect(question.getCorrectAns() == ans);
	}
}
