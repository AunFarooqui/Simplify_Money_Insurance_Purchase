package com.insurance.purchase.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Insurance {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private Double coverageAmount;
    private String description;
    private int minAge;
    private int maxAge;
    private String gender;
    private double minIncome;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getCoverageAmount() {
		return coverageAmount;
	}
	public void setCoverageAmount(Double coverageAmount) {
		this.coverageAmount = coverageAmount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getMinAge() {
		return minAge;
	}
	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}
	public int getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public double getMinIncome() {
		return minIncome;
	}
	public void setMinIncome(double minIncome) {
		this.minIncome = minIncome;
	}
	public Insurance(Long id, String name, String type, Double coverageAmount, String description, int minAge,
			int maxAge, String gender, double minIncome) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.coverageAmount = coverageAmount;
		this.description = description;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.gender = gender;
		this.minIncome = minIncome;
	}
	public Insurance() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
