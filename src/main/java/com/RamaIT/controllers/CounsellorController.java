package com.RamaIT.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.RamaIT.models.Counsellor;
import com.RamaIT.services.CounsellorService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CounsellorController {
	@Autowired
	CounsellorService counsellorService;
	@GetMapping("/dashboard")
	public String viewDashboard(HttpServletRequest request,Model model) {
		Counsellor session_counsellor = (Counsellor) request.getSession().getAttribute("counsellor");
		model.addAttribute("dashboardResponse", counsellorService.getDashboardInfo(session_counsellor.getCounsellorId()));
		
		return "dashboard";
	}

}
