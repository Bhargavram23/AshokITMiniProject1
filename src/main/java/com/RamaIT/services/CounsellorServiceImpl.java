package com.RamaIT.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RamaIT.enumerations.Status;
import com.RamaIT.models.Counsellor;
import com.RamaIT.models.DashboardResponse;
import com.RamaIT.models.Enquiry;
import com.RamaIT.repositories.EnquiryRepository;
import com.RamaIT.repositories.counsellorRepository;
@Service
public class CounsellorServiceImpl implements CounsellorService {
@Autowired	
counsellorRepository counsellorRepo;
@Autowired
EnquiryRepository enquiryRepo;
@Autowired
EnquiryServiceImpl enquiryService;
	@Override
	public Counsellor registerCounsellor(Counsellor counsellor) {
		Counsellor savedCounsellor = counsellorRepo.save(counsellor);
		return savedCounsellor;
	}

	@Override
	public Counsellor getCounsellorByEmailAndPswd(String email, String pwd) {
		Counsellor counsellor_byemail_password = counsellorRepo.findByCounsellorEmailAndPassword(email, pwd);
		return counsellor_byemail_password;
	}

	@Override
	public DashboardResponse getDashboardInfo(Integer counsellorId) {
		List<Enquiry> enquiries = enquiryService.viewAllEnquiry(counsellorId);
		DashboardResponse dashboardResponse = new DashboardResponse();
		int successCount=0,failCount=0;
		for(Enquiry enquiry:enquiries) {
			if(enquiry.getStatus()==Status.ENROLLED) {
				successCount++;
			}
			if(enquiry.getStatus()==Status.LOST) {
				failCount++;
			}
		}
		dashboardResponse.setEnrolledCount(enquiries.size());
		dashboardResponse.setSuccessCount(successCount);
		dashboardResponse.setFailCount(failCount);
		return dashboardResponse;
	}

	@Override
	public Counsellor getCounsellorByEmail(String email) {
		Counsellor counsellor_byEmail = counsellorRepo.findByCounsellorEmail(email);
		return counsellor_byEmail;
	}

	@Override
	public boolean isCounsellorExistsForEmail(String email) {
		Counsellor byEmail = getCounsellorByEmail( email);
		return byEmail!=null;
	}


}
