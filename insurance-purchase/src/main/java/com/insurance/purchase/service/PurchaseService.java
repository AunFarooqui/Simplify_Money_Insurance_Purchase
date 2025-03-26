package com.insurance.purchase.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insurance.purchase.exception.ResourceNotFoundException;
import com.insurance.purchase.model.Insurance;
import com.insurance.purchase.model.Purchase;
import com.insurance.purchase.repository.InsuranceRepository;
import com.insurance.purchase.repository.PurchaseRepository;


@Service
public class PurchaseService {
	
	  @Autowired
	    private PurchaseRepository purchaseRepository;
	  
	  @Value("${policy.document.path}")
	    private String policyDocumentPath;
	    
	    @Autowired
	    private InsuranceRepository insuranceRepository;
        
	    @Transactional   
	    public Purchase purchaseInsurance(Long insuranceId, String userId) throws IOException {
	        Insurance insurance = insuranceRepository.findById(insuranceId)
	                .orElseThrow(() -> new RuntimeException("Insurance not found"));
	        
	        Purchase purchase = new Purchase();
	        purchase.setInsurance(insurance);
	        purchase.setUserId(userId);
	        purchase.setPurchaseDate(LocalDate.now());
	     // First save to generate ID
	        Purchase savedPurchase = purchaseRepository.save(purchase);
	        
	        // Generate document with full data
	        savedPurchase.setPolicyDocumentPath(generatePolicyDocument(savedPurchase));

	        
//	        String generatedFilePath = generatePolicyDocument(purchase.getId());
//	        purchase.setPolicyDocumentPath(generatedFilePath);
	        return purchaseRepository.save(purchase);	
	        
	    }   
	    public Purchase getPurchaseById(Long purchaseId) {
	        return purchaseRepository.findById(purchaseId).orElseThrow(()->
	        	new ResourceNotFoundException(
	                    "Purchase not found with id: " + purchaseId));    
	    }
	    
	    private String generatePolicyDocument(Purchase purchase) throws IOException {
	    	 Insurance insurance = purchase.getInsurance();
	    	    Long purchaseId = purchase.getId();
	        String fileName = "policy-" + purchaseId + ".pdf";
	        Path outputPath = (Path) Paths.get(policyDocumentPath, fileName);
	        
	        try (PDDocument document = new PDDocument()) {
	            PDPage page = new PDPage();
	            document.addPage(page);
	            
//	            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
//	                contentStream.beginText();
//	                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
//	                contentStream.newLineAtOffset(100, 700);
//	                contentStream.showText("Insurance Policy Certificate");
//	                contentStream.newLineAtOffset(0, -20);
//	                contentStream.showText("Policy ID: " + purchaseId);
//	                contentStream.newLineAtOffset(0, -20);
//	                contentStream.showText(" This is a policy certificate ");
//	                contentStream.endText();
//	            }
	            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
	                // Set up initial coordinates
	                float margin = 50;
	                float yPosition = 700;
	                float lineSpacing = 20;

	                // Set font and size
	                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
	                
	                // Policy Header
	                contentStream.beginText();
	                contentStream.newLineAtOffset(margin, yPosition);
	                contentStream.showText("Insurance Policy Certificate");
	                contentStream.endText();
	                yPosition -= lineSpacing * 2;

	                // Policy Details from Database
	                contentStream.setFont(PDType1Font.HELVETICA, 12);
	                addTextLine(contentStream, margin, yPosition, "Policy Number: " + purchaseId);
	                yPosition -= lineSpacing;
	                
	                addTextLine(contentStream, margin, yPosition, "Insurance Plan: " + insurance.getName());
	                yPosition -= lineSpacing;
	                
	                addTextLine(contentStream, margin, yPosition, "Coverage Amount: $" + 
	                    String.format("%,.2f", insurance.getCoverageAmount()));
	                yPosition -= lineSpacing;
	                
	                addTextLine(contentStream, margin, yPosition, "Purchase Date: " + 
	                    purchase.getPurchaseDate().format(DateTimeFormatter.ISO_DATE));
	                yPosition -= lineSpacing;
	                
	                addTextLine(contentStream, margin, yPosition, "Policy Holder: " + purchase.getUserId());
	                yPosition -= lineSpacing * 2;

	                // Add more data as needed
	                addTextLine(contentStream, margin, yPosition, "Eligibility Criteria:");
	                yPosition -= lineSpacing;
	                addTextLine(contentStream, margin, yPosition, "Minimum Age: " + insurance.getMinAge());
	                yPosition -= lineSpacing;
	                addTextLine(contentStream, margin, yPosition, "Maximum Age: " + insurance.getMaxAge());
	            }
	            document.save(((java.nio.file.Path) outputPath).toFile());
	            return outputPath.toString();
	            
	        } catch (IOException e) {
	            throw new RuntimeException("Failed to generate policy document", e);
	       }
	       
	  }
	    
	    //helper function to add text line
	    private void addTextLine(PDPageContentStream contentStream, float x, float y, String text) 
	            throws IOException {
	        contentStream.beginText();
	        contentStream.newLineAtOffset(x, y);
	        contentStream.showText(text);
	        contentStream.endText();
	    }

}
