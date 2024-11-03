package com.RamaIT.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.RamaIT.enumerations.Course;
import com.RamaIT.enumerations.Mode;
import com.RamaIT.enumerations.Status;
import com.RamaIT.models.Counsellor;
import com.RamaIT.models.Enquiry;
import com.RamaIT.models.ViewEnqFilterRequest;
import com.RamaIT.repositories.EnquiryRepository;
import com.RamaIT.services.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class EnquiryController {
	@Autowired
	EnquiryService enquiryService;
	@Autowired
	EnquiryRepository enquiryRepo;

	@GetMapping("/add")
	public String addEnquiry(Model model) {
		model.addAttribute("enquiry", new Enquiry());
		model.addAttribute("courses", Course.values());
		model.addAttribute("modes", Mode.values());
		model.addAttribute("status", Status.values());
		return "addEnq";
	}

	@PostMapping("/add")
	public String addEnquiry(Enquiry enquiry, HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {

		Counsellor session_counsellor = (Counsellor) request.getSession().getAttribute("counsellor");
		boolean returnStatus = enquiryService.addEnquiry(enquiry, session_counsellor.getCounsellorId());
		if (returnStatus) {
			response.sendRedirect("/view");
		}
		return "addEnq";
	}

	@GetMapping("/edit/{id}")
	public String editEnquiry(@PathVariable(name = "id") Integer id, Model model) {
		Optional<Enquiry> enquirybyId = enquiryRepo.findById(id);
		model.addAttribute("enquiry", enquirybyId.get());
		model.addAttribute("courses", Course.values());
		model.addAttribute("modes", Mode.values());
		model.addAttribute("status", Status.values());
		return "addEnq";
	}

	@PostMapping("/view")
	public String viewPage(@ModelAttribute(name = "filterrequest") ViewEnqFilterRequest filteredRequest, Model model, HttpServletRequest request) {
		Counsellor session_counsellor = (Counsellor) request.getSession().getAttribute("counsellor");
		model.addAttribute("enquiries",
				enquiryService.viewFilteredEnquiry(filteredRequest, session_counsellor));
		model.addAttribute("filterrequest", filteredRequest);
		model.addAttribute("courses", Course.values());
		model.addAttribute("modes", Mode.values());
		model.addAttribute("status", Status.values());
		return "viewEnq";
	}

	@GetMapping("/view")
	public String loadviewPage(Model model, ViewEnqFilterRequest filteredRequest, HttpServletRequest request) {
		Counsellor session_counsellor = (Counsellor) request.getSession().getAttribute("counsellor");
		List<Enquiry> enquiries =enquiryService.viewAllEnquiry(session_counsellor.getCounsellorId());
		model.addAttribute("enquiries", enquiries);
		model.addAttribute("courses", Course.values());
		model.addAttribute("modes", Mode.values());
		model.addAttribute("status", Status.values());
		model.addAttribute("filterrequest", filteredRequest);
		return "viewEnq";
	}
	


}
