package com.RamaIT.services;

import java.util.List;

import com.RamaIT.models.Counsellor;
import com.RamaIT.models.Enquiry;
import com.RamaIT.models.ViewEnqFilterRequest;

public interface EnquiryService {
	public boolean addEnquiry(Enquiry enquiry,Integer counsellor_id)  throws Exception;

	public List<Enquiry> viewAllEnquiry(Integer counsellor_id);

	public List<Enquiry> viewFilteredEnquiry(ViewEnqFilterRequest filteredRequest,Counsellor counsellor);

	public Enquiry getEnquiryById(Integer enquiry_id);
}
