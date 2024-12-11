package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ErrorMsgLogic;
import model.ErrorMsgModel;
import model.Question;
import model.QuestionLogic;
import model.Stat;
import model.StatLogic;

@WebServlet("/Calc")
public class Calc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 計算に使う数値の範囲
		final int NUMCOUNT = 2;
		final int MIN = 0;
		final int MAX = 9;
		
		// アプリケーションスコープをインスタンス化
		// 正誤記録は履歴として残すためアプリケーションスコープを使用
		ServletContext application = this.getServletContext();
		Stat stat = (Stat)application.getAttribute("stat");
		
		// 初回起動時のスコープnull確認と履歴用のインスタンス化
		// setAttribute
		if(stat == null) {
			stat = new Stat();
		}
		application.setAttribute("stat", stat);
		
		// 計算問題をインスタンス化
		// 計算ロジックをインスタンス化
		// 計算ロジックの計算問題生成メソッド実行
		Question question = new Question();
		QuestionLogic questionLogic = new QuestionLogic();
		questionLogic.create(question, NUMCOUNT, MIN, MAX);
		
		// セッションスコープをインスタンス化
		// 計算問題の表示から正誤結果表示まで保存するためセッションスコープを使用
		// setAttribute
		HttpSession session = request.getSession();
		session.setAttribute("question",question);
		
		// 問題表示画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 回答を取得
		request.setCharacterEncoding("UTF-8");
		String ans = (String)request.getParameter("ans");
		
		// 回答のnullチェック
		String errorMsg = "";
		if(ans == null || ans.length() == 0) {
			errorMsg = "答えが入力されていません";
			ErrorMsgModel errorMsgModel = new ErrorMsgModel();
			ErrorMsgLogic errorMsgLogic = new ErrorMsgLogic();
			errorMsgLogic.inputError(errorMsgModel, errorMsg);
			
			// リクエストスコープをインスタンス化
			// エラーメッセージをレスポンスするまで保存するためリクエストスコープを使用
			// setAttribute
			request.setAttribute("errorMsg", errorMsgModel);
			
		}else {
		
			// セッションスコープをインスタンス化
			// getAttribute
			HttpSession session = request.getSession();
			Question question = (Question)session.getAttribute("question");
			
			// 計算ロジックをインスタンス化
			// 計算ロジックの正誤結果メソッドを実行
			QuestionLogic questionLogic = new QuestionLogic();
			questionLogic.result(question, ans);
			
			// アプリケーションスコープをインスタンス化
			// getAttribute
			ServletContext application = this.getServletContext();
			Stat stat = (Stat)application.getAttribute("stat");
			
			// 履歴追加ロジックをインスタンス化
			// booleanにより履歴追加ロジックの正誤それぞれの結果追加メソッドを実行
			StatLogic statLogic = new StatLogic();
			if(question.isCorrect()) {
				statLogic.correctPlus(stat);
			}else {
				statLogic.wrongPlus(stat);
			}
		}

		// 問題表示画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
		dispatcher.forward(request, response);
	}
}
