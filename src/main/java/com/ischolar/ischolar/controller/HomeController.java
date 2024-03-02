package com.ischolar.ischolar.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ischolar.ischolar.entity.User;
import com.ischolar.ischolar.helpe.Message;
import com.ischolar.ischolar.repo.UserDao;

@Controller
public class HomeController {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder passencoder;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title" , "Home");
//		System.out.print("Home Page Called !!");
		return "home";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title" , "Sign Up");
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@PostMapping("/register_user") 
	public String register(@ModelAttribute("user") User user, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model, HttpSession session) {
		try {
			if(!agreement) {
				System.out.println("Not Signed User Agreemet");
				throw new Exception("Terms and Condition not Accepted !!");
			}
			user.setRole("ROLE_USER");
			user.setFirstName(user.getFirstName().toUpperCase());
			user.setLastName(user.getLastName().toUpperCase());
			user.setMiddleName(user.getMiddleName().toUpperCase());
			user.setPassword(passencoder.encode(user.getPassword()));
			User res = userDao.save(user);
//			System.out.println(res);
			session.setAttribute("message", new Message("alert-success", "Registration Successfull !!"));
		}
		catch(Exception e){
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message( "alert-danger","Something Whent Wrong !! "+e.getMessage()));
			return "signup";
		}
		
		return "login";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("title", "Login");
		return "login";
	}
	
	@GetMapping("/user/dashboard")
	public String redirectUser(Model model, Principal principal) {
		String type = userDao.getUserByUserName(principal.getName()).getType();
		System.out.println(type);
		if(type.equals("ddo")) {
			return "redirect:ddo/0";
		}
		if(type.equals("department")) {
			return "redirect:department/0";
		}
		if(type.equals("institute")) {
			return "redirect:institute/0";
		}
		return "redirect:student/0";
	}
}
