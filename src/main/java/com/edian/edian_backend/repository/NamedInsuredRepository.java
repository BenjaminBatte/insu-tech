package com.edian.edian_backend.repository;
import com.edian.edian_backend.entity.NamedInsured;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NamedInsuredRepository extends JpaRepository<NamedInsured, Long> {
}