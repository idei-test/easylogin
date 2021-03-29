package com.example.easylogin.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;

import com.example.easylogin.model.dao.UserRepository;
import com.example.easylogin.model.entity.User;
// リクエスト　レスポンス　を処理
@Controller
public class LoginController {
// リポジトリインターフェースを実装させる
	@Autowired
	UserRepository userRepos;
	// トップページをリクエストされたら/indexを返す
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	// /loginをリクエストされたら以下の処理する
	@RequestMapping("/login")
	//@ResponseBody
	// 処理に必要なpostを引数で受け取る　htmlに返すための型を用意
	public String login(
			@RequestParam("userName")String userName,
			@RequestParam("password")String password,
			Model m) {
		
		String message = "Welcom!";
		// 実体化したインターフェースに定義した自己定義メソッドを呼び利用する
		List<User> users = userRepos.findByUserNameAndPassword(userName,password);
		// 中身があるなら（DBにあったなら値を代入）
		if(users.size()>0) {
			User user = users.get(0);
			message += user.getFullName();
		}else {
			message += "guest";
		}
		// 表示するまえにhtmlの変数messageに値を入れる
		m.addAttribute("message",message);
		// /loginを返す
		return "login";
	}
}
