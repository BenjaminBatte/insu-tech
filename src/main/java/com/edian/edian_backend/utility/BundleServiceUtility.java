package com.edian.edian_backend.utility;

import com.edian.edian_backend.dto.BundleDto;
import com.edian.edian_backend.entity.Bundle;
import com.edian.edian_backend.entity.Policy;
import java.util.List;
import java.util.stream.Collectors;

public class BundleServiceUtility {


    public static BundleDto toBundleDto(Bundle bundle) {
        BundleDto dto = new BundleDto();
        dto.setId(bundle.getId());
        dto.setBundleNumber(bundle.getBundleNumber());
        dto.setDescription(bundle.getDescription());

        if (bundle.getPolicies() != null) {
            List<String> policyNumbers = bundle.getPolicies().stream()
                    .map(Policy::getPolicyNumber)
                    .collect(Collectors.toList());
            dto.setPolicyNumbers(policyNumbers);
        } else {
            dto.setPolicyNumbers(List.of("No policies attached to the Bundle " + bundle.getBundleNumber()));
        }

        return dto;
    }

    public static Bundle toBundle(BundleDto dto) {
        Bundle bundle = new Bundle();
        bundle.setId(dto.getId());

        return bundle;
    }
}
