package com.edian.edian_backend.controller;

import com.edian.edian_backend.dto.BundleDto;
import com.edian.edian_backend.service.BundleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bundles")
@AllArgsConstructor
public class BundleController {

    private final BundleService bundleService;

    @PostMapping("/create")
    public ResponseEntity<BundleDto> createBundle() {
        BundleDto createdBundle = bundleService.createBundle();
        return new ResponseEntity<>(createdBundle, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BundleDto> getBundleById(@PathVariable Long id) {
        BundleDto bundleDto = bundleService.getBundleById(id);
        return ResponseEntity.ok(bundleDto);
    }

    @GetMapping
    public ResponseEntity<List<BundleDto>> getAllBundles() {
        List<BundleDto> bundles = bundleService.getAllBundles();
        return ResponseEntity.ok(bundles);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<BundleDto>> addBundles(@RequestBody List<BundleDto> dtoList) {
        List<BundleDto> addedBundles = bundleService.addBundles(dtoList);
        return new ResponseEntity<>(addedBundles, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBundle(@PathVariable Long id) {
        bundleService.deleteBundle(id);
        return ResponseEntity.ok("Bundle deleted successfully");
    }

    @PostMapping("/{bundleId}/policies/{policyId}")
    public ResponseEntity<BundleDto> addPolicyToBundle(
            @PathVariable Long bundleId,
            @PathVariable Long policyId
    ) {
        BundleDto updatedBundle = bundleService.addPolicyToBundle(bundleId, policyId);
        return ResponseEntity.ok(updatedBundle);
    }

    @PostMapping("/{bundleId}/policies")
    public ResponseEntity<List<BundleDto>> addPoliciesToBundle(
            @PathVariable Long bundleId,
            @RequestBody List<Long> policyIds
    ) {
        List<BundleDto> updatedBundles = bundleService.addPoliciesToBundle(bundleId, policyIds);
        return ResponseEntity.ok(updatedBundles);
    }
}
