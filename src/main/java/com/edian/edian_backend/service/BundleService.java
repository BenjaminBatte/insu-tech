package com.edian.edian_backend.service;

import com.edian.edian_backend.dto.BundleDto;

import java.util.List;

public interface BundleService {
    BundleDto createBundle();
    BundleDto getBundleById(Long id);
    List<BundleDto> getAllBundles();
    void deleteBundle(Long id);
    List<BundleDto> addBundles(List<BundleDto> dtoList);
    BundleDto addPolicyToBundle(Long bundleId, Long policyId);
    List<BundleDto> addPoliciesToBundle(Long bundleId, List<Long> policyIds);
}