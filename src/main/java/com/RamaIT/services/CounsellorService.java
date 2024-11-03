package com.RamaIT.services;

import com.RamaIT.models.Counsellor;
import com.RamaIT.models.DashboardResponse;

public interface CounsellorService {
	public Counsellor getCounsellorByEmail(String email);
	public boolean isCounsellorExistsForEmail(String email);
	public Counsellor registerCounsellor(Counsellor counsellor);

	public Counsellor getCounsellorByEmailAndPswd(String email, String pwd);

	public DashboardResponse getDashboardInfo(Integer counsellorId);

}
