package com.RamaIT.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.RamaIT.models.Counsellor;
import com.RamaIT.services.CounsellorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	CounsellorService counsellorService;

	@GetMapping("/login")
	String loadLoginPage(Model model) {
		model.addAttribute("counsellor", new Counsellor());
		return "login";
	}

	@PostMapping("/login")
	String loadLoginPage(Counsellor counsellor, Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// logic to check if user is valid
		Counsellor counsellorByEmailAndPswd = counsellorService
				.getCounsellorByEmailAndPswd(counsellor.getCounsellorEmail(), counsellor.getPassword());
		// if valid user add user to session
		if (counsellorByEmailAndPswd != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("counsellor", counsellorByEmailAndPswd);
			return "redirect:/dashboard";
		}
		// if invalid user
		model.addAttribute("message", "invalid credential please try agian");
		return "login";
	}

	@GetMapping("/register")
	String loadRegisterPage(Model model) {
		model.addAttribute("counsellor", new Counsellor());
		return "register";
	}

	@PostMapping("/register")
	String loadRegisterPage(Counsellor counsellor, Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// check if user already exists
		if (counsellorService.isCounsellorExistsForEmail(counsellor.getCounsellorEmail())) {
			model.addAttribute("message", "Details with this already exists, Try logging in");
			return "login";
		}
		// if not exists add counselor to database and redirect to login
		else {
			Counsellor registerdCounsellor = counsellorService.registerCounsellor(counsellor);
			HttpSession session = request.getSession(true);
			session.setAttribute("counsellor", registerdCounsellor);
		}
		// redirect it to login page with message user already exists

		return "redirect:dashboard";
	}

	@GetMapping("/logout")
	String logout(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession(true);
		session.removeAttribute("counsellor");
		
		return "redirect:login";
	}
}
