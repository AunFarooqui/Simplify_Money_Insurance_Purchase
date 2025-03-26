package com.insurance.purchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.purchase.model.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance,Long> {

}
