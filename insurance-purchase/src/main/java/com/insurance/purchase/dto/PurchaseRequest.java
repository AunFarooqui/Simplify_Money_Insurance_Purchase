package com.insurance.purchase.dto;

public  class PurchaseRequest {
	  private Long insuranceId;
      private String userId;

     
      public Long getInsuranceId() { return insuranceId; }
      public void setInsuranceId(Long insuranceId) {
    	  this.insuranceId = insuranceId; }
      public String getUserId() { 
    	  return userId; }
      public void setUserId(String userId) { 
    	  this.userId = userId; }
}
