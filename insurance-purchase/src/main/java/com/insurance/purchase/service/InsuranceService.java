package com.insurance.purchase.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.purchase.model.Insurance;
import com.insurance.purchase.repository.InsuranceRepository;

@Service
public class InsuranceService {
    
	
	    @Autowired
	    private InsuranceRepository insuranceRepository;

	    public List<Insurance> getCuratedInsurances(int age, String gender, double income) {
	        return insuranceRepository.findAll().stream()
	                .filter(insurance -> 
	                    age >= insurance.getMinAge() && 
	                    age <= insurance.getMaxAge() &&
	                    insurance.getGender().equalsIgnoreCase(gender) &&
	                    income >= insurance.getMinIncome())
	                .collect(Collectors.toList());
	    }
	    
	    public List<Insurance> getAllInsurance(){
	    	return insuranceRepository.findAll();
	    }
}
