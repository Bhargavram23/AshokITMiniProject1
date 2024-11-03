package com.RamaIT.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.RamaIT.enumerations.Course;
import com.RamaIT.enumerations.Mode;
import com.RamaIT.enumerations.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Enquiry {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer enquiryId;
	String studentName;
	String phoneNumber;
	@Enumerated(EnumType.STRING)
	Status status;

	@Enumerated(EnumType.STRING)
	Mode mode;

	@Enumerated(EnumType.STRING)
	Course course;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "counsellorId")
	Counsellor counsellor;
	@Column(updatable = false)
	@CreationTimestamp
	LocalDateTime createdDate;
	@UpdateTimestamp
	LocalDateTime updatedDate;

}
