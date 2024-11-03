package com.RamaIT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RamaIT.models.Counsellor;
@Repository
public interface counsellorRepository extends JpaRepository<Counsellor, Integer> {
	Counsellor findByCounsellorEmail(String email);
	Counsellor findByCounsellorEmailAndPassword(String email,String Password);
}
