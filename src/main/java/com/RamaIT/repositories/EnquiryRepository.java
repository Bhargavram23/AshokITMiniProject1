package com.RamaIT.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RamaIT.models.Enquiry;
@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Integer> {
	
	List<Enquiry> findEnquiriesBycounsellor_counsellorId(Integer counsellor_Id);
}
