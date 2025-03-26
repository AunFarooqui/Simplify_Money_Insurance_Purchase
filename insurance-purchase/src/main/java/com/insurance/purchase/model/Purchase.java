package com.insurance.purchase.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Purchase {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch=FetchType.EAGER)//to Ensure  insurance is loaded
    @JoinColumn(name="insurance_id")
    private Insurance insurance;
    private String userId;
    private LocalDate purchaseDate;
    private String policyDocumentPath;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Insurance getInsurance() {
		return insurance;
	}
	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getPolicyDocumentPath() {
		return policyDocumentPath;
	}
	public void setPolicyDocumentPath(String policyDocumentPath) {
		this.policyDocumentPath = policyDocumentPath;
	}
	public Purchase(Long id, Insurance insurance, String userId, LocalDate purchaseDate, String policyDocumentPath) {
		super();
		this.id = id;
		this.insurance = insurance;
		this.userId = userId;
		this.purchaseDate = purchaseDate;
		this.policyDocumentPath = policyDocumentPath;
	}
	public Purchase() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
