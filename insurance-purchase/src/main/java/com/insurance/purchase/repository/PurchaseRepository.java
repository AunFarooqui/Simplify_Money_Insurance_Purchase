package com.insurance.purchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.purchase.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {

}
