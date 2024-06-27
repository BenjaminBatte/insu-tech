package com.edian.edian_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BundleDto {
    private Long id;
    private Long bundleNumber;
    private String description;
    private List<String> policyNumbers;
}
