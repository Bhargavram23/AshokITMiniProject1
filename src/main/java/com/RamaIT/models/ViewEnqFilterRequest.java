package com.RamaIT.models;

import com.RamaIT.enumerations.Course;
import com.RamaIT.enumerations.Mode;
import com.RamaIT.enumerations.Status;

import lombok.Data;
@Data
public class ViewEnqFilterRequest {
	Status status;
	Mode mode;
	Course course;
}
