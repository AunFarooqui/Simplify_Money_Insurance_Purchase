package com.insurance.purchase.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.insurance.purchase.dto.PurchaseRequest;
import com.insurance.purchase.exception.FileDownloadException;
import com.insurance.purchase.exception.FileNotFoundException;
import com.insurance.purchase.exception.ResourceNotFoundException;
import com.insurance.purchase.model.Purchase;
import com.insurance.purchase.service.PurchaseService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<Purchase> createPurchase(
            @RequestBody PurchaseRequest request) throws IOException {
        
        Purchase purchase = purchaseService.purchaseInsurance(
            request.getInsuranceId(),
            request.getUserId()
        );
        return ResponseEntity.ok(purchase);
    }

    @GetMapping("/{purchaseId}/policy")
    public ResponseEntity<Resource> downloadPolicy(
            @PathVariable Long purchaseId) {
        
        Purchase purchase = purchaseService.getPurchaseById(purchaseId);
               

        try {
            Path filePath = Paths.get(purchase.getPolicyDocumentPath());
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_PDF)
                        .header(HttpHeaders.CONTENT_DISPOSITION, 
                                "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new FileNotFoundException("Policy document not found");
            }
        } catch (Exception e) {
            throw new FileDownloadException("Failed to download policy document", e);
        }
    }

 }