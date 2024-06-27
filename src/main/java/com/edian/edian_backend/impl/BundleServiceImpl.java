package com.edian.edian_backend.impl;

import com.edian.edian_backend.dto.BundleDto;
import com.edian.edian_backend.entity.Bundle;
import com.edian.edian_backend.entity.Policy;
import com.edian.edian_backend.exception.ResourceNotFoundException;
import com.edian.edian_backend.repository.BundleRepository;
import com.edian.edian_backend.repository.PolicyRepository;
import com.edian.edian_backend.service.BundleService;
import com.edian.edian_backend.utility.BundleServiceUtility;
import lombok.AllArgsConstructor;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class BundleServiceImpl implements BundleService {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private final BundleRepository bundleRepository;
    private PolicyRepository policyRepository;

    public BundleDto createBundle() {
        Bundle bundle = new Bundle();
        bundle.setDescription("Bundle: " + LocalDateTime.now().format(formatter));
        bundle.setBundleNumber(generateRandomBundleNumber());
        bundleRepository.save(bundle);
        return BundleServiceUtility.toBundleDto(bundle);
    }
    private Long generateRandomBundleNumber() {
        long min = 100000L; // Minimum value for 6-digit number (100000)
        long max = 999999L; // Maximum value for 6-digit number (999999)
        return min + (long) (Math.random() * (max - min));
    }
    @Override
    public BundleDto getBundleById(Long id) {
        Bundle bundle = findBundleById(id);
        return BundleServiceUtility.toBundleDto(bundle);
    }

    @Override
    public List<BundleDto> getAllBundles() {
        List<Bundle> bundles = bundleRepository.findAll();
        return bundles.stream()
                .map(BundleServiceUtility::toBundleDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBundle(Long id) {
        Bundle bundle = findBundleById(id);
        bundleRepository.deleteById(id);
    }

    @Override
    public List<BundleDto> addBundles(List<BundleDto> dtoList) {
        List<Bundle> bundleList = dtoList.stream()
                .map(BundleServiceUtility::toBundle)
                .collect(Collectors.toList());

        List<Bundle> savedBundleList = bundleRepository.saveAll(bundleList);

        return savedBundleList.stream()
                .map(BundleServiceUtility::toBundleDto)
                .collect(Collectors.toList());
    }

    @Override

    public BundleDto addPolicyToBundle(Long bundleId, Long policyId) {
        Bundle bundle = findBundleById(bundleId);
        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> new ResourceNotFoundException("Policy with id " + policyId + " not found"));

        bundle.addPolicy(policy);
        bundleRepository.save(bundle);

        return BundleServiceUtility.toBundleDto(bundle);
    }

    @Override
    public List<BundleDto> addPoliciesToBundle(Long bundleId, List<Long> policyIds) {
        Bundle bundle = findBundleById(bundleId);
        List<Policy> policies = policyRepository.findAllById(policyIds);

        for (Policy policy : policies) {
            bundle.addPolicy(policy);
        }

        bundleRepository.save(bundle);

        return List.of(BundleServiceUtility.toBundleDto(bundle));
    }

    private Bundle findBundleById(Long id) {
        return bundleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bundle with id " + id + " not found"));
    }
}