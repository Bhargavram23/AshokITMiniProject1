package com.RamaIT.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.RamaIT.models.Counsellor;
import com.RamaIT.models.Enquiry;
import com.RamaIT.models.ViewEnqFilterRequest;
import com.RamaIT.repositories.EnquiryRepository;
import com.RamaIT.repositories.counsellorRepository;

@Service
public class EnquiryServiceImpl implements EnquiryService {
	@Autowired
	EnquiryRepository enquiryRepo;
	@Autowired
	counsellorRepository counsellorRepo;

	@Override
	public boolean addEnquiry(Enquiry enquiry, Integer counsellor_id) throws Exception {
		Counsellor counsellor = counsellorRepo.findById(counsellor_id).orElse(null);
		if (counsellor == null) {
			throw new Exception("NO COUNSELLOR FOUND");
		}
		enquiry.setCounsellor(counsellor);
		Enquiry savedEnquiry = enquiryRepo.save(enquiry);
		return savedEnquiry.getEnquiryId() != null;
	}

	@Override
	public List<Enquiry> viewAllEnquiry(Integer counsellor_id) {
		List<Enquiry> enquiriesByCounsellorId = enquiryRepo.findEnquiriesBycounsellor_counsellorId(counsellor_id);
		return enquiriesByCounsellorId;
	}

	public List<Enquiry> viewFilteredEnquiry(ViewEnqFilterRequest filteredRequest, Counsellor counsellor) {
		Enquiry enquiry = new Enquiry();
		enquiry.setCourse(filteredRequest.getCourse());
		enquiry.setStatus(filteredRequest.getStatus());
		enquiry.setMode(filteredRequest.getMode());
		enquiry.setCounsellor(counsellor);
		List<Enquiry> filteredEnquiryList = enquiryRepo.findAll(Example.of(enquiry));
		return filteredEnquiryList;
	}

	@Override
	public Enquiry getEnquiryById(Integer enquiry_id) {
		Enquiry enquiry = enquiryRepo.getById(enquiry_id);
		return enquiry;
	}

}
