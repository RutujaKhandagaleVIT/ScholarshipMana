package com.ischolar.ischolar.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ischolar.ischolar.entity.ApplicationForm;
import com.ischolar.ischolar.entity.User;
import com.ischolar.ischolar.helpe.Message;
import com.ischolar.ischolar.repo.ApplicationDao;
import com.ischolar.ischolar.repo.UserDao;

@Controller
@RequestMapping("/user")
public class DepartmentController {

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
	
	@GetMapping("department/{page}")
	public String department_dashboard(@PathVariable("page") Integer page, Model model, Principal principal) {
		model.addAttribute("title","Dashboard");
		User user = userDao.getUserByUserName(principal.getName());
		Pageable p = PageRequest.of(page, 5);
		Page<ApplicationForm> pages = this.apkDao.getByDepartment("Approved","Awaiting", p);
		System.out.println(pages.getTotalPages());
		model.addAttribute("list",pages);
		model.addAttribute("current", page);
		model.addAttribute("total", pages.getTotalPages());
		return "department/dashboard";
	}
	
	@GetMapping("/department")
	public String setting(Model model, Principal principal) {
		model.addAttribute("title","Profile");
		return "department/profile";
	}
	
	@PostMapping("update_department") 
	public String update_department(@ModelAttribute("User") User user,Model model, Principal principal,HttpSession session) {
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
			return  "department/profile";
		}
		return "department/profile";
	}
	
	@GetMapping("/{id}/application_action_department") 
	public String application_action_institute(@PathVariable("id") Integer id, Model model, Principal principal) {
		ApplicationForm f = apkDao.getById(id);
		model.addAttribute("p",f);
		return "department/applliaction_action_department";
	}
	
	@PostMapping("/application_appove_department") 
	public String applicationActionDepartment(@RequestParam("id") int id, @RequestParam("msg") String msg,@RequestParam("action") String action ,Model model, Principal principal) {
		try {
			boolean edit = false;
			
			if(action.equals("Sent_Back")) {
				edit = true;		
			}
			else {
				msg = "forwaded for approval";
			}
			apkDao.updateDepartment(action, id, edit, msg);
		}
		catch(Exception e) {
			e.printStackTrace();	
			return "redirect:department/0";
		}
		return "redirect:department/0";
	}
}
