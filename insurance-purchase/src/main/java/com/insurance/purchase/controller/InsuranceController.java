package com.insurance.purchase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.purchase.dto.UserDetailsRequest;
import com.insurance.purchase.model.Insurance;
import com.insurance.purchase.service.InsuranceService;



@RestController
@RequestMapping("/api/insurances")
public class InsuranceController {
     
	@Autowired
	InsuranceService insuranceService;
	
    @PostMapping("/curated")
    public ResponseEntity<List<Insurance>> getCuratedInsurances(
            @RequestBody UserDetailsRequest userDetails) {
        
    	  List<Insurance> insurances = insuranceService.getCuratedInsurances(
    	            userDetails.getAge(),
    	            userDetails.getGender(),
    	            userDetails.getIncome()
    	        );
        return ResponseEntity.ok(insurances);
    }
    
    @GetMapping
    public ResponseEntity<List<Insurance>> getAllInsurance(){
    	List<Insurance> insurances=insuranceService.getAllInsurance();
    	return ResponseEntity.ok(insurances) ;
    }
    
}
