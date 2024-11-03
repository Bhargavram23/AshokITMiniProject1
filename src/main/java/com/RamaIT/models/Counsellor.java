package com.RamaIT.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Counsellor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer counsellorId;
	public String counsellorName;
	public String counsellorEmail;
	public String password;
	@CreationTimestamp
	@Column(updatable = false)
	public LocalDateTime createdDate;
	@UpdateTimestamp
	public LocalDateTime updatedDate;
}
