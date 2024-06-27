package com.edian.edian_backend.repository;

import com.edian.edian_backend.entity.Bundle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BundleRepository extends JpaRepository<Bundle, Long> {

    Optional<Bundle> findByBundleNumber(Long bundleNumber);
}
