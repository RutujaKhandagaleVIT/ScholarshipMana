package com.ischolar.ischolar.controller;


import java.security.Principal;


import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ischolar.ischolar.entity.ApplicationForm;
import com.ischolar.ischolar.entity.User;
import com.ischolar.ischolar.helpe.Message;
import com.ischolar.ischolar.repo.ApplicationDao;
import com.ischolar.ischolar.repo.UserDao;


@Controller
@RequestMapping("/user")
public class StudentController {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ApplicationDao apkDao;
	
	
	@Autowired
	private BCryptPasswordEncoder passencoder;
	
	@ModelAttribute
	public void addCommon(Model model, Principal principal) {
		model.addAttribute("user", userDao.getUserByUserName(principal.getName()));
	}
	
	@GetMapping("student/{page}")
	public String student_dashboard(@PathVariable("page") Integer page, Model model, Principal principal) {
		model.addAttribute("title","Dashboard");
		User user = userDao.getUserByUserName(principal.getName());
		Pageable p = PageRequest.of(page, 5);
		Page<ApplicationForm> pages = this.apkDao.getByUserId(user.getId(), p);
		model.addAttribute("list",pages);
		model.addAttribute("current", page);
		model.addAttribute("total", pages.getTotalPages());
		return "student/dashboard";
	}
	
	@GetMapping("application")
	public String student_apk(Model model, Principal principal) {
		model.addAttribute("apk", new ApplicationForm());
		return "student/application";
	}
	
	@PostMapping("/application_submit")
	public String application_submit(@ModelAttribute("apk") ApplicationForm apk, Model model, HttpSession session, Principal principal) {
		try {
			apk.setInstitute("Awaiting");
			apk.setDdo("Awaiting");
			apk.setDepartment("Awaiting");
			apk.setMessage("forwaded for approval");
			apk.setEditable(true);
			User user = userDao.getUserByUserName(principal.getName());
			apk.setUser(user);
			user.getApplications().add(apk);
			userDao.save(user);
			session.setAttribute("message", new Message( "alert-success","Application Submited Successfuly !!"));
		}
		catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("message", new Message( "alert-danger","Something Whent Wrong !! "+e.getMessage()));
			return "student/application";
		}
		return "student/application";
	}
	
	@GetMapping("/profile")
	public String setting(Model model, Principal principal) {
		model.addAttribute("title","Profile");
		return "student/profile";
	}
	
	@PostMapping("update_profile") 
	public String update_profile(@ModelAttribute("User") User user,Model model, Principal principal,HttpSession session) {
		try {
			User old = userDao.getUserByUserName(principal.getName());
			old.setFirstName(user.getFirstName().toUpperCase());
			old.setLastName(user.getLastName().toUpperCase());
			old.setMiddleName(user.getMiddleName().toUpperCase());
			old.setPassword(passencoder.encode(user.getPassword()));
			User res = userDao.save(old);
			session.setAttribute("message", new Message("alert-success", "Update Successfull !!"));
		}
		catch(Exception e){
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message( "alert-danger","Something Whent Wrong !! "+e.getMessage()));
			return  "student/profile";
		}
		return "student/profile";
	}
	
	@GetMapping("/{id}/application") 
	public String show_application(@PathVariable("id") Integer id, Model model, Principal principal) {
		ApplicationForm f = apkDao.getById(id);
		model.addAttribute("p",f);
		return "student/show_application";
	}
	
	@GetMapping("/{id}/edit")
	public String edit_apk(@PathVariable("id") Integer id, Model model, Principal principal) {
		ApplicationForm apk = apkDao.getById(id);
		model.addAttribute("apk",apk);
		return "student/edit_apk";
	}
	
	@PostMapping("/application_update")
	public String application_update(@ModelAttribute("apk") ApplicationForm apk, Model model, Principal principal) {
		int id = apk.getId();
		apk.setInstitute("Awaiting");
		apk.setDdo("Awaiting");
		apk.setDepartment("Awaiting");
		apk.setEditable(true);
		apk.setMessage("forwaded for approval");
		User user = userDao.getUserByUserName(principal.getName());
		apk.setUser(user);
		apkDao.save(apk);
		return "redirect:/user/"+id+"/application";
	}
}
